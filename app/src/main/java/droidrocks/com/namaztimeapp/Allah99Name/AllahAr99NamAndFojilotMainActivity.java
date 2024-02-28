package droidrocks.com.namaztimeapp.Allah99Name;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import droidrocks.com.namaztimeapp.Activitys.MainActivity;
import droidrocks.com.namaztimeapp.R;
import droidrocks.com.namaztimeapp.Utils.AppUtils;

public class AllahAr99NamAndFojilotMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button a1, a2, a3, a4, a5, a6, a7, a8, a9, a10,
            a11, a12, a13, a14, a15, a16, a17, a18, a19, a20,
            a21, a22, a23, a24, a25, a26, a27, a28, a29, a30,
            a31, a32, a33, a34, a35, a36, a37, a38, a39, a40,
            a41, a42, a43, a44, a45, a46, a47, a48, a49, a50,
            a51, a52, a53, a54, a55, a56, a57, a58, a59, a60,
            a61, a62, a63, a64, a65, a66, a67, a68, a69, a70,
            a71, a72, a73, a74, a75, a76, a77, a78, a79, a80,
            a81, a82, a83, a84, a85, a86, a87, a88, a89, a90,
            a91, a92, a93, a94, a95, a96, a97, a98, a99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allah_ar99_nam_and_fojilot_main);
        topBar();
        init();


    }

    private void init() {

        // from 1 to 10
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);
        a9 = findViewById(R.id.a9);
        a10 = findViewById(R.id.a10);

        //from 11 to 20                     from 20-30                      from 30 - 40
        a11 = findViewById(R.id.a11);
        a21 = findViewById(R.id.a21);
        a31 = findViewById(R.id.a31);
        a12 = findViewById(R.id.a12);
        a22 = findViewById(R.id.a22);
        a32 = findViewById(R.id.a32);
        a13 = findViewById(R.id.a13);
        a23 = findViewById(R.id.a23);
        a33 = findViewById(R.id.a33);
        a14 = findViewById(R.id.a14);
        a24 = findViewById(R.id.a24);
        a34 = findViewById(R.id.a34);
        a15 = findViewById(R.id.a15);
        a25 = findViewById(R.id.a25);
        a35 = findViewById(R.id.a35);
        a16 = findViewById(R.id.a16);
        a26 = findViewById(R.id.a26);
        a36 = findViewById(R.id.a36);
        a17 = findViewById(R.id.a17);
        a27 = findViewById(R.id.a27);
        a37 = findViewById(R.id.a37);
        a18 = findViewById(R.id.a18);
        a28 = findViewById(R.id.a28);
        a38 = findViewById(R.id.a38);
        a19 = findViewById(R.id.a19);
        a29 = findViewById(R.id.a29);
        a39 = findViewById(R.id.a39);
        a20 = findViewById(R.id.a20);
        a30 = findViewById(R.id.a30);
        a40 = findViewById(R.id.a40);


        a41 = findViewById(R.id.a41);
        a51 = findViewById(R.id.a51);
        a61 = findViewById(R.id.a61);
        a42 = findViewById(R.id.a42);
        a52 = findViewById(R.id.a52);
        a62 = findViewById(R.id.a62);
        a43 = findViewById(R.id.a43);
        a53 = findViewById(R.id.a53);
        a63 = findViewById(R.id.a63);
        a44 = findViewById(R.id.a44);
        a54 = findViewById(R.id.a54);
        a64 = findViewById(R.id.a64);
        a45 = findViewById(R.id.a45);
        a55 = findViewById(R.id.a55);
        a65 = findViewById(R.id.a65);
        a46 = findViewById(R.id.a46);
        a56 = findViewById(R.id.a56);
        a66 = findViewById(R.id.a66);
        a47 = findViewById(R.id.a47);
        a57 = findViewById(R.id.a57);
        a67 = findViewById(R.id.a67);
        a48 = findViewById(R.id.a48);
        a58 = findViewById(R.id.a58);
        a68 = findViewById(R.id.a68);
        a49 = findViewById(R.id.a49);
        a59 = findViewById(R.id.a59);
        a69 = findViewById(R.id.a69);
        a50 = findViewById(R.id.a50);
        a60 = findViewById(R.id.a60);
        a70 = findViewById(R.id.a70);


        a71 = findViewById(R.id.a71);
        a81 = findViewById(R.id.a81);
        a91 = findViewById(R.id.a91);
        a72 = findViewById(R.id.a72);
        a82 = findViewById(R.id.a82);
        a92 = findViewById(R.id.a92);
        a73 = findViewById(R.id.a73);
        a83 = findViewById(R.id.a83);
        a93 = findViewById(R.id.a93);
        a74 = findViewById(R.id.a74);
        a84 = findViewById(R.id.a84);
        a94 = findViewById(R.id.a94);
        a75 = findViewById(R.id.a75);
        a85 = findViewById(R.id.a85);
        a95 = findViewById(R.id.a95);
        a76 = findViewById(R.id.a76);
        a86 = findViewById(R.id.a86);
        a96 = findViewById(R.id.a96);
        a77 = findViewById(R.id.a77);
        a87 = findViewById(R.id.a87);
        a97 = findViewById(R.id.a97);
        a78 = findViewById(R.id.a78);
        a88 = findViewById(R.id.a88);
        a98 = findViewById(R.id.a98);
        a79 = findViewById(R.id.a79);
        a89 = findViewById(R.id.a89);
        a99 = findViewById(R.id.a99);
        a80 = findViewById(R.id.a80);
        a90 = findViewById(R.id.a90);


        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        a5.setOnClickListener(this);
        a6.setOnClickListener(this);
        a7.setOnClickListener(this);
        a8.setOnClickListener(this);
        a9.setOnClickListener(this);
        a10.setOnClickListener(this);


        a11.setOnClickListener(this);
        a21.setOnClickListener(this);
        a31.setOnClickListener(this);
        a12.setOnClickListener(this);
        a22.setOnClickListener(this);
        a32.setOnClickListener(this);
        a13.setOnClickListener(this);
        a23.setOnClickListener(this);
        a33.setOnClickListener(this);
        a14.setOnClickListener(this);
        a24.setOnClickListener(this);
        a34.setOnClickListener(this);
        a15.setOnClickListener(this);
        a25.setOnClickListener(this);
        a35.setOnClickListener(this);
        a16.setOnClickListener(this);
        a26.setOnClickListener(this);
        a36.setOnClickListener(this);
        a17.setOnClickListener(this);
        a27.setOnClickListener(this);
        a37.setOnClickListener(this);
        a18.setOnClickListener(this);
        a28.setOnClickListener(this);
        a38.setOnClickListener(this);
        a19.setOnClickListener(this);
        a29.setOnClickListener(this);
        a39.setOnClickListener(this);
        a20.setOnClickListener(this);
        a30.setOnClickListener(this);
        a40.setOnClickListener(this);


        a41.setOnClickListener(this);
        a51.setOnClickListener(this);
        a61.setOnClickListener(this);
        a42.setOnClickListener(this);
        a52.setOnClickListener(this);
        a62.setOnClickListener(this);
        a43.setOnClickListener(this);
        a53.setOnClickListener(this);
        a63.setOnClickListener(this);
        a44.setOnClickListener(this);
        a54.setOnClickListener(this);
        a64.setOnClickListener(this);
        a45.setOnClickListener(this);
        a55.setOnClickListener(this);
        a65.setOnClickListener(this);
        a46.setOnClickListener(this);
        a56.setOnClickListener(this);
        a66.setOnClickListener(this);
        a47.setOnClickListener(this);
        a57.setOnClickListener(this);
        a67.setOnClickListener(this);
        a48.setOnClickListener(this);
        a58.setOnClickListener(this);
        a68.setOnClickListener(this);
        a49.setOnClickListener(this);
        a59.setOnClickListener(this);
        a69.setOnClickListener(this);
        a50.setOnClickListener(this);
        a60.setOnClickListener(this);
        a70.setOnClickListener(this);


        a71.setOnClickListener(this);
        a81.setOnClickListener(this);
        a91.setOnClickListener(this);
        a72.setOnClickListener(this);
        a82.setOnClickListener(this);
        a92.setOnClickListener(this);
        a73.setOnClickListener(this);
        a83.setOnClickListener(this);
        a93.setOnClickListener(this);
        a74.setOnClickListener(this);
        a84.setOnClickListener(this);
        a94.setOnClickListener(this);
        a75.setOnClickListener(this);
        a85.setOnClickListener(this);
        a95.setOnClickListener(this);
        a76.setOnClickListener(this);
        a86.setOnClickListener(this);
        a96.setOnClickListener(this);
        a77.setOnClickListener(this);
        a87.setOnClickListener(this);
        a97.setOnClickListener(this);
        a78.setOnClickListener(this);
        a88.setOnClickListener(this);
        a98.setOnClickListener(this);
        a79.setOnClickListener(this);
        a89.setOnClickListener(this);
        a99.setOnClickListener(this);
        a80.setOnClickListener(this);
        a90.setOnClickListener(this);


    }

    private void topBar() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //  Fixed Portrait orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove the notifcation bar
        Toolbar topAppBar = findViewById(R.id.toolbar);

        topAppBar.setOnMenuItemClickListener(item -> {


            return false;
        });

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.a1) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "0");
            intent.putExtra("nameBangla", "0");
            intent.putExtra("nameOrtho", "0");
            intent.putExtra("namedefination", "0");

            startActivity(intent);

        }
        if (v.getId() == R.id.a2) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "1");
            intent.putExtra("nameBangla", "1");
            intent.putExtra("nameOrtho", "1");
            intent.putExtra("namedefination", "1");

            startActivity(intent);

        }
        if (v.getId() == R.id.a3) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "2");
            intent.putExtra("nameBangla", "2");
            intent.putExtra("nameOrtho", "2");
            intent.putExtra("namedefination", "2");
            startActivity(intent);

        }
        if (v.getId() == R.id.a4) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "3");
            intent.putExtra("nameBangla", "3");
            intent.putExtra("nameOrtho", "3");
            intent.putExtra("namedefination", "3");

            startActivity(intent);

        }
        if (v.getId() == R.id.a5) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "4");
            intent.putExtra("nameBangla", "4");
            intent.putExtra("nameOrtho", "4");
            intent.putExtra("namedefination", "4");

            startActivity(intent);

        }
        if (v.getId() == R.id.a6) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "5");
            intent.putExtra("nameBangla", "5");
            intent.putExtra("nameOrtho", "5");
            intent.putExtra("namedefination", "5");

            startActivity(intent);

        }
        if (v.getId() == R.id.a7) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "6");
            intent.putExtra("nameBangla", "6");
            intent.putExtra("nameOrtho", "6");
            intent.putExtra("namedefination", "6");

            startActivity(intent);

        }
        if (v.getId() == R.id.a8) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "7");
            intent.putExtra("nameBangla", "7");
            intent.putExtra("nameOrtho", "7");
            intent.putExtra("namedefination", "7");

            startActivity(intent);

        }
        if (v.getId() == R.id.a9) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "8");
            intent.putExtra("nameBangla", "8");
            intent.putExtra("nameOrtho", "8");
            intent.putExtra("namedefination", "8");

            startActivity(intent);

        }
        if (v.getId() == R.id.a10) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "9");
            intent.putExtra("nameBangla", "9");
            intent.putExtra("nameOrtho", "9");
            intent.putExtra("namedefination", "9");

            startActivity(intent);

        }

        if (v.getId() == R.id.a11) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "10");
            intent.putExtra("nameBangla", "10");
            intent.putExtra("nameOrtho", "10");
            intent.putExtra("namedefination", "10");

            startActivity(intent);

        }
        if (v.getId() == R.id.a12) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "11");
            intent.putExtra("nameBangla", "11");
            intent.putExtra("nameOrtho", "11");
            intent.putExtra("namedefination", "11");

            startActivity(intent);

        }
        if (v.getId() == R.id.a13) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "12");
            intent.putExtra("nameBangla", "12");
            intent.putExtra("nameOrtho", "12");
            intent.putExtra("namedefination", "12");
            startActivity(intent);

        }
        if (v.getId() == R.id.a14) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "13");
            intent.putExtra("nameBangla", "13");
            intent.putExtra("nameOrtho", "13");
            intent.putExtra("namedefination", "13");

            startActivity(intent);

        }
        if (v.getId() == R.id.a15) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "14");
            intent.putExtra("nameBangla", "14");
            intent.putExtra("nameOrtho", "14");
            intent.putExtra("namedefination", "14");

            startActivity(intent);

        }
        if (v.getId() == R.id.a16) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "15");
            intent.putExtra("nameBangla", "15");
            intent.putExtra("nameOrtho", "15");
            intent.putExtra("namedefination", "15");

            startActivity(intent);

        }
        if (v.getId() == R.id.a17) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "16");
            intent.putExtra("nameBangla", "16");
            intent.putExtra("nameOrtho", "16");
            intent.putExtra("namedefination", "16");

            startActivity(intent);

        }
        if (v.getId() == R.id.a18) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "17");
            intent.putExtra("nameBangla", "17");
            intent.putExtra("nameOrtho", "17");
            intent.putExtra("namedefination", "17");

            startActivity(intent);

        }
        if (v.getId() == R.id.a19) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "18");
            intent.putExtra("nameBangla", "18");
            intent.putExtra("nameOrtho", "18");
            intent.putExtra("namedefination", "18");

            startActivity(intent);

        }
        if (v.getId() == R.id.a20) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "19");
            intent.putExtra("nameBangla", "19");
            intent.putExtra("nameOrtho", "19");
            intent.putExtra("namedefination", "19");

            startActivity(intent);

        }


        if (v.getId() == R.id.a21) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "20");
            intent.putExtra("nameBangla", "20");
            intent.putExtra("nameOrtho", "20");
            intent.putExtra("namedefination", "20");

            startActivity(intent);

        }
        if (v.getId() == R.id.a22) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "21");
            intent.putExtra("nameBangla", "21");
            intent.putExtra("nameOrtho", "21");
            intent.putExtra("namedefination", "21");

            startActivity(intent);

        }
        if (v.getId() == R.id.a23) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "22");
            intent.putExtra("nameBangla", "22");
            intent.putExtra("nameOrtho", "22");
            intent.putExtra("namedefination", "22");
            startActivity(intent);

        }
        if (v.getId() == R.id.a24) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "23");
            intent.putExtra("nameBangla", "23");
            intent.putExtra("nameOrtho", "23");
            intent.putExtra("namedefination", "23");

            startActivity(intent);

        }
        if (v.getId() == R.id.a25) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "24");
            intent.putExtra("nameBangla", "24");
            intent.putExtra("nameOrtho", "24");
            intent.putExtra("namedefination", "24");

            startActivity(intent);

        }
        if (v.getId() == R.id.a26) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "25");
            intent.putExtra("nameBangla", "25");
            intent.putExtra("nameOrtho", "25");
            intent.putExtra("namedefination", "25");

            startActivity(intent);

        }
        if (v.getId() == R.id.a27) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "26");
            intent.putExtra("nameBangla", "26");
            intent.putExtra("nameOrtho", "26");
            intent.putExtra("namedefination", "26");

            startActivity(intent);

        }
        if (v.getId() == R.id.a28) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "27");
            intent.putExtra("nameBangla", "27");
            intent.putExtra("nameOrtho", "27");
            intent.putExtra("namedefination", "27");

            startActivity(intent);

        }
        if (v.getId() == R.id.a29) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "28");
            intent.putExtra("nameBangla", "28");
            intent.putExtra("nameOrtho", "28");
            intent.putExtra("namedefination", "28");

            startActivity(intent);

        }
        if (v.getId() == R.id.a30) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "29");
            intent.putExtra("nameBangla", "29");
            intent.putExtra("nameOrtho", "29");
            intent.putExtra("namedefination", "29");

            startActivity(intent);

        }


        if (v.getId() == R.id.a31) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "30");
            intent.putExtra("nameBangla", "30");
            intent.putExtra("nameOrtho", "30");
            intent.putExtra("namedefination", "30");

            startActivity(intent);

        }
        if (v.getId() == R.id.a32) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "31");
            intent.putExtra("nameBangla", "31");
            intent.putExtra("nameOrtho", "31");
            intent.putExtra("namedefination", "31");

            startActivity(intent);

        }
        if (v.getId() == R.id.a33) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "32");
            intent.putExtra("nameBangla", "32");
            intent.putExtra("nameOrtho", "32");
            intent.putExtra("namedefination", "32");
            startActivity(intent);

        }
        if (v.getId() == R.id.a34) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "33");
            intent.putExtra("nameBangla", "33");
            intent.putExtra("nameOrtho", "33");
            intent.putExtra("namedefination", "33");

            startActivity(intent);

        }
        if (v.getId() == R.id.a35) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "34");
            intent.putExtra("nameBangla", "34");
            intent.putExtra("nameOrtho", "34");
            intent.putExtra("namedefination", "34");

            startActivity(intent);

        }
        if (v.getId() == R.id.a36) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "35");
            intent.putExtra("nameBangla", "35");
            intent.putExtra("nameOrtho", "35");
            intent.putExtra("namedefination", "35");

            startActivity(intent);

        }
        if (v.getId() == R.id.a37) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "36");
            intent.putExtra("nameBangla", "36");
            intent.putExtra("nameOrtho", "36");
            intent.putExtra("namedefination", "36");

            startActivity(intent);

        }
        if (v.getId() == R.id.a38) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "37");
            intent.putExtra("nameBangla", "37");
            intent.putExtra("nameOrtho", "37");
            intent.putExtra("namedefination", "37");

            startActivity(intent);

        }
        if (v.getId() == R.id.a39) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "38");
            intent.putExtra("nameBangla", "38");
            intent.putExtra("nameOrtho", "38");
            intent.putExtra("namedefination", "38");

            startActivity(intent);

        }
        if (v.getId() == R.id.a40) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "39");
            intent.putExtra("nameBangla", "39");
            intent.putExtra("nameOrtho", "39");
            intent.putExtra("namedefination", "39");

            startActivity(intent);

        }


        if (v.getId() == R.id.a41) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "40");
            intent.putExtra("nameBangla", "40");
            intent.putExtra("nameOrtho", "40");
            intent.putExtra("namedefination", "40");

            startActivity(intent);

        }
        if (v.getId() == R.id.a42) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "41");
            intent.putExtra("nameBangla", "41");
            intent.putExtra("nameOrtho", "41");
            intent.putExtra("namedefination", "41");

            startActivity(intent);

        }
        if (v.getId() == R.id.a43) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "42");
            intent.putExtra("nameBangla", "42");
            intent.putExtra("nameOrtho", "42");
            intent.putExtra("namedefination", "42");
            startActivity(intent);

        }
        if (v.getId() == R.id.a44) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "43");
            intent.putExtra("nameBangla", "43");
            intent.putExtra("nameOrtho", "43");
            intent.putExtra("namedefination", "43");

            startActivity(intent);

        }
        if (v.getId() == R.id.a45) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "44");
            intent.putExtra("nameBangla", "44");
            intent.putExtra("nameOrtho", "44");
            intent.putExtra("namedefination", "44");

            startActivity(intent);

        }
        if (v.getId() == R.id.a46) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "45");
            intent.putExtra("nameBangla", "45");
            intent.putExtra("nameOrtho", "45");
            intent.putExtra("namedefination", "45");

            startActivity(intent);

        }
        if (v.getId() == R.id.a47) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "46");
            intent.putExtra("nameBangla", "46");
            intent.putExtra("nameOrtho", "46");
            intent.putExtra("namedefination", "46");

            startActivity(intent);

        }
        if (v.getId() == R.id.a48) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "47");
            intent.putExtra("nameBangla", "47");
            intent.putExtra("nameOrtho", "47");
            intent.putExtra("namedefination", "47");

            startActivity(intent);

        }
        if (v.getId() == R.id.a49) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "48");
            intent.putExtra("nameBangla", "48");
            intent.putExtra("nameOrtho", "48");
            intent.putExtra("namedefination", "48");

            startActivity(intent);

        }
        if (v.getId() == R.id.a50) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "49");
            intent.putExtra("nameBangla", "49");
            intent.putExtra("nameOrtho", "49");
            intent.putExtra("namedefination", "49");

            startActivity(intent);

        }


        if (v.getId() == R.id.a51) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "40");
            intent.putExtra("nameBangla", "40");
            intent.putExtra("nameOrtho", "40");
            intent.putExtra("namedefination", "40");

            startActivity(intent);

        }
        if (v.getId() == R.id.a52) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "41");
            intent.putExtra("nameBangla", "41");
            intent.putExtra("nameOrtho", "41");
            intent.putExtra("namedefination", "41");

            startActivity(intent);

        }
        if (v.getId() == R.id.a53) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "42");
            intent.putExtra("nameBangla", "42");
            intent.putExtra("nameOrtho", "42");
            intent.putExtra("namedefination", "42");
            startActivity(intent);

        }
        if (v.getId() == R.id.a54) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "43");
            intent.putExtra("nameBangla", "43");
            intent.putExtra("nameOrtho", "43");
            intent.putExtra("namedefination", "43");

            startActivity(intent);

        }
        if (v.getId() == R.id.a55) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "44");
            intent.putExtra("nameBangla", "44");
            intent.putExtra("nameOrtho", "44");
            intent.putExtra("namedefination", "44");

            startActivity(intent);

        }
        if (v.getId() == R.id.a56) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "45");
            intent.putExtra("nameBangla", "45");
            intent.putExtra("nameOrtho", "45");
            intent.putExtra("namedefination", "45");

            startActivity(intent);

        }
        if (v.getId() == R.id.a57) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "46");
            intent.putExtra("nameBangla", "46");
            intent.putExtra("nameOrtho", "46");
            intent.putExtra("namedefination", "46");

            startActivity(intent);

        }
        if (v.getId() == R.id.a58) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "47");
            intent.putExtra("nameBangla", "47");
            intent.putExtra("nameOrtho", "47");
            intent.putExtra("namedefination", "47");

            startActivity(intent);

        }
        if (v.getId() == R.id.a59) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "48");
            intent.putExtra("nameBangla", "48");
            intent.putExtra("nameOrtho", "48");
            intent.putExtra("namedefination", "48");

            startActivity(intent);

        }
        if (v.getId() == R.id.a60) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "49");
            intent.putExtra("nameBangla", "49");
            intent.putExtra("nameOrtho", "49");
            intent.putExtra("namedefination", "49");

            startActivity(intent);

        }


        if (v.getId() == R.id.a61) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "60");
            intent.putExtra("nameBangla", "60");
            intent.putExtra("nameOrtho", "60");
            intent.putExtra("namedefination", "60");

            startActivity(intent);

        }
        if (v.getId() == R.id.a62) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "61");
            intent.putExtra("nameBangla", "61");
            intent.putExtra("nameOrtho", "61");
            intent.putExtra("namedefination", "61");

            startActivity(intent);

        }
        if (v.getId() == R.id.a63) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "62");
            intent.putExtra("nameBangla", "62");
            intent.putExtra("nameOrtho", "62");
            intent.putExtra("namedefination", "62");
            startActivity(intent);

        }
        if (v.getId() == R.id.a64) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "63");
            intent.putExtra("nameBangla", "63");
            intent.putExtra("nameOrtho", "63");
            intent.putExtra("namedefination", "63");

            startActivity(intent);

        }
        if (v.getId() == R.id.a65) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "64");
            intent.putExtra("nameBangla", "64");
            intent.putExtra("nameOrtho", "64");
            intent.putExtra("namedefination", "64");

            startActivity(intent);

        }
        if (v.getId() == R.id.a66) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "65");
            intent.putExtra("nameBangla", "65");
            intent.putExtra("nameOrtho", "65");
            intent.putExtra("namedefination", "65");

            startActivity(intent);

        }
        if (v.getId() == R.id.a67) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "66");
            intent.putExtra("nameBangla", "66");
            intent.putExtra("nameOrtho", "66");
            intent.putExtra("namedefination", "66");

            startActivity(intent);

        }
        if (v.getId() == R.id.a68) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "67");
            intent.putExtra("nameBangla", "67");
            intent.putExtra("nameOrtho", "67");
            intent.putExtra("namedefination", "57");

            startActivity(intent);

        }
        if (v.getId() == R.id.a69) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "68");
            intent.putExtra("nameBangla", "68");
            intent.putExtra("nameOrtho", "68");
            intent.putExtra("namedefination", "68");

            startActivity(intent);

        }
        if (v.getId() == R.id.a70) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "69");
            intent.putExtra("nameBangla", "69");
            intent.putExtra("nameOrtho", "69");
            intent.putExtra("namedefination", "69");

            startActivity(intent);

        }


        if (v.getId() == R.id.a71) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "70");
            intent.putExtra("nameBangla", "70");
            intent.putExtra("nameOrtho", "70");
            intent.putExtra("namedefination", "70");

            startActivity(intent);

        }
        if (v.getId() == R.id.a72) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "71");
            intent.putExtra("nameBangla", "71");
            intent.putExtra("nameOrtho", "71");
            intent.putExtra("namedefination", "71");

            startActivity(intent);

        }
        if (v.getId() == R.id.a73) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "72");
            intent.putExtra("nameBangla", "72");
            intent.putExtra("nameOrtho", "72");
            intent.putExtra("namedefination", "72");
            startActivity(intent);

        }
        if (v.getId() == R.id.a74) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "73");
            intent.putExtra("nameBangla", "73");
            intent.putExtra("nameOrtho", "73");
            intent.putExtra("namedefination", "73");

            startActivity(intent);

        }
        if (v.getId() == R.id.a75) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "74");
            intent.putExtra("nameBangla", "74");
            intent.putExtra("nameOrtho", "74");
            intent.putExtra("namedefination", "74");

            startActivity(intent);

        }
        if (v.getId() == R.id.a76) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "75");
            intent.putExtra("nameBangla", "75");
            intent.putExtra("nameOrtho", "75");
            intent.putExtra("namedefination", "75");

            startActivity(intent);

        }
        if (v.getId() == R.id.a77) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "76");
            intent.putExtra("nameBangla", "76");
            intent.putExtra("nameOrtho", "76");
            intent.putExtra("namedefination", "76");

            startActivity(intent);

        }
        if (v.getId() == R.id.a78) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "77");
            intent.putExtra("nameBangla", "77");
            intent.putExtra("nameOrtho", "77");
            intent.putExtra("namedefination", "77");

            startActivity(intent);

        }
        if (v.getId() == R.id.a79) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "78");
            intent.putExtra("nameBangla", "78");
            intent.putExtra("nameOrtho", "78");
            intent.putExtra("namedefination", "78");

            startActivity(intent);

        }
        if (v.getId() == R.id.a80) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "79");
            intent.putExtra("nameBangla", "79");
            intent.putExtra("nameOrtho", "79");
            intent.putExtra("namedefination", "79");

            startActivity(intent);

        }


        if (v.getId() == R.id.a81) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "80");
            intent.putExtra("nameBangla", "80");
            intent.putExtra("nameOrtho", "80");
            intent.putExtra("namedefination", "80");

            startActivity(intent);

        }
        if (v.getId() == R.id.a82) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "81");
            intent.putExtra("nameBangla", "81");
            intent.putExtra("nameOrtho", "81");
            intent.putExtra("namedefination", "81");

            startActivity(intent);

        }
        if (v.getId() == R.id.a83) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "82");
            intent.putExtra("nameBangla", "82");
            intent.putExtra("nameOrtho", "82");
            intent.putExtra("namedefination", "82");
            startActivity(intent);

        }
        if (v.getId() == R.id.a84) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "73");
            intent.putExtra("nameBangla", "73");
            intent.putExtra("nameOrtho", "73");
            intent.putExtra("namedefination", "73");

            startActivity(intent);

        }
        if (v.getId() == R.id.a85) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "84");
            intent.putExtra("nameBangla", "84");
            intent.putExtra("nameOrtho", "84");
            intent.putExtra("namedefination", "84");

            startActivity(intent);

        }
        if (v.getId() == R.id.a86) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "85");
            intent.putExtra("nameBangla", "85");
            intent.putExtra("nameOrtho", "85");
            intent.putExtra("namedefination", "85");

            startActivity(intent);

        }
        if (v.getId() == R.id.a87) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "86");
            intent.putExtra("nameBangla", "86");
            intent.putExtra("nameOrtho", "86");
            intent.putExtra("namedefination", "86");

            startActivity(intent);

        }
        if (v.getId() == R.id.a88) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "87");
            intent.putExtra("nameBangla", "87");
            intent.putExtra("nameOrtho", "87");
            intent.putExtra("namedefination", "87");

            startActivity(intent);

        }
        if (v.getId() == R.id.a89) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "88");
            intent.putExtra("nameBangla", "88");
            intent.putExtra("nameOrtho", "88");
            intent.putExtra("namedefination", "88");

            startActivity(intent);

        }
        if (v.getId() == R.id.a90) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "89");
            intent.putExtra("nameBangla", "89");
            intent.putExtra("nameOrtho", "89");
            intent.putExtra("namedefination", "89");

            startActivity(intent);

        }

        if (v.getId() == R.id.a91) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "90");
            intent.putExtra("nameBangla", "90");
            intent.putExtra("nameOrtho", "90");
            intent.putExtra("namedefination", "90");

            startActivity(intent);

        }
        if (v.getId() == R.id.a92) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "91");
            intent.putExtra("nameBangla", "91");
            intent.putExtra("nameOrtho", "91");
            intent.putExtra("namedefination", "91");

            startActivity(intent);

        }
        if (v.getId() == R.id.a93) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "92");
            intent.putExtra("nameBangla", "92");
            intent.putExtra("nameOrtho", "92");
            intent.putExtra("namedefination", "92");
            startActivity(intent);

        }
        if (v.getId() == R.id.a94) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "93");
            intent.putExtra("nameBangla", "93");
            intent.putExtra("nameOrtho", "93");
            intent.putExtra("namedefination", "93");

            startActivity(intent);

        }
        if (v.getId() == R.id.a95) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "94");
            intent.putExtra("nameBangla", "94");
            intent.putExtra("nameOrtho", "94");
            intent.putExtra("namedefination", "94");

            startActivity(intent);

        }
        if (v.getId() == R.id.a96) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "95");
            intent.putExtra("nameBangla", "95");
            intent.putExtra("nameOrtho", "95");
            intent.putExtra("namedefination", "95");

            startActivity(intent);

        }
        if (v.getId() == R.id.a97) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "96");
            intent.putExtra("nameBangla", "96");
            intent.putExtra("nameOrtho", "96");
            intent.putExtra("namedefination", "96");

            startActivity(intent);

        }
        if (v.getId() == R.id.a98) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "97");
            intent.putExtra("nameBangla", "97");
            intent.putExtra("nameOrtho", "97");
            intent.putExtra("namedefination", "97");

            startActivity(intent);

        }
        if (v.getId() == R.id.a99) {

            Intent intent = new Intent(AllahAr99NamAndFojilotMainActivity.this, AllahMainActivity.class);
            intent.putExtra("nameArabic", "98");
            intent.putExtra("nameBangla", "98");
            intent.putExtra("nameOrtho", "98");
            intent.putExtra("namedefination", "98");

            startActivity(intent);

        }
    }
}