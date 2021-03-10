package droidrocks.com.namaztimeapp.NamazShikkha;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.card.MaterialCardView;

import droidrocks.com.namaztimeapp.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class NamazerDowaMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namazer_dowa_main);
        topBar();
        init();

    }

    private void init() {
        MaterialCardView dowakunut, dowayMasura, dorudShorif, jaynamazerDaranorDowa, rukuShejdarTasbih, salamAndMunajat, sana, takbireTahrima, tasahud;

        dowakunut = findViewById(R.id.dowakunutID);
        dowayMasura = findViewById(R.id.dowayMasuraID);
        dorudShorif = findViewById(R.id.dorudShorifID);
        jaynamazerDaranorDowa = findViewById(R.id.jaynamazerDaranorDowaID);
        rukuShejdarTasbih = findViewById(R.id.rukuShejdarTasbihID);
        salamAndMunajat = findViewById(R.id.salamAndMunajatID);
        sana = findViewById(R.id.sanaID);
        takbireTahrima = findViewById(R.id.takbireTahrimaID);
        tasahud = findViewById(R.id.tasahudID);

        dowakunut.setOnClickListener(this);
        dowayMasura.setOnClickListener(this);
        dorudShorif.setOnClickListener(this);
        jaynamazerDaranorDowa.setOnClickListener(this);
        rukuShejdarTasbih.setOnClickListener(this);
        salamAndMunajat.setOnClickListener(this);
        sana.setOnClickListener(this);
        takbireTahrima.setOnClickListener(this);
        tasahud.setOnClickListener(this);

    }

    private void topBar() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
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

        if (v.getId() == R.id.dowakunutID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","dowa_kunut");
            intent.putExtra("title","দোয়া কুনুত");
            startActivity(intent);


        }
        if (v.getId() == R.id.dowayMasuraID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","doway_masura");
            intent.putExtra("title","দোয়ায়ে মাসুরা");
            startActivity(intent);


        }
        if (v.getId() == R.id.dorudShorifID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","dorud_shorif");
            intent.putExtra("title","দরূদ শরীফ");
            startActivity(intent);


        }
        if (v.getId() == R.id.jaynamazerDaranorDowaID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","jaynamazer_dowa");
            intent.putExtra("title","জায়নামাজের দোয়া");
            startActivity(intent);


        }
        if (v.getId() == R.id.rukuShejdarTasbihID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","ruku_sejdar_tasbih");
            intent.putExtra("title","রুকু ও সেজদার তাসবিহ");
            startActivity(intent);


        }
        if (v.getId() == R.id.salamAndMunajatID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","salam_munajat");
            intent.putExtra("title","সালাম ও মুনাজাত");
            startActivity(intent);


        }
        if (v.getId() == R.id.sanaID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","sana");
            intent.putExtra("title","সানা");
            startActivity(intent);


        }
        if (v.getId() == R.id.takbireTahrimaID) {
            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","takbire_tahrima");
            intent.putExtra("title","তাকবিরে তাহরিমা");
            startActivity(intent);


        }
        if (v.getId() == R.id.tasahudID) {

            Intent intent = new Intent(NamazerDowaMainActivity.this, ContentViewActivity.class);
            intent.putExtra("layoutName","tasahud");
            intent.putExtra("title","তাশাহুদ");
            startActivity(intent);

        }

    }
}