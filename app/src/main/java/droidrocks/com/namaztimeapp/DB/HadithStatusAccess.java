package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.HadithEntity.HadithStatus;

public class HadithStatusAccess {
    private static final String TAG = "HadithStatusDatabase";
    private static final String TABLE_STATUS = "hadithstatus";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithStatusAccess instance;

    // Define column names
    private static final String COL_STATUS_ID = "StatusID";
    private static final String COL_STATUS_BG = "StatusBG";
    private static final String COL_STATUS_EN = "StatusEN";
    private static final String COL_COL_CODE = "ColCode";
    private static final String COL_ACTIVE = "Active";

    private HadithStatusAccess(Context context) {
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static synchronized HadithStatusAccess getInstance(Context context) {
        if (instance == null) {
            instance = new HadithStatusAccess(context);
        }
        return instance;
    }

    // Get all status records
    @SuppressLint("Range")
    public List<HadithStatus> getAllStatus() {
        List<HadithStatus> statusList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_STATUS, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithStatus status = new HadithStatus();
                    status.setStatusID(cursor.getInt(cursor.getColumnIndex(COL_STATUS_ID)));
                    status.setStatusBG(cursor.getString(cursor.getColumnIndex(COL_STATUS_BG)));
                    status.setStatusEN(cursor.getString(cursor.getColumnIndex(COL_STATUS_EN)));
                    status.setColCode(cursor.getString(cursor.getColumnIndex(COL_COL_CODE)));
                    status.setActive(cursor.getInt(cursor.getColumnIndex(COL_ACTIVE))==1);
                    statusList.add(status);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting all status records: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return statusList;
    }

    // Get status record by ID
    @SuppressLint("Range")
    public HadithStatus getStatusById(int statusId) {
        HadithStatus status = null;
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_STATUS, null, COL_STATUS_ID + "=?",
                    new String[]{String.valueOf(statusId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                status = new HadithStatus();
                status.setStatusID(cursor.getInt(cursor.getColumnIndex(COL_STATUS_ID)));
                status.setStatusBG(cursor.getString(cursor.getColumnIndex(COL_STATUS_BG)));
                status.setStatusEN(cursor.getString(cursor.getColumnIndex(COL_STATUS_EN)));
                status.setColCode(cursor.getString(cursor.getColumnIndex(COL_COL_CODE)));
                status.setActive(cursor.getInt(cursor.getColumnIndex(COL_ACTIVE))==1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting status record by ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return status;
    }

    // Get total count of status records
    public int getStatusCount() {
        int count = 0;
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_STATUS, null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting total count of status records: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return count;
    }

}
