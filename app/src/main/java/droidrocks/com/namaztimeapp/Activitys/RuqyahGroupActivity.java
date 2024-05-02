package droidrocks.com.namaztimeapp.Activitys;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.Adapters.RuqyahGroupAdapter;
import droidrocks.com.namaztimeapp.Models.RuqyahGroupEntity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.SQLiteDB.RuqyahDataAccess;
import droidrocks.com.namaztimeapp.databinding.ActivityRuqyahGroupBinding;

public class RuqyahGroupActivity extends AppCompatActivity {

    private ActivityRuqyahGroupBinding binding;
    private static final String TAG = "RuqyahGroupActivity";
    private List<RuqyahGroupEntity> ruqyahGroupList;
    private RuqyahGroupAdapter ruqyahGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRuqyahGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        topBar();
        fetchRuqyahGroupList();
    }

    private void topBar() {
        MaterialToolbar topAppBar = binding.materialToolbar;
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }

    private void fetchRuqyahGroupList(){
        RuqyahDataAccess dataAccess = RuqyahDataAccess.getInstance(RuqyahGroupActivity.this);
        dataAccess.open();
        ruqyahGroupList = dataAccess.getRuqyahGroupList();
        if (ruqyahGroupList == null){
            Log.d(TAG, "fetchRuqyahGroupList: empty list");
            return;
        }
        populateRecyclerView(ruqyahGroupList);
    }

    private void populateRecyclerView(List<RuqyahGroupEntity> ruqyahGroupList) {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        ruqyahGroupAdapter = new RuqyahGroupAdapter(RuqyahGroupActivity.this, ruqyahGroupList);
        binding.recyclerview.setAdapter(ruqyahGroupAdapter);
    }
}