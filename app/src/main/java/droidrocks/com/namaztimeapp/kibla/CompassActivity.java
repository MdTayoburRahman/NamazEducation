package droidrocks.com.namaztimeapp.kibla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Utils.AppUtils;

public class CompassActivity extends AppCompatActivity {

    private static final String TAG = "CompassTag";
    private boolean compassFound = false;
    private Compass compass;
    private ImageView arrowView;
    private TextView tvOutput, tvOutput2, tvDegree, tvMagStrength;


    private float currentAzimuth;
    float bearing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        topBar();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar


        arrowView =  findViewById(R.id.main_image_hands);
        tvOutput =  findViewById(R.id.tv_output);
        tvOutput2 =  findViewById(R.id.tv_output2);
        tvDegree =  findViewById(R.id.tv_degree);
        tvMagStrength = findViewById(R.id.tv_mag_strength);
        setupCompass();
    }
    private void topBar() {

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
       Toolbar topAppBar = findViewById(R.id.toolbar);

        topAppBar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.about) {
                AppUtils.showAboutAlert(this);
                return true;
            }

            return false;
        });

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compass = null;
    }

    private void setupCompass() {
        compass = new Compass(this);
        Compass.CompassListener cl = new Compass.CompassListener() {
            @Override
            public void onNewAzimuth(float azimuth) {
                adjustArrow(azimuth);
            }

            @Override
            public void onMagField(float strength) {
                MagField(strength);
            }
        };
        compass.setListener(cl);

        if (compass != null) {
            compass.setListener(cl);
            if (compass.getStatus()) {
                compassFound = true;
            }
        }
        if (!compassFound) {
            tvOutput.setText("Sorry, but this device not contain magnetic sensor.");
        }
    }

    private void MagField(float strength) {
        tvMagStrength.setText(strength + " μT"); //milli Tesla
    }

    private void adjustArrow(float azimuth) {
        if (compass.getSensorData()) {
            float heading = azimuth;
            heading = (bearing - heading) * -1;

            Animation an = new RotateAnimation(-currentAzimuth, -heading,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            currentAzimuth = heading;

            an.setDuration(500);
            an.setRepeatCount(0);
            an.setFillAfter(true);

            arrowView.startAnimation(an);
            tvOutput.setText(String.valueOf(azimuth));
            tvOutput2.setText(String.valueOf(heading));
            showDirection(heading);
        } else {
            tvOutput.setText("Sorry, no data from compass sensor.");
        }
    }

    private void showDirection(float degree) {
        String heading = "";
        if (degree >= 338 || degree < 23) {
            //GOING NORTH
            heading = "N";
        } else if (degree >= 23 && degree < 68) {
            //GOING NORTH EAST
            heading = "NE";
        } else if (degree >= 68 && degree < 113) {
            //GOING EAST
            heading = "E";
        } else if (degree >= 113 && degree < 158) {
            //GOING SOUTH EAST
            heading = "SE";
        } else if (degree >= 158 && degree < 203) {
            //GOING SOUTH
            heading = "S";
        } else if (degree >= 203 && degree < 248) {
            //GOING SOUTH WEST
            heading = "SW";
        } else if (degree >= 248 && degree < 293) {
            //GOING WEST
            heading = "W";
        } else if (degree >= 293 && degree < 338) {
            //GOING NORTH WEST
            heading = "NW";
        }

        tvDegree.setText(Math.round(degree) + "° " + heading);
    }
}