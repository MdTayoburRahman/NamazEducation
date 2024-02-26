package droidrocks.com.namaztimeapp.DB;

import android.content.Context;

import droidrocks.com.namaztimeapp.SQLiteAssetHelperLibrary.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "hadith.db";
    private static final int DATABASE_VERSION = 1;


    public DataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null,  DATABASE_VERSION);
    }
}
