package droidrocks.com.namaztimeapp.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import droidrocks.com.namaztimeapp.Adapters.RuqyahContentAdapter;
import droidrocks.com.namaztimeapp.Models.RuqyahAyahEntity;
import droidrocks.com.namaztimeapp.Models.RuqyahGroupEntity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.SQLiteDB.RuqyahDataAccess;
import droidrocks.com.namaztimeapp.databinding.ActivityRuqyahContentBinding;

public class RuqyahContentActivity extends AppCompatActivity {

    private ActivityRuqyahContentBinding binding;
    private static final String TAG = "RuqyahContentActivity";
    private List<RuqyahAyahEntity> ruqyahAyahEntityList;
    private RuqyahContentAdapter ruqyahContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRuqyahContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the Intent that started this activity
        Intent intent = getIntent();
        // Retrieve the RuqyahGroupEntity object from the Intent
        RuqyahGroupEntity ruqyahGroup = intent.getParcelableExtra("ruqyah_group");
        fetchData(ruqyahGroup);
        topBar(ruqyahGroup);
    }

    private void fetchData(RuqyahGroupEntity ruqyahGroup) {
        RuqyahDataAccess dataAccess = RuqyahDataAccess.getInstance(RuqyahContentActivity.this);
        dataAccess.open();
        ruqyahAyahEntityList = dataAccess.getRuqyahAyahListByGroupId(ruqyahGroup);
        if (ruqyahAyahEntityList == null) {
            Log.d(TAG, "fetchData: empty list");
            return;
        }
        populateRecyclerView(ruqyahAyahEntityList);


    }

    private void populateRecyclerView(List<RuqyahAyahEntity> ruqyahAyahEntityList) {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(RuqyahContentActivity.this));
        ruqyahContentAdapter = new RuqyahContentAdapter(RuqyahContentActivity.this, ruqyahAyahEntityList);
        binding.recyclerview.setAdapter(ruqyahContentAdapter);
    }

    private void topBar(RuqyahGroupEntity ruqyahGroup) {
        MaterialToolbar topAppBar = binding.materialToolbar;
        topAppBar.setTitle(ruqyahGroup.getSubtitle());
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }
}