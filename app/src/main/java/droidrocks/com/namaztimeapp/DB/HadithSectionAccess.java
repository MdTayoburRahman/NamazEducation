package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.HadithEntity.HadithSection;

public class HadithSectionAccess {
    private static final String TAG = HadithSectionAccess.class.getSimpleName();

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithSectionAccess instance;

    // Define table name and column names
    private static final String TABLE_SECTION = "hadithsection";
    private static final String COL_SECTION_ID = "SectionID";
    private static final String COL_BOOK_ID = "BookID";
    private static final String COL_SECTION_BD = "SectionBD";
    private static final String COL_SECTION_EN = "SectionEN";
    private static final String COL_SEC_ACTIVE = "SecActive";

    private HadithSectionAccess(Context context) {
        openHelper = new DataBaseOpenHelper(context);
    }

    public static HadithSectionAccess getInstance(Context context) {
        if (instance == null) {
            instance = new HadithSectionAccess(context);
        }
        return instance;
    }

    @SuppressLint("Range")
    public List<HadithSection> getAllSections() {
        List<HadithSection> sectionList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SECTION, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSection section = new HadithSection();
                    section.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    section.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    section.setSectionBD(cursor.getString(cursor.getColumnIndex(COL_SECTION_BD)));
                    section.setSectionEN(cursor.getString(cursor.getColumnIndex(COL_SECTION_EN)));
                    section.setSecActive(cursor.getInt(cursor.getColumnIndex(COL_SEC_ACTIVE))==1);
                    sectionList.add(section);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting sections: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sectionList;
    }

    // Method to get records by Section ID
    @SuppressLint("Range")
    public HadithSection getSectionById(int sectionId) {
        HadithSection section = null;
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SECTION + " WHERE " + COL_SECTION_ID + " = ?", new String[]{String.valueOf(sectionId)});
            if (cursor != null && cursor.moveToFirst()) {
                section = new HadithSection();
                section.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                section.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                section.setSectionBD(cursor.getString(cursor.getColumnIndex(COL_SECTION_BD)));
                section.setSectionEN(cursor.getString(cursor.getColumnIndex(COL_SECTION_EN)));
                section.setSecActive(cursor.getInt(cursor.getColumnIndex(COL_SEC_ACTIVE))==1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting section by ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return section;
    }

    // Method to get records by Book ID
    @SuppressLint("Range")
    public List<HadithSection> getSectionsByBookId(int bookId) {
        List<HadithSection> sectionList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_SECTION + " WHERE " + COL_BOOK_ID + " = ?", new String[]{String.valueOf(bookId)});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithSection section = new HadithSection();
                    section.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    section.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    section.setSectionBD(cursor.getString(cursor.getColumnIndex(COL_SECTION_BD)));
                    section.setSectionEN(cursor.getString(cursor.getColumnIndex(COL_SECTION_EN)));
                    section.setSecActive(cursor.getInt(cursor.getColumnIndex(COL_SEC_ACTIVE))==1);
                    sectionList.add(section);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting sections by Book ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return sectionList;
    }
}
