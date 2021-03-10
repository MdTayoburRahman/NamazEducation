package droidrocks.com.namaztimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import droidrocks.com.namaztimeapp.Tools.AppInternetStatus;
import droidrocks.com.namaztimeapp.Tools.InternetCheckActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkInternet();


            }
        },3000);
    }
    @SuppressLint("WrongConstant")
    private void checkInternet() {
        if (AppInternetStatus.getInstance(this).isOnline()) {
            Log.d("TAG", "checkInternet: Internet Connected" );
            Intent SplashIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
            startActivity(SplashIntent);
            finish();


        } else {
            Log.d("TAG", "checkInternet: Internet not Connected" );
            Intent SplashIntent = new Intent(SplashScreenActivity.this, InternetCheckActivity.class);
            startActivity(SplashIntent);
            finish();

        }

    }
}