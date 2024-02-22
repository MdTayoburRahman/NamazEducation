package droidrocks.com.namaztimeapp.NamazShikkha;

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

public class NamazerNiyotActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namazer_niyot);
        topBar();
        init();
    }

    private void init() {
        MaterialCardView fojor,juhor,asor,magrib,esha;

        fojor= findViewById(R.id.fojor_button);
        juhor= findViewById(R.id.juhor_button);
        asor= findViewById(R.id.asor_button);
        magrib= findViewById(R.id.magrib_button);
        esha= findViewById(R.id.esha_button);

        fojor.setOnClickListener(this);
        juhor.setOnClickListener(this);
        asor.setOnClickListener(this);
        magrib.setOnClickListener(this);
        esha.setOnClickListener(this);
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
        if (v.getId()==R.id.fojor_button){

            Intent intent = new Intent(NamazerNiyotActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","fojor");
            intent.putExtra("title","ফজরের সালাত");
            startActivity(intent);


        }
        if (v.getId()==R.id.juhor_button){
            Intent intent = new Intent(NamazerNiyotActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","zuhar");
            intent.putExtra("title","যোহরের সালাত");
            startActivity(intent);

        }
        if (v.getId()==R.id.asor_button){
            Intent intent = new Intent(NamazerNiyotActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","asr");
            intent.putExtra("title","আসরের সালাত");
            startActivity(intent);

        }
        if (v.getId()==R.id.magrib_button){
            Intent intent = new Intent(NamazerNiyotActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","magrib");
            intent.putExtra("title","মাগরিবের সালাত");
            startActivity(intent);

        }
        if (v.getId()==R.id.esha_button){
            Intent intent = new Intent(NamazerNiyotActivity.this,ContentViewActivity.class);
            intent.putExtra("layoutName","isha");
            intent.putExtra("title","এশার সালাত");
            startActivity(intent);

        }

    }
}