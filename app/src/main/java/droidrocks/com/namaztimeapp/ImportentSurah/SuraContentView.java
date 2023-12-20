package droidrocks.com.namaztimeapp.ImportentSurah;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import droidrocks.com.namaztimeapp.Activitys.AboutActivity;
import droidrocks.com.namaztimeapp.R;


public class SuraContentView extends AppCompatActivity {
    private String tileText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String layout = intent.getStringExtra("layoutName");
        tileText = intent.getStringExtra("title");

        if (layout.equals("sura_fatiha")) {
            setContentView(R.layout.sura_fatiha_layout);
            topBar();
            return;
        }

        if (layout.equals("sura_fill")) {
            setContentView(R.layout.sura_fill_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_ekhlas")) {
            setContentView(R.layout.sura_ekhlas_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_falaq")) {
            setContentView(R.layout.sura_falaq_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_kafirun")) {
            setContentView(R.layout.sura_kafirun_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_kausar")) {
            setContentView(R.layout.sura_kausar_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_lahab")) {
            setContentView(R.layout.sura_lahab_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_maun")) {
            setContentView(R.layout.sura_maun_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_nas")) {
            setContentView(R.layout.sura_nas_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_nasr")) {
            setContentView(R.layout.sura_nasr_layout);
            topBar();
            return;
        }
        if (layout.equals("sura_quraisy")) {
            setContentView(R.layout.sura_quraisy_layout);
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
