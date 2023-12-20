package droidrocks.com.namaztimeapp.Azan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import droidrocks.com.namaztimeapp.Activitys.AboutActivity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Tools.AppInternetStatus;
import droidrocks.com.namaztimeapp.Tools.InternetCheckActivity;

import static com.google.android.exoplayer2.offline.DownloadHelper.createMediaSource;

public class ExoPlayerActivity extends AppCompatActivity {

    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        checkInternet();
        topBar();


        Intent intent = getIntent();
        String Url = intent.getStringExtra("link");
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(Url));

        try {
            StyledPlayerView playerView = findViewById(R.id.player_view);
            // Instantiate the player.
            player = new SimpleExoPlayer.Builder(this).build();
            // Attach player to the view.
            playerView.setPlayer(player);
            // Set the media source to be played.
            player.setMediaItem(mediaItem);
            // Prepare the player.
            player.prepare();
            player.play();

        }catch (Exception e){
            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }

    @SuppressLint("WrongConstant")
    private void checkInternet() {
        if (AppInternetStatus.getInstance(this).isOnline()) {
            Log.d("TAG", "checkInternet: Internet Connected" );

        } else {
            Log.d("TAG", "checkInternet: Internet not Connected" );
            Intent Intent = new Intent(ExoPlayerActivity.this, InternetCheckActivity.class);
            startActivity(Intent);
            finish();

        }

    }
    private void topBar() {

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
        Toolbar topAppBar = findViewById(R.id.toolbar);

        topAppBar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.about) {
                player.stop();
                player.release();
                finish();
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                player.release();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        player.stop();
        player.release();
        super.onBackPressed();

    }
}