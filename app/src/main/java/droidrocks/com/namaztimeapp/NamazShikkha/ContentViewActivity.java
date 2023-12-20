package droidrocks.com.namaztimeapp.NamazShikkha;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import droidrocks.com.namaztimeapp.Activitys.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class ContentViewActivity extends AppCompatActivity {
    String tileText = "সালাত";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String layout = intent.getStringExtra("layoutName");
        tileText = intent.getStringExtra("title");

        // this code is for NamazerNiyotActivity
        if (layout.equals("fojor")) {
            setContentView(R.layout.fojor_layout);
            topBar();
            return;
        } else if (layout.equals("zuhar")) {
            setContentView(R.layout.zuhar_layout);
            topBar();
            return;
        } else if (layout.equals("asr")) {
            setContentView(R.layout.asr_layout);
            topBar();
            return;
        } else if (layout.equals("magrib")) {
            setContentView(R.layout.magrib_layout);
            topBar();
            return;
        } else if (layout.equals("isha")) {
            setContentView(R.layout.isha_layout);
            topBar();
            return;
        }
        //---------------------------------------------------------
        // this code is for NamazerPoddotiActivity

        else if (layout.equals("four_rakat")) {
            setContentView(R.layout.four_rakat_layout);
            topBar();
            return;
        } else if (layout.equals("three_rakat")) {
            setContentView(R.layout.three_rakat_layout);
            topBar();
            return;
        } else if (layout.equals("two_rakat")) {
            setContentView(R.layout.two_rakat_layout);
            topBar();
            return;
        } else if (layout.equals("kaja")) {
            setContentView(R.layout.kaja_layout);
            topBar();
            return;
        } else if (layout.equals("nofol")) {
            setContentView(R.layout.nofol_layout);
            topBar();
            return;
        } else if (layout.equals("betor")) {
            setContentView(R.layout.betor_layout);
            topBar();
            return;
        }


        // this code is for namaz er dowa

        else if (layout.equals("dowa_kunut")) {
            setContentView(R.layout.dowa_kunut_layout);
            topBar();
            return;
        }
        else if (layout.equals("doway_masura")) {
            setContentView(R.layout.doway_masura_layout);
            topBar();
            return;
        }
        else if (layout.equals("dorud_shorif")) {
            setContentView(R.layout.dorud_shorif_layout);
            topBar();
            return;
        }
        else if (layout.equals("jaynamazer_dowa")) {
            setContentView(R.layout.jaynamazer_dowa_layout);
            topBar();
            return;
        }
        else if (layout.equals("ruku_sejdar_tasbih")) {
            setContentView(R.layout.ruku_sejdar_tasbih_layout);
            topBar();
            return;
        }
        else if (layout.equals("salam_munajat")) {
            setContentView(R.layout.salam_munajat_layout);
            topBar();
            return;
        }
        else if (layout.equals("sana")) {
            setContentView(R.layout.sana_layout);
            topBar();
            return;
        }
        else if (layout.equals("takbire_tahrima")) {
            setContentView(R.layout.takbire_tahrima_layout);
            topBar();
            return;
        }
        else if (layout.equals("tasahud")) {
            setContentView(R.layout.tasahud_layout);
            topBar();
            return;
        }

        // নামাজের ভুল সমূহ ----------------------------
        else if (layout.equals("namajer_vul")){
            setContentView(R.layout.namajer_vul_layout);
            topBar();
            return;
        }




        else {
            setContentView(R.layout.activity_content_view);
            topBar();
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