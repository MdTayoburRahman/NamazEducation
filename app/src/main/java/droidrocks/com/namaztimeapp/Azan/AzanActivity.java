package droidrocks.com.namaztimeapp.Azan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;



import droidrocks.com.namaztimeapp.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class AzanActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azan);
        topBar();
        RecyclerViewTask();



    }

    private void RecyclerViewTask() {

        RecyclerView recyclerView;

        String title[];

        recyclerView = findViewById(R.id.RecyclerView);

        title = getResources().getStringArray(R.array.azan_list);
        RecyclerView.LayoutManager mlayoutManager;

        mlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setHasFixedSize(true);
        AzanAdapter azanAdapter = new AzanAdapter(title, this);
        recyclerView.setAdapter(azanAdapter);

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



}