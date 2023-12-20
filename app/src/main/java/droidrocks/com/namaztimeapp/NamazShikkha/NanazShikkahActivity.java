package droidrocks.com.namaztimeapp.NamazShikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;

import droidrocks.com.namaztimeapp.Activitys.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class NanazShikkahActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialCardView namazer_prathomik_bisoy,namajer_niyot,namajer_niyom,namajer_dowa,namajer_vul_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanaz_shikkah);
        topBar();
        init();
    }

    private void init() {
        namajer_dowa= findViewById(R.id.namajer_dowa_button);
        namazer_prathomik_bisoy= findViewById(R.id.namazer_prathomik_bisoy_button);
        namajer_niyot= findViewById(R.id.namajer_niyot_button);
        namajer_niyom= findViewById(R.id.namajer_niyom_button);
        namajer_vul_button= findViewById(R.id.namajer_vul_button);

        namajer_niyom.setOnClickListener(this);
        namajer_niyot.setOnClickListener(this);
        namazer_prathomik_bisoy.setOnClickListener(this);
        namajer_dowa.setOnClickListener(this);
        namajer_vul_button.setOnClickListener(this);
    }

    private void topBar() {

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
        Toolbar topAppBar = findViewById(R.id.toolbar);

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

    @Override
    public void onClick(View v) {


        if (v.getId()==R.id.namajer_dowa_button){

            Intent intent = new Intent(this, NamazerDowaMainActivity.class);
            startActivity(intent);
        }

        if (v.getId()==R.id.namazer_prathomik_bisoy_button){

            Intent intent = new Intent(this, NamzerPrathomikBisoyActivity.class);
            startActivity(intent);

        }

        if (v.getId()==R.id.namajer_niyot_button){

            Intent intent = new Intent(this, NamazerNiyotActivity.class);
            startActivity(intent);
        }

        if (v.getId()==R.id.namajer_niyom_button){

            Intent intent = new Intent(this, NamazPoddotiMainActivity.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.namajer_vul_button){

            Intent intent = new Intent(this, ContentViewActivity.class);
            intent.putExtra("layoutName","namajer_vul");
            intent.putExtra("title","নামাজে ভূল হলে করনীয় সমূহ");
            startActivity(intent);
        }



    }
}