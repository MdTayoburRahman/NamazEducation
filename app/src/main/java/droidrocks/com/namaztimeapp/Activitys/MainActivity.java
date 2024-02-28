 package droidrocks.com.namaztimeapp.Activitys;

 import android.Manifest;
 import android.annotation.SuppressLint;
 import android.app.AlertDialog;
 import android.content.ActivityNotFoundException;
 import android.content.Intent;
 import android.content.pm.ActivityInfo;
 import android.net.Uri;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.MenuItem;
 import android.view.View;
 import android.view.WindowManager;
 import android.widget.LinearLayout;

 import androidx.activity.OnBackPressedCallback;
 import androidx.annotation.NonNull;
 import androidx.appcompat.app.ActionBarDrawerToggle;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;
 import androidx.core.view.GravityCompat;
 import androidx.drawerlayout.widget.DrawerLayout;

 import com.google.android.material.dialog.MaterialAlertDialogBuilder;
 import com.google.android.material.navigation.NavigationView;
 import com.google.firebase.messaging.FirebaseMessaging;
 import com.karumi.dexter.Dexter;
 import com.karumi.dexter.MultiplePermissionsReport;
 import com.karumi.dexter.PermissionToken;
 import com.karumi.dexter.listener.PermissionRequest;
 import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
 import com.shurajcodx.appratingdialog.AppRatingDialog;

 import java.util.List;
 import java.util.Objects;

 import droidrocks.com.namaztimeapp.Allah99Name.AllahAr99NamAndFojilotMainActivity;
 import droidrocks.com.namaztimeapp.ImportentSurah.ImportantSuraActivity;
 import droidrocks.com.namaztimeapp.NamazShikkha.NanazShikkahActivity;
 import droidrocks.com.namaztimeapp.R;
 import droidrocks.com.namaztimeapp.Utils.AppUtils;
 import droidrocks.com.namaztimeapp.Utils.UpdateChecker;
 import droidrocks.com.namaztimeapp.databinding.ActivityMainBinding;
 import droidrocks.com.namaztimeapp.kalima.KalimaActivity;
 import droidrocks.com.namaztimeapp.kibla.CompassActivity;


 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private static final String TAG = "MainActivity";
     private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar topAppBar;
    private ActivityMainBinding binding;




     @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // check internet First
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        topBar();
        SetupNavDrawer();
        checkAllPermission();
        RateAppAlert();
        backPressed();
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        try {
            // Check for updates
            UpdateChecker.checkForUpdates(this);
            UpdateChecker.checkForReview(MainActivity.this);
        } catch (Exception e) {
            Log.d(TAG, "onCreate: "+e.getMessage());
            e.printStackTrace();
        }


    }


     private void init() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        LinearLayout CompassCard = findViewById(R.id.compassCard);
         LinearLayout ojurNiyomCard = findViewById(R.id.ojurNiyomCard);
         LinearLayout rakatTabelCard = findViewById(R.id.rakatTabelCard);
         LinearLayout namazShikkaid = findViewById(R.id.namazShikkaid);
         LinearLayout all_surah = findViewById(R.id.all_surah);
         LinearLayout tasbih = findViewById(R.id.tasbih);
         LinearLayout allah_99Name = findViewById(R.id.allah_99Name);
         LinearLayout kalima = findViewById(R.id.kalima);


        CompassCard.setOnClickListener(this);
        ojurNiyomCard.setOnClickListener(this);
        rakatTabelCard.setOnClickListener(this);
        namazShikkaid.setOnClickListener(this);
        all_surah.setOnClickListener(this);
        tasbih.setOnClickListener(this);
        allah_99Name.setOnClickListener(this);
        kalima.setOnClickListener(this);
        binding.islamicBlog.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BlogPostActivity.class));
        });

    }

    private void topBar() {

        topAppBar = findViewById(R.id.topAppBar);
    }




     private void SetupNavDrawer() {
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, topAppBar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.privacy) {

                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    startActivity(new Intent(MainActivity.this, BrowserActivity.class)
                            .putExtra("pageFileName", "privacy.html")
                            .putExtra("pageTitle", "Privacy Policy")
                    );
                }
                if (item.getItemId() == R.id.terms_condition) {

                    startActivity(new Intent(MainActivity.this, BrowserActivity.class)
                            .putExtra("pageFileName", "terms.html")
                            .putExtra("pageTitle", "Terms & Conditions"));

                }

                if (item.getItemId() == R.id.MoreApp) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Md+Tayobur+Rahman")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Md+Tayobur+Rahman")));
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

        // documandation here - https://github.com/shurajcodx/android-app-rating-dialog
        @SuppressLint("UseCompatLoadingForDrawables") final AppRatingDialog appRatingDialog = new AppRatingDialog.Builder(this)
                .setIconDrawable(true, getDrawable(R.drawable.ic_launcher_round)) // default icon isn't show
                .setTriggerCount(2)
                .setRepeatCount(5)
                .build();
        appRatingDialog.show();


    }


     private void backPressed() {
         getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
             @Override
             public void handleOnBackPressed() {
                 //TODO
                 try {
                     showExitAlert();
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }
         });
     }

     private void showExitAlert() {

         if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
             drawerLayout.closeDrawer(GravityCompat.START);
         } else if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {

             MaterialAlertDialogBuilder alertDialogBuilder;
             alertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
             alertDialogBuilder.setTitle("Do you want to exit application?");

             alertDialogBuilder.setCancelable(false);

             alertDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
                 finish();
                 System.exit(0);
             });
             alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
             androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
             alertDialog.show();

         }
     }


 }