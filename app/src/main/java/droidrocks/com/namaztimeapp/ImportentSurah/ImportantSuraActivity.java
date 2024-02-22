package droidrocks.com.namaztimeapp.ImportentSurah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;

import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Utils.AppUtils;

public class ImportantSuraActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_sura);
        topBar();
        init();
    }

    private void init() {
        MaterialCardView fatiha,alFill,quraish,maun,kausar,kafirun,naas,lahab,ekhlas,falak,nasr;
        fatiha = findViewById(R.id.sura_fatiha_id);
        alFill = findViewById(R.id.sura_fill_id);
        quraish = findViewById(R.id.sura_quraisy_id);
        maun = findViewById(R.id.sura_maun_id);
        kausar = findViewById(R.id.sura_kausar_id);
        kafirun = findViewById(R.id.sura_kafirun_id);
        naas = findViewById(R.id.sura_nas_id);
        lahab = findViewById(R.id.sura_lahab_id);
        ekhlas = findViewById(R.id.sura_ekhlas_id);
        falak = findViewById(R.id.sura_falaq_id);
        nasr = findViewById(R.id.sura_nasr_id);


        fatiha.setOnClickListener(this);
        alFill.setOnClickListener(this);
        quraish.setOnClickListener(this);
        maun.setOnClickListener(this);
        kausar.setOnClickListener(this);
        kafirun.setOnClickListener(this);
        naas.setOnClickListener(this);
        lahab.setOnClickListener(this);
        ekhlas.setOnClickListener(this);
        falak.setOnClickListener(this);
        nasr.setOnClickListener(this);
        
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
    public void onClick(View v) {

        if (v.getId()==R.id.sura_fatiha_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_fatiha");
            intent.putExtra("title","সূরা ফাতিহা");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_fill_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_fill");
            intent.putExtra("title","সূরা আল-ফীল");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_ekhlas_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_ekhlas");
            intent.putExtra("title","সূরা ইখলাছ");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_falaq_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_falaq");
            intent.putExtra("title","সূরা ফালাক");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_kafirun_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_kafirun");
            intent.putExtra("title","সূরা কাফিরুন");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_kausar_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_kausar");
            intent.putExtra("title","সূরা কাউছার");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_lahab_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_lahab");
            intent.putExtra("title","সূরা লাহাব");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_maun_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_maun");
            intent.putExtra("title","সূরা মাউন");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_nas_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_nas");
            intent.putExtra("title","সূরা নাস");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_nasr_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_nasr");
            intent.putExtra("title","সূরা নাসর");
            startActivity(intent);
        }
        if (v.getId()==R.id.sura_quraisy_id){
            Intent intent = new Intent(ImportantSuraActivity.this,SuraContentView.class);
            intent.putExtra("layoutName","sura_quraisy");
            intent.putExtra("title","সূরা কোরাইশ");
            startActivity(intent);
        }
        
    }
}