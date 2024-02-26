package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HadithBookMetaAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithBookMetaAccess instance;

    private static final String TABLE_BOOK_META = "hadith_book_meta";
    private static final String COL_ROW_ID = "row_id";
    private static final String COL_BOOK_ID = "book_id";
    private static final String COL_META_KEY = "meta_key";
    private static final String COL_META_DATA = "meta_data";

    public HadithBookMetaAccess(Context context) {
        openHelper = new DataBaseOpenHelper(context);
    }

    public static synchronized HadithBookMetaAccess getInstance(Context context) {
        if (instance == null) {
            instance = new HadithBookMetaAccess(context.getApplicationContext());
        }
        return instance;
    }

    public void open() throws SQLException {
        db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }


    // Method to get meta data for a specific book and key
    @SuppressLint("Range")
    public String getBookMetaData(String bookId, String metaKey) {
        String metaData = null;
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_BOOK_META, new String[]{COL_META_DATA},
                    COL_BOOK_ID + " = ? AND " + COL_META_KEY + " = ?", new String[]{bookId, metaKey},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                metaData = cursor.getString(cursor.getColumnIndex(COL_META_DATA));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return metaData;
    }

    // Method to get a list of all meta data for a specific book
    public List<String> getAllBookMetaData(String bookId) {
        List<String> metaDataList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_BOOK_META, new String[]{COL_META_KEY, COL_META_DATA},
                    COL_BOOK_ID + " = ?", new String[]{bookId},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range")
                    String metaData = cursor.getString(cursor.getColumnIndex(COL_META_KEY)) +
                            ": " + cursor.getString(cursor.getColumnIndex(COL_META_DATA));
                    metaDataList.add(metaData);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return metaDataList;
    }

    // Method to search for a specific meta data by key for a given book
    @SuppressLint("Range")
    public String searchBookMetaData(String bookId, String searchKey) {
        String metaData = null;
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_BOOK_META, new String[]{COL_META_DATA},
                    COL_BOOK_ID + " = ? AND " + COL_META_KEY + " LIKE ?",
                    new String[]{bookId, "%" + searchKey + "%"},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                metaData = cursor.getString(cursor.getColumnIndex(COL_META_DATA));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return metaData;
    }
}
