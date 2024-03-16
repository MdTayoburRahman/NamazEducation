package droidrocks.com.namaztimeapp.Activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import droidrocks.com.namaztimeapp.Models.StoryItem;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Utils.FileUtils;
import droidrocks.com.namaztimeapp.databinding.ActivityNabiStoryDetailsBinding;

public class NabiStoryDetailsActivity extends AppCompatActivity {

    public static final String TAG = "NabiStoryDetailsActivity";
    private ActivityNabiStoryDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityNabiStoryDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        topBar();
        // Retrieve Parcelable data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            StoryItem storyItem = intent.getParcelableExtra("storyItem");
            if (storyItem != null) {
                // Use the retrieved StoryItem object
                String title = storyItem.getTitle();
                binding.materialToolbar.setTitle(title);
                binding.title.setText(title);
                String textFile = storyItem.getText_file();
                binding.content.setText(FileUtils.readTextFromAssets(NabiStoryDetailsActivity.this,"nabi_story/"+textFile));

                Glide.with(NabiStoryDetailsActivity.this)
                        .load("file:///android_asset/nabi_story/" + storyItem.getImage()) // Path to the image in assets folder
                        .placeholder(R.drawable.placeholder_image) // Placeholder image while loading (optional)
                        .error(R.drawable.placeholder_image) // Error image if Glide fails to load (optional)
                        .into(binding.image);
            } else {
                // Handle case where Parcelable data is null
            }
        } else {
            // Handle case where Intent is null
        }


    }

    private void topBar() {
        MaterialToolbar topAppBar = binding.materialToolbar;
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }
}