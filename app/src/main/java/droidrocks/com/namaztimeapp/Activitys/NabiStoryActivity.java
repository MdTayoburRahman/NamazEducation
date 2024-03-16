package droidrocks.com.namaztimeapp.Activitys;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import droidrocks.com.namaztimeapp.Adapters.NabiStoryAdapter;
import droidrocks.com.namaztimeapp.Models.StoryItem;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Utils.FileUtils;
import droidrocks.com.namaztimeapp.databinding.ActivityNabiStoryBinding;

public class NabiStoryActivity extends AppCompatActivity {


    private static final String TAG = "NabiStoryActivity";
    private ActivityNabiStoryBinding binding;
    private NabiStoryAdapter nabiStoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityNabiStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        topBar();
        populateRecyclerView();


    }

    private void populateRecyclerView() {
        try {
            // Read JSON object from assets
            JSONObject object = new JSONObject(Objects.requireNonNull(FileUtils
                    .loadJSONFromAsset(NabiStoryActivity.this, "nabi_story/jiboni.json")));

            // Check if the JSONObject is null
            if (object != null) {
                // If not null, proceed to retrieve the JSONArray
                JSONArray jsonArray = object.getJSONArray("jiboni");

                // Parse the JSON array into a list of StoryItems
                List<StoryItem> storyItemList = parseStoryItems(jsonArray);
                Log.d(TAG, "populateRecyclerView: "+storyItemList.size());
                // Set up RecyclerView with the parsed list of StoryItems
                binding.recyclerview.setLayoutManager(new GridLayoutManager(NabiStoryActivity.this, 2));
                nabiStoryAdapter = new NabiStoryAdapter(NabiStoryActivity.this, storyItemList);
                binding.recyclerview.setAdapter(nabiStoryAdapter);
            } else {
                // Handle case where JSON object is null (file not found or unable to read)
                Log.e(TAG, "populateRecyclerView: JSONObject is null");
                // Show appropriate error message to the user or handle the situation accordingly
                // For example, display a Toast or log the error
            }
        } catch (Exception e) {
            Log.e(TAG, "populateRecyclerView: Error loading JSON", e);
            // Handle other exceptions, such as JSONException or IOException
            // For example, display a Toast or log the error
            throw new RuntimeException(e);
        }
    }


    private List<StoryItem> parseStoryItems(JSONArray jsonArray) {
        Gson gson = new Gson();
        List<StoryItem> storyItemList = gson.fromJson(jsonArray.toString(),
                new com.google.gson.reflect.TypeToken<List<StoryItem>>(){}.getType());
        return storyItemList;
    }

    private void topBar() {
        MaterialToolbar topAppBar = binding.materialToolbar;
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }
}