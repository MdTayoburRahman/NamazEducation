package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.HadithEntity.HadithSource;

public class HadithSourceAccess {
    // Define table name and column names
    public static String TAG = HadithSourceAccess.class.getSimpleName();
    private static final String TABLE_SOURCE = "hadithsource";
    private static final String COL_SOURCE_ID = "SourceID";
    private static final String COL_SOURCE_NAME_BD = "SourceNameBD";
    private static final String COL_SOURCE_NAME_EN = "SourceNameEN";
    private static final String COL_SOURCE_ACTIVE = "SourceActive";

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithSourceAccess instance;

    // Constructor
    public HadithSourceAccess(Context context) {
        openHelper = new DataBaseOpenHelper(context);
    }

    // Singleton instance method
    public static synchronized HadithSourceAccess getInstance(Context context){
        if (instance == null){
            instance = new HadithSourceAccess(context);
        }
        return instance;
    }

    // Method to open database connection
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    // Method to close database connection
    public void close(){
        if (db != null){
            this.db.close();
        }
    }

    // Method to retrieve source by ID
    @SuppressLint("Range")
    public HadithSource getSourceById(int sourceId) {
        HadithSource source = null;
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SOURCE + " WHERE " + COL_SOURCE_ID + " = ?", new String[]{String.valueOf(sourceId)});
            if (cursor != null && cursor.moveToFirst()) {
                source = new HadithSource();
                source.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                source.setSourceNameBD(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_BD)));
                source.setSourceNameEN(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_EN)));
                source.setSourceActive(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ACTIVE))==1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting source by ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return source;
    }

    // Method to retrieve all sources
    @SuppressLint("Range")
    public List<HadithSource> getAllSources() {
        List<HadithSource> sourceList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SOURCE, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSource source = new HadithSource();
                    source.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    source.setSourceNameBD(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_BD)));
                    source.setSourceNameEN(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_EN)));
                    source.setSourceActive(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ACTIVE))==1);
                    sourceList.add(source);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting all sources: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sourceList;
    }

    // Method to retrieve sources by name in Bengali
    @SuppressLint("Range")
    public List<HadithSource> getSourceByNameBD(String nameBD) {
        List<HadithSource> sourceList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SOURCE + " WHERE " + COL_SOURCE_NAME_BD + " = ?", new String[]{nameBD});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSource source = new HadithSource();
                    source.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    source.setSourceNameBD(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_BD)));
                    source.setSourceNameEN(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_EN)));
                    source.setSourceActive(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ACTIVE))==1);
                    sourceList.add(source);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting sources by name in Bengali: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sourceList;
    }

    // Method to retrieve sources by name in English
    @SuppressLint("Range")
    public List<HadithSource> getSourceByNameEN(String nameEN) {
        List<HadithSource> sourceList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SOURCE + " WHERE " + COL_SOURCE_NAME_EN + " = ?", new String[]{nameEN});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSource source = new HadithSource();
                    source.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    source.setSourceNameBD(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_BD)));
                    source.setSourceNameEN(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_EN)));
                    source.setSourceActive(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ACTIVE))==1);
                    sourceList.add(source);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting sources by name in English: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sourceList;
    }

    // Method to retrieve all active sources
    @SuppressLint("Range")
    public List<HadithSource> getAllActiveSources() {
        List<HadithSource> sourceList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SOURCE + " WHERE " + COL_SOURCE_ACTIVE + " = ?", new String[]{"1"});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSource source = new HadithSource();
                    source.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    source.setSourceNameBD(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_BD)));
                    source.setSourceNameEN(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_EN)));
                    source.setSourceActive(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ACTIVE))==1);
                    sourceList.add(source);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting all active sources: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sourceList;
    }



    // Method to retrieve sources by their names in Bengali
    @SuppressLint("Range")
    public List<HadithSource> getSourcesByBengaliName(String name) {
        List<HadithSource> sourceList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SOURCE + " WHERE " + COL_SOURCE_NAME_BD + " LIKE ?", new String[]{"%" + name + "%"});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSource source = new HadithSource();
                    source.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    source.setSourceNameBD(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_BD)));
                    source.setSourceNameEN(cursor.getString(cursor.getColumnIndex(COL_SOURCE_NAME_EN)));
                    source.setSourceActive(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ACTIVE))==1);
                    sourceList.add(source);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting sources by Bengali name: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sourceList;
    }

    public int getTotalSourceCount() {
        int totalCount = 0;
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_SOURCE, null);
            if (cursor != null && cursor.moveToFirst()) {
                totalCount = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting total source count: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return totalCount;
    }




}
