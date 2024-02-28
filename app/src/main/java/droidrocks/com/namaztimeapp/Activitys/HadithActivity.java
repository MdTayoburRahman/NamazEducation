package droidrocks.com.namaztimeapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.databinding.ActivityHadithBinding;

public class HadithActivity extends AppCompatActivity {

    private ActivityHadithBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHadithBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}