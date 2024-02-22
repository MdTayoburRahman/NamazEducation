package droidrocks.com.namaztimeapp.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

import droidrocks.com.namaztimeapp.R;

public class AppUtils {
    static AlertDialog alertDialog = null;
    @SuppressLint("SetTextI18n")
    public static void showAboutAlert(Activity activity) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.about_app_layout, null);
        TextView appVersionText = view.findViewById(R.id.app_version);

        Button button = view.findViewById(R.id.closeBTN);
        Button emailButton = view.findViewById(R.id.sendEmailBTN);


        try {
            PackageInfo pInfo = null;
            pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            String version = pInfo.versionName;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String copyright = year + " ©Md Tayobur Rahman - All rights reserved";
            String website = "\n www.droidrocks.com \n tayoburrahman119@gmail.com";
            appVersionText.setText(copyright + website + "\n\n App Version - " + version);

        } catch (Exception e) {
            e.printStackTrace();
        }
        emailButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "tayoburrahman119@gmail.com", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding "+activity.getString(R.string.app_name)+" App -");
            activity.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });


        button.setOnClickListener(view1 -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        });


        builder.setView(view);
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.show();

    }
}
