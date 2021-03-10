package droidrocks.com.namaztimeapp.Allah99Name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import droidrocks.com.namaztimeapp.AboutActivity;
import droidrocks.com.namaztimeapp.R;

public class AllahMainActivity extends AppCompatActivity {

    TextView text_title1, text_title2, text_title3, text_title4;
    private Button copyButtonID, shareButtonID;
    private Toolbar topAppBar;


    String[] allah_nintynine_name_arabic;
    String[] allah_nintynine_name_bangla;
    String[] allah_nintynine_name_ortho;
    String [] allah_nintynine_name_defination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allah_main);
        topBar();
        init();

    }

    private void init() {
        allah_nintynine_name_arabic = getResources().getStringArray(R.array.allah_nintynine_name_arabic);
        allah_nintynine_name_bangla = getResources().getStringArray(R.array.allah_nintynine_name_bangla);
        allah_nintynine_name_ortho = getResources().getStringArray(R.array.allah_nintynine_name_ortho);
        allah_nintynine_name_defination = getResources().getStringArray(R.array.allah_nintynine_name_defination);


        text_title1 = findViewById(R.id.text_title1);
        text_title2 = findViewById(R.id.text_title2);
        text_title3 = findViewById(R.id.text_title3);
        text_title4 = findViewById(R.id.text_title4);

        copyButtonID = findViewById(R.id.copyButtonID);
        shareButtonID = findViewById(R.id.shareButtonID);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String value1 = bundle.getString("nameArabic");
            String value2 = bundle.getString("nameBangla");
            String value3 = bundle.getString("nameOrtho");
            String value4 = bundle.getString("namedefination");


            topAppBar.setTitle(allah_nintynine_name_arabic[Integer.parseInt(value1)] + " " + allah_nintynine_name_bangla[Integer.parseInt(value2)]);

            text_title1.setText(allah_nintynine_name_arabic[Integer.parseInt(value1)]);
            text_title2.setText(allah_nintynine_name_bangla[Integer.parseInt(value2)]);
            text_title3.setText(allah_nintynine_name_ortho[Integer.parseInt(value3)]);
            text_title4.setText(allah_nintynine_name_defination[Integer.parseInt(value4)]);




        } else {
            Toast.makeText(this, "No value", Toast.LENGTH_SHORT).show();
        }

        copyButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Result = text_title1.getText().toString() + "\n" + text_title2.getText().toString() + "\n" + text_title3.getText().toString() + "\n"+text_title4.getText().toString() +"\n download - Namaz Time App for more information";

                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);

                ClipData clip = ClipData.newPlainText(" ", Result);
                clipboard.setPrimaryClip(clip);


                Toast toast = Toast.makeText(AllahMainActivity.this, "Copied", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();


            }
        });

        shareButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = text_title1.getText().toString()+ "\n" +"বাংলা উচ্চারণঃ"+ text_title2.getText().toString() + "\n" +"বাংলা অর্থঃ"+ text_title3.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(sharingIntent, "Share text via"));


            }
        });


    }

    private void topBar() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
        topAppBar = findViewById(R.id.toolbar);

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