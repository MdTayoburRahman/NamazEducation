package droidrocks.com.namaztimeapp.kalima;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import droidrocks.com.namaztimeapp.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class KalimaContentView extends AppCompatActivity {
    private String tileText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String layout = intent.getStringExtra("layoutName");
        tileText = intent.getStringExtra("title");

        if (layout.equals("kalimaRaddekufor")) {
            setContentView(R.layout.kalima_raddekufor_layout);
            topBar();
            return;
        }
        if (layout.equals("kalimaSahadat")) {
            setContentView(R.layout.kalima_sahadat_layout);
            topBar();
            return;
        }
        if (layout.equals("kalimaTamjid")) {
            setContentView(R.layout.kalima_tamjid_layout);
            topBar();
            return;
        }
        if (layout.equals("kalimaTaowhid")) {
            setContentView(R.layout.kalima_taowhid_layout);
            topBar();
            return;
        }
         if (layout.equals("kalimaTayyeba")) {
            setContentView(R.layout.kalima_tayyeba_layout);
            topBar();
            return;
        }


    }
    private void topBar() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
        Toolbar topAppBar = findViewById(R.id.toolbar);
        topAppBar.setTitle(tileText);

        topAppBar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.about) {

                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
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
}
