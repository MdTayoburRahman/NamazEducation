package droidrocks.com.namaztimeapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import droidrocks.com.namaztimeapp.R;

public class TasbihActivity extends AppCompatActivity {

    TextView restultTextView;
    int counter;
    int resetdefault = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);
        topBar();
        init();

    }

    private void init() {

        Button addButton,resetButton;


        addButton= findViewById(R.id.addButton);
        resetButton = findViewById(R.id.resetButton);
        restultTextView = findViewById(R.id.countTextView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCounter();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reSetCounter();
            }
        });

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

    private void reSetCounter(){

        counter = 0;
        restultTextView.setText("00"+counter);
    }

    private void addCounter(){

        if (counter==resetdefault){

            counter = 0;
            restultTextView.setText("0"+counter);

        }else {

            counter = counter+1;
            restultTextView.setText(""+counter);

        }


    }
}