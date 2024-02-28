package droidrocks.com.namaztimeapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.bumptech.glide.Glide;

import droidrocks.com.namaztimeapp.Api.OnPostClickListener;
import droidrocks.com.namaztimeapp.Api.PostDataModel;
import droidrocks.com.namaztimeapp.databinding.ActivityPostViewBinding;

public class PostViewActivity extends AppCompatActivity  {

    ActivityPostViewBinding binding;
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.postTitle.setText(getIntent().getStringExtra("title"));
        binding.dateOfPost.setText(getIntent().getStringExtra("date"));
        String content = "<html><body>"+getIntent().getStringExtra("content")+" </body></html>";
        content = content.replaceAll("<a[^>]*>([^<]+)</a>", "");
        binding.webView.getSettings().setJavaScriptEnabled(true);
        //binding.webView.loadData(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY).toString(), "text/html", "UTF-8");
      binding.webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);





    }

}