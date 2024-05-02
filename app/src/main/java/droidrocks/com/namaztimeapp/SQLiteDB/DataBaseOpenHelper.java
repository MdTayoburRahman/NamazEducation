package droidrocks.com.namaztimeapp.SQLiteDB;

import android.content.Context;

import droidrocks.com.namaztimeapp.SQLiteAssetHelperLibrary.SQLiteAssetHelper;


public class DataBaseOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "ruqyah.db";
    private static final int DATABASE_VERSION = 2;


    public DataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null,  DATABASE_VERSION);
    }
}
