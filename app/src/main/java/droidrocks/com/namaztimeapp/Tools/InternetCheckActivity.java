package droidrocks.com.namaztimeapp.Tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import droidrocks.com.namaztimeapp.Activitys.MainActivity;
import droidrocks.com.namaztimeapp.R;

public class InternetCheckActivity extends AppCompatActivity {

    private Intent panelIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_check);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        MaterialButton materialButton = findViewById(R.id.checkWifi);
        MaterialButton materialButton2 = findViewById(R.id.checkNow);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                }
                else {
                    panelIntent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
                }
                startActivity(panelIntent);
            }


        });


        materialButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppInternetStatus.getInstance(getApplicationContext()).isOnline()) {
                    Log.d("TAG", "checkInternet: Internet Connected" );
                    Toast.makeText(getApplicationContext(), "ইন্টারনেট সংযোগ সফল হয়েছে।", Toast.LENGTH_SHORT).show();
                    Intent SplashIntent = new Intent(InternetCheckActivity.this, MainActivity.class);
                    startActivity(SplashIntent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "অনুগ্রহ করে আপনার ইন্টারনেট সংযোগটি চালু করুন। তারপর পুনরায় চেক করুন।", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}