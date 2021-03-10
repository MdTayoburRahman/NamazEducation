package droidrocks.com.namaztimeapp.NamazShikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;

import droidrocks.com.namaztimeapp.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class NamazPoddotiMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namaz_poddoti_main);
        topBar();
        init();

    }

    private void init() {
        MaterialCardView fourRakat,twoRakat,threeRakat,nofol,kaja,betor;

        fourRakat = findViewById(R.id.fourRakat_namaz_button);
        twoRakat = findViewById(R.id.twoRakat_namaz_button);
        threeRakat = findViewById(R.id.threeRakat_namaz_button);
        nofol = findViewById(R.id.nofol_namaz_button);
        kaja = findViewById(R.id.kaja_namaz_button);
        betor = findViewById(R.id.betorer_namaz_button);


        twoRakat.setOnClickListener(this);
        threeRakat.setOnClickListener(this);
        fourRakat.setOnClickListener(this);
        nofol.setOnClickListener(this);
        kaja.setOnClickListener(this);
        betor.setOnClickListener(this);
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

        if (v.getId()==R.id.fourRakat_namaz_button){

            Intent intent = new Intent(NamazPoddotiMainActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","four_rakat");
            intent.putExtra("title","৪ রাকাত সালাত");
            startActivity(intent);

        }
        if (v.getId()==R.id.threeRakat_namaz_button){

            Intent intent = new Intent(NamazPoddotiMainActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","three_rakat");
            intent.putExtra("title","৩ রাকাত সালাত");
            startActivity(intent);

        }
        if (v.getId()==R.id.twoRakat_namaz_button){

            Intent intent = new Intent(NamazPoddotiMainActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","two_rakat");
            intent.putExtra("title","২ রাকাত সালাত");
            startActivity(intent);
        }
        if (v.getId()==R.id.kaja_namaz_button){

            Intent intent = new Intent(NamazPoddotiMainActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","kaja");
            intent.putExtra("title","ক্বাযা সালাত");
            startActivity(intent);
        }
        if (v.getId()==R.id.nofol_namaz_button){

            Intent intent = new Intent(NamazPoddotiMainActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","nofol");
            intent.putExtra("title","নফল সালাত");
            startActivity(intent);
        }

        if (v.getId()==R.id.betorer_namaz_button){

            Intent intent = new Intent(NamazPoddotiMainActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","betor");
            intent.putExtra("title","বিতরের সালাত");
            startActivity(intent);
        }

    }
}