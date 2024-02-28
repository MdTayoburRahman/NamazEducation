package droidrocks.com.namaztimeapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import droidrocks.com.namaztimeapp.Api.BlogPostAdapter;
import droidrocks.com.namaztimeapp.Api.PostDataModel;
import droidrocks.com.namaztimeapp.Api.PostViewModel;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Utils.AppUtils;
import droidrocks.com.namaztimeapp.databinding.ActivityBlogPostBinding;

public class BlogPostActivity extends AppCompatActivity {

    private ActivityBlogPostBinding binding;
    private BlogPostAdapter blogPostAdapter;
    private PostViewModel viewModel;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlogPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationOnClickListener(v -> finish());
        if (AppUtils.checkInternet(BlogPostActivity.this)){
            fatchData();
        }

    }
    private void fatchData() {
        shimmerFrameLayout = binding.ShimmerFrameLayout;
        if (!shimmerFrameLayout.isShimmerVisible()) {
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        shimmerFrameLayout.startShimmer();

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.getListMutableLiveData().observe(this, new Observer<List<PostDataModel>>() {
            @Override
            public void onChanged(List<PostDataModel> postDataModels) {
                if (postDataModels!=null){
                    populateRV(postDataModels);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //stop loading animation
                            shimmerFrameLayout.stopShimmer();
                            //hide the animation layout
                            shimmerFrameLayout.setVisibility(View.GONE);
                            //make visible recycler view
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                }

            }
        });

        viewModel.MakeVolleyApiCall(BlogPostActivity.this);

    }

    private void populateRV(List<PostDataModel> blogPostList) {
        recyclerView = binding.RecyclerView;
        if (blogPostList.size()>0){
            recyclerView.setLayoutManager(new GridLayoutManager(BlogPostActivity.this, 2));
            blogPostAdapter = new BlogPostAdapter(BlogPostActivity.this,blogPostList);
            recyclerView.setAdapter(blogPostAdapter);
        }

    }
}