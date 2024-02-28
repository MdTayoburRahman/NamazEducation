package droidrocks.com.namaztimeapp.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public final class SpUtil {
    @NotNull
    public static final String PREFS_FILENAME = "app_prefs";

    @SuppressLint("StaticFieldLeak")
    @NotNull
    public static final SpUtil INSTANCE;

    static {
        SpUtil spUtil = new SpUtil();
        INSTANCE = spUtil;

    }

    public SpUtil() {
    }

    public void storeString(Context context, String key, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, text);
        editor.commit();
    }

    public void storeInteger(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void storeBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public String getString(Context context, String key, String def) {
        String uriPref = context.getSharedPreferences(PREFS_FILENAME, 0).getString(key, def);
        if (uriPref == null) {
            uriPref = "";
        }
        String text = uriPref;
        return text;
    }

    public int getInteger(Context context, String key, int def) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_FILENAME, 0);
        return sharedPref.getInt(key, def);
    }

    public boolean getBoolean(Context context, String key) {
        return context.getSharedPreferences(PREFS_FILENAME, 0).getBoolean(key, false);
    }


}