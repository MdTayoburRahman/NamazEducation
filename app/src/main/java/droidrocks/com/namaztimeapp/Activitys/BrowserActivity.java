package droidrocks.com.namaztimeapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.databinding.ActivityBrowserBinding;

public class BrowserActivity extends AppCompatActivity {
    ActivityBrowserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBrowserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        topBar();
        binding.web.loadUrl("file:///android_asset/webassets/" + getIntent().getStringExtra("pageFileName"));

    }

    private void topBar() {
        MaterialToolbar topAppBar = binding.materialToolbar;
        topAppBar.setTitle(getIntent().getStringExtra("pageTitle"));
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }
}