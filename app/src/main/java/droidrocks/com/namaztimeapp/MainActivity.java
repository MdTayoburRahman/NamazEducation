 package droidrocks.com.namaztimeapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


import droidrocks.com.namaztimeapp.Allah99Name.AllahAr99NamAndFojilotMainActivity;
import droidrocks.com.namaztimeapp.Azan.AzanActivity;
import droidrocks.com.namaztimeapp.Azan.AzanAdapter;
import droidrocks.com.namaztimeapp.ImportentSurah.ImportantSuraActivity;
import droidrocks.com.namaztimeapp.NamazShikkha.NanazShikkahActivity;
import droidrocks.com.namaztimeapp.Tools.AppController;
import droidrocks.com.namaztimeapp.Tools.AppInternetStatus;
import droidrocks.com.namaztimeapp.Tools.CountdownTimerClass;
import droidrocks.com.namaztimeapp.Tools.InternetCheckActivity;
import droidrocks.com.namaztimeapp.Tools.TimeConverter;
import droidrocks.com.namaztimeapp.kalima.KalimaActivity;
import droidrocks.com.namaztimeapp.kibla.CompassActivity;
import hotchemi.android.rate.AppRate;

import static android.graphics.Typeface.createFromFile;


 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar topAppBar;

    private TextView countdownTextView;
    private MaterialCardView countdownCardView;



    private TextView startFajar, startZuhar, startAsr, startMagrib, startEsha, hijriDate;
    private TextView endFajar, endZuhar, endAsr, endMagrib, endEsha;
     TextView iftar;
     TextView sehri;

     private ImageButton alarm_button_isha,alarm_button_magrib,alarm_button_asr,alarm_button_zuhar,alarm_button_fajr;


    private String sFajar, sZuhar, sAsr, sMagrib, sIsha;



    private TextView sunRise, sunSet;
    private Spinner spinner;
    private String spinnerValue;
    String tag_json_obj = "json_obj_req";
    final TimeConverter timeConverter = new TimeConverter();
    private static final String TAG = "tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // check internet First
        checkInternet();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        topBar();
        SetupNavDrawer();
        checkAllPermission();
        RamadanCountdown();
        RateAppAlert();


        try {
            setUpSpinner();
        } catch (Exception e) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show();
        }


    }

     private void RamadanCountdown() {



         Calendar start_calendar = Calendar.getInstance();
         Calendar end_calendar = Calendar.getInstance();
         end_calendar.set(2021, 3, 12); // 10 = November, month start at 0 = January
         long start_millis = start_calendar.getTimeInMillis(); //get the start time in milliseconds
         long end_millis = end_calendar.getTimeInMillis(); //get the end time in milliseconds
         long total_millis = (end_millis - start_millis); //total time in milliseconds

         //1000 = 1 second interval
         CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
             @Override
             public void onTick(long millisUntilFinished) {
                 long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                 millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                 long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                 millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                 long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                 millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                 long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
          countdownTextView.setText(days + " দিন : " + hours + " ঘন্টা : " + minutes + " মিনিট : " + seconds+" সেকেন্ড"); //You can compute the millisUntilFinished on hours/minutes/seconds

             }

             @Override
             public void onFinish() {
                 countdownTextView.setText("Finish!");
                 countdownCardView.setVisibility(View.GONE);

             }
         };
         cdt.start();







     }


     private void checkInternet() {
        if (AppInternetStatus.getInstance(this).isOnline()) {
            Log.d("TAG", "checkInternet: Internet Connected");

        } else {
            Log.d("TAG", "checkInternet: Internet not Connected");
            Intent SplashIntent = new Intent(MainActivity.this, InternetCheckActivity.class);
            startActivity(SplashIntent);
            finish();
        }
    }

    private void setUpSpinner() {

        spinner = findViewById(R.id.spinnerFirst);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = parent.getItemAtPosition(position).toString();

                if (spinnerValue.equals("ঢাকা")) {
                    Dhaka();
                    testMilS();
                    Toast.makeText(MainActivity.this, "ঢাকার সময়। ", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("সিলেট")) {
                    Sylhet();
                    testMilS();
                    Toast.makeText(MainActivity.this, "সিলেটের সময়।", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("খুলনা")) {
                    Khulna();
                    testMilS();
                    Toast.makeText(MainActivity.this, "খুলনার সময়।", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("রাজশাহী")) {
                    rajshahi();
                    testMilS();
                    Toast.makeText(MainActivity.this, "রাজশাহীর সময়।", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("বরিশাল")) {
                    borishal();
                    testMilS();
                    Toast.makeText(MainActivity.this, "বরিশালের সময়।", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("রংপুর")) {
                    rongpur();
                    testMilS();
                    Toast.makeText(MainActivity.this, "রংপুর এর সময়।", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("ময়মনসিংহ")) {
                    maymansing();
                    testMilS();
                    Toast.makeText(MainActivity.this, "ময়মনসিংহের সময়।", Toast.LENGTH_SHORT).show();
                }
                if (spinnerValue.equals("চট্টগ্রাম")) {
                    ctg();
                    testMilS();
                    Toast.makeText(MainActivity.this, "চট্টগ্রাম এর সময়।", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    // this method is calculate for wakt ses
    private void testMilS() {
        // এই ম্যাথডের মাধ্যমে ওয়াক্তের শেষ সময় হিসাব করা হয়েছে
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                        String eFjr = timeConverter.ExtactEndTime(MainActivity.this, sFajar, 74);
                        endFajar.setText(timeConverter.TimeConvertTO(eFjr));

                        // সেহরীর শেষ
                        String eSehri = timeConverter.ExtactEndTime(MainActivity.this,sFajar,-5);
                        sehri.setText("সেহরির শেষ- "+timeConverter.TimeConvertTO(eSehri));

                        endZuhar.setText(timeConverter.TimeConvertTO(sAsr));

                        String eAsr = timeConverter.SubtractTime(MainActivity.this, sMagrib, -21);
                        endAsr.setText(timeConverter.TimeConvertTO(eAsr));

                        endMagrib.setText(timeConverter.TimeConvertTO(sIsha));

                        String eIsha = timeConverter.ExtactEndTime(MainActivity.this, sIsha, 550);
                        endEsha.setText(timeConverter.TimeConvertTO(eIsha));


                       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            try {
                                setUpCountDownforWakt();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
*/
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        }, 2000);


    }

    private void init() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        startFajar = findViewById(R.id.startFajr);
        startZuhar = findViewById(R.id.startZuhar);
        startAsr = findViewById(R.id.startAsr);
        startMagrib = findViewById(R.id.statMagrib);
        startEsha = findViewById(R.id.startIsha);
        hijriDate = findViewById(R.id.hijriDate);

        endFajar = findViewById(R.id.endFajr);
        endZuhar = findViewById(R.id.endZuhar);
        endAsr = findViewById(R.id.endAsr);
        endMagrib = findViewById(R.id.endMagrib);
        endEsha = findViewById(R.id.endIsha);

        iftar = findViewById(R.id.iftarID);
        sehri = findViewById(R.id.sehriID);
        countdownTextView = findViewById(R.id.countDownTextView);

        sunRise = findViewById(R.id.sunrise);
        sunSet = findViewById(R.id.sunset);

        alarm_button_fajr = findViewById(R.id.alarm_button_fajr);
        alarm_button_zuhar = findViewById(R.id.alarm_button_zuhar);
        alarm_button_asr = findViewById(R.id.alarm_button_asr);
        alarm_button_magrib = findViewById(R.id.alarm_button_magrib);
        alarm_button_isha = findViewById(R.id.alarm_button_isha);

        alarm_button_fajr.setOnClickListener(this);
        alarm_button_zuhar.setOnClickListener(this);
        alarm_button_asr.setOnClickListener(this);
        alarm_button_magrib.setOnClickListener(this);
        alarm_button_isha.setOnClickListener(this);


        MaterialCardView CompassCard = findViewById(R.id.compassCard);
        MaterialCardView ojurNiyomCard = findViewById(R.id.ojurNiyomCard);
        MaterialCardView rakatTabelCard = findViewById(R.id.rakatTabelCard);
        MaterialCardView namazShikkaid = findViewById(R.id.namazShikkaid);
        MaterialCardView all_surah = findViewById(R.id.all_surah);
        MaterialCardView tasbih = findViewById(R.id.tasbih);
        MaterialCardView allah_99Name = findViewById(R.id.allah_99Name);
        MaterialCardView kalima = findViewById(R.id.kalima);
        MaterialCardView azan = findViewById(R.id.azan);
        countdownCardView = findViewById(R.id.countdownCardView);

        CompassCard.setOnClickListener(this);
        ojurNiyomCard.setOnClickListener(this);
        rakatTabelCard.setOnClickListener(this);
        namazShikkaid.setOnClickListener(this);
        all_surah.setOnClickListener(this);
        tasbih.setOnClickListener(this);
        allah_99Name.setOnClickListener(this);
        kalima.setOnClickListener(this);
        azan.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUpCountDownforWakt() throws ParseException {
        // এই মেথদ ওয়াক্তের কত সময় বাকি আছে সেটা নির্নয় করা হয় । এর কাজ পুরো শেষ হয়নি শুধু current date আর eventDate আউটপুট আসে ।
        LocalDate current_year = LocalDate.now();
        String EVENT_DATE_TIME_FAJAR = current_year+" "+endFajar.getText().toString()+":00";
        String EVENT_DATE_TIME_JUHAR = current_year+" "+endZuhar.getText().toString()+":00";
        String EVENT_DATE_TIME_ASR = current_year+" "+endAsr.getText().toString()+":00";
        String EVENT_DATE_TIME_MAGRIB = current_year+" "+endMagrib.getText().toString()+":00";
        String EVENT_DATE_TIME_ISHA = current_year+" "+endEsha.getText().toString()+":00";

        String DATE_FRMAT = "yyyy-MM-dd HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FRMAT);

        Date current_date = new Date();
        long current_time = current_date.getTime();
        Log.d(TAG, "setUpCountDown: C_DATE "+current_date);
        
        Date event_date_fajar = dateFormat.parse(EVENT_DATE_TIME_FAJAR);
        long event_fajar_time = event_date_fajar.getTime();
        Log.d(TAG, "setUpCountDown: E_FAJAR= "+event_date_fajar);
        
        Date event_date_juhar = dateFormat.parse(EVENT_DATE_TIME_JUHAR);
        long event_juhar_time = event_date_juhar.getTime();
        Log.d(TAG, "setUpCountDown: E_JUHAR= "+event_date_juhar);
        
        Date event_date_asr = dateFormat.parse(EVENT_DATE_TIME_ASR);
        long event_Asr_time = event_date_asr.getTime();
        Log.d(TAG, "setUpCountDown: E_ASR= "+event_date_asr);
        
        Date event_date_magrib = dateFormat.parse(EVENT_DATE_TIME_MAGRIB);
        long event_magrib_time = event_date_magrib.getTime();
        Log.d(TAG, "setUpCountDown: E_MAGRIB= "+event_date_magrib);
        
        Date event_date_isha = dateFormat.parse(EVENT_DATE_TIME_ISHA);
        long event_isha_time = event_date_isha.getTime();
        Log.d(TAG, "setUpCountDown: E_ISHA= "+event_date_isha);
        //---------------------------------------------------------------

        if (current_time>event_fajar_time && current_time<=event_juhar_time){
            Log.d(TAG, "setUpCountDownforWakt: FAJAR WORK");
        }
        if (current_time>event_juhar_time && current_time<=event_Asr_time){
            Log.d(TAG, "setUpCountDownforWakt: ZUHAR WORK");
        }
        if (current_time>event_Asr_time && current_time<=event_magrib_time){
            Log.d(TAG, "setUpCountDownforWakt: ASR WORK");
        }
        if (current_time>event_magrib_time && current_time<=event_isha_time){
            Log.d(TAG, "setUpCountDownforWakt: MAGRIB WORK");
        }
        if (current_time>event_isha_time){
            Log.d(TAG, "setUpCountDownforWakt: ISA WORK");
        }



        
        
        
        
        /*if (!current_date.before(event_date_fajar)){
            Log.d(TAG, "setUpCountDownforWakt: Fajar wok");
        }
        if (!current_date.before(event_date_juhar)){
            Log.d(TAG, "setUpCountDownforWakt: Zuhar wok");
        }
        if (!current_date.before(event_date_asr)){
            Log.d(TAG, "setUpCountDownforWakt: Asr wok");
        }
        if (!current_date.before(event_date_magrib)){
            Log.d(TAG, "setUpCountDownforWakt: Magrib wok");
        }
        if (!current_date.before(event_date_isha)){
            Log.d(TAG, "setUpCountDownforWakt: Isha wok");
        }
        else {
            Log.d(TAG, "setUpCountDownforWakt: NOTHING WORK");
        }*/

    }

    private void Dhaka() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=dhaka&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            sZuhar = mduhar;
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));


                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void Sylhet() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=Sylhet&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void borishal() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=borishal&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void rajshahi() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=rajshahi&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void rongpur() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=rongpur&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void maymansing() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=maymansing&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void ctg() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=ctg&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }

    private void Khulna() {


        String url = "http://api.aladhan.com/v1/timingsByCity?city=Khulna&country=Bangladesh&method=8";  // for dhaka

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String mfajr = response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            sFajar = mfajr;
                            startFajar.setText(timeConverter.TimeConvertTO(mfajr));


                            String mduhar = response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            startZuhar.setText(timeConverter.TimeConvertTO(mduhar));
                            sZuhar = mduhar;

                            String masr = response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            startAsr.setText(timeConverter.TimeConvertTO(masr));
                            sAsr = masr;

                            String mmagrib = response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            startMagrib.setText(timeConverter.TimeConvertTO(mmagrib));
                            iftar.setText("ইফতার শুরু -"+timeConverter.TimeConvertTO(mmagrib));
                            sMagrib = mmagrib;

                            String misha = response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            startEsha.setText(timeConverter.TimeConvertTO(misha));
                            sIsha = misha;

                            String hijridate = response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            hijriDate.setText("আজকের হিজরি তারিখ \n "+hijridate);

                            String sunriseing = response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            sunRise.setText(timeConverter.TimeConvertTO(sunriseing));

                            String sunseting = response.getJSONObject("data").getJSONObject("timings").get("Sunset").toString();
                            sunSet.setText(timeConverter.TimeConvertTO(sunseting));


                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error =" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);

    }


    private void topBar() {

        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.about) {

                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
            }


            return false;
        });
    }

    private void SetupNavDrawer() {
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, topAppBar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.MoreApp) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Droid+Rocks")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Droid+Rocks")));
                    }

                }


                if (item.getItemId() == R.id.feedbackid) {

                    Intent Email = new Intent(Intent.ACTION_SEND);
                    Email.setType("text/email");
                    Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"tayoburrahman119@gmail.com"});
                    Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback About " + getPackageName());
                    Email.putExtra(Intent.EXTRA_TEXT, "Hello..\n" + "");
                    startActivity(Intent.createChooser(Email, "Send Feedback:"));

                }
                if (item.getItemId() == R.id.about) {
                     startActivity(new Intent(MainActivity.this, AboutActivity.class));
                }
                if (item.getItemId() == R.id.shareid) {
                    Uri url = Uri.parse("http://play.google.com/store/apps/details?id=" + Objects.requireNonNull(getApplicationContext()).getPackageName());

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Download " + R.string.app_name + " from this Url";
                    String shareSub = String.valueOf(R.string.app_name);
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody + url);
                    startActivity(Intent.createChooser(sharingIntent, "Share using"));
                }
                if (item.getItemId() == R.id.rateAppid) {
                    Uri uri = Uri.parse("market://details?id=" + Objects.requireNonNull(getApplicationContext()).getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    // To count with Play market backstack, After pressing back button,
                    // to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                    }
                }
                if (item.getItemId() == R.id.exitid) {

                    AlertDialog.Builder alertDialogBuilder;
                    alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setIcon(R.drawable.alerticon);
                    alertDialogBuilder.setTitle("do you want to exit application?");
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        finish();
                        System.exit(0);
                    });

                    alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }


                return false;
            }
        });


    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.compassCard) {
            startActivity(new Intent(MainActivity.this, CompassActivity.class));
        }
        if (v.getId() == R.id.ojurNiyomCard) {
            startActivity(new Intent(MainActivity.this, OjurNiyomActivity.class));

        }
        if (v.getId() == R.id.rakatTabelCard) {
            startActivity(new Intent(MainActivity.this, RakatTableActivity.class));

        }
        if (v.getId() == R.id.namazShikkaid) {
            startActivity(new Intent(MainActivity.this, NanazShikkahActivity.class));
        }
        if (v.getId() == R.id.all_surah) {
            startActivity(new Intent(MainActivity.this, ImportantSuraActivity.class));

        }
        if (v.getId() == R.id.tasbih) {
            startActivity(new Intent(MainActivity.this, TasbihActivity.class));

        }
        if (v.getId() == R.id.allah_99Name) {
            startActivity(new Intent(MainActivity.this, AllahAr99NamAndFojilotMainActivity.class));

        }
        if (v.getId() == R.id.kalima) {
            startActivity(new Intent(MainActivity.this, KalimaActivity.class));

        }
        if (v.getId()==R.id.azan){
            startActivity(new Intent(MainActivity.this, AzanActivity.class));
        }

        if (v.getId()== R.id.alarm_button_fajr){

           String [] parts = sFajar.split(":");
           int part1 = Integer.parseInt(parts[0]);
           int part2 = Integer.parseInt(parts[1]);
            Log.d(TAG, "onClick: "+part1);
            Log.d(TAG, "onClick: "+part2);

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "ফজরের নামাজ");
            i.putExtra(AlarmClock.EXTRA_HOUR, part1);
            i.putExtra(AlarmClock.EXTRA_MINUTES, part2);
            startActivity(i);


        }
        if (v.getId()== R.id.alarm_button_zuhar){

            String [] parts = sZuhar.split(":");
            int part1 = Integer.parseInt(parts[0]);
            int part2 = Integer.parseInt(parts[1]);
            Log.d(TAG, "onClick: "+part1);
            Log.d(TAG, "onClick: "+part2);

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "যোহরের নামাজ");
            i.putExtra(AlarmClock.EXTRA_HOUR, part1);
            i.putExtra(AlarmClock.EXTRA_MINUTES, part2);
            startActivity(i);



        }
        if (v.getId()== R.id.alarm_button_asr){

            String [] parts = sAsr.split(":");
            int part1 = Integer.parseInt(parts[0]);
            int part2 = Integer.parseInt(parts[1]);
            Log.d(TAG, "onClick: "+part1);
            Log.d(TAG, "onClick: "+part2);

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "আসরের নামাজ");
            i.putExtra(AlarmClock.EXTRA_HOUR, part1);
            i.putExtra(AlarmClock.EXTRA_MINUTES, part2);
            startActivity(i);

        }
        if (v.getId()== R.id.alarm_button_magrib){

            String [] parts = sMagrib.split(":");
            int part1 = Integer.parseInt(parts[0]);
            int part2 = Integer.parseInt(parts[1]);
            Log.d(TAG, "onClick: "+part1);
            Log.d(TAG, "onClick: "+part2);

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "মাগরিবের নামাজ");
            i.putExtra(AlarmClock.EXTRA_HOUR, part1);
            i.putExtra(AlarmClock.EXTRA_MINUTES, part2);
            startActivity(i);

        }
        if (v.getId()== R.id.alarm_button_isha){

            String [] parts = sIsha.split(":");
            int part1 = Integer.parseInt(parts[0]);
            int part2 = Integer.parseInt(parts[1]);
            Log.d(TAG, "onClick: "+part1);
            Log.d(TAG, "onClick: "+part2);

            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "ইশার নামাজ");
            i.putExtra(AlarmClock.EXTRA_HOUR, part1);
            i.putExtra(AlarmClock.EXTRA_MINUTES, part2);
            startActivity(i);

        }


    }

    private void checkAllPermission() {

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Log.d("TAG", "Permission Granted");
                            // do you work now
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                            Log.d("TAG", "Permission Granted");
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();


    }

    public void RateAppAlert() {

        AppRate.with(this)
                .setInstallDays(1) // default 10, 0 means install day.
                .setLaunchTimes(2) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);


    }

     @Override
     public void onBackPressed() {
         if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
             drawerLayout.closeDrawer(GravityCompat.START);
         } else if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {

             AlertDialog.Builder alertDialogBuilder;
             alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
             alertDialogBuilder.setIcon(R.drawable.alerticon);
             alertDialogBuilder.setTitle("Do you want to exit application?");
             alertDialogBuilder.setMessage("Press 'Yes' if you want to exit the app.If you do not want to leave the app, press 'No'.");

             alertDialogBuilder.setCancelable(false);


             alertDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
                 finish();
                 System.exit(0);
             });

             alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());

             AlertDialog alertDialog = alertDialogBuilder.create();
             alertDialog.show();

         } else {
             super.onBackPressed();
         }
     }


}