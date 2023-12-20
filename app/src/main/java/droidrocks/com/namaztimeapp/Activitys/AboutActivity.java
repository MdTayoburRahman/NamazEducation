package droidrocks.com.namaztimeapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

import droidrocks.com.namaztimeapp.R;

public class AboutActivity extends AppCompatActivity {
    Toolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topBar();

        Button share = findViewById(R.id.shareButtonID);
        Button rateus = findViewById(R.id.RateUsButtonID);

        // share app
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlePlayShare();
            }
        });

        // rate app
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlePlayRate();
            }
        });
    }
    private void topBar() {
        topAppBar = findViewById(R.id.toolbar);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void googlePlayRate() {

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

    public void googlePlayShare() {

        Uri url = Uri.parse("http://play.google.com/store/apps/details?id=" + Objects.requireNonNull(getApplicationContext()).getPackageName());

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download "+R.string.app_name+" from this Url";
        String shareSub = String.valueOf(R.string.app_name);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody + url);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));

    }
}