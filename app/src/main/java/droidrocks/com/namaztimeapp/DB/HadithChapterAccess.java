package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.HadithEntity.HadithChapter;

public class HadithChapterAccess {

    // Database fields
    private static final String TABLE_CHAPTER = "hadithchapter";
    private static final String COL_CHAPTER_ID = "chapID";
    private static final String COL_BOOK_ID = "BookID";
    private static final String COL_SECTION_ID = "SectionID";
    private static final String COL_CHAPTER_BG = "ChapterBG";
    private static final String COL_CHAPTER_AR = "ChapterAR";
    private static final String COL_CHAPTER_EN = "ChapterEN";
    private static final String COL_STATUS_ACTIVE = "StatusActive";

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithChapterAccess instance;

    private HadithChapterAccess(Context context) {
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static HadithChapterAccess getInstance(Context context) {
        if (instance == null) {
            instance = new HadithChapterAccess(context);
        }
        return instance;
    }

    // Method to open the database
    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    // Method to close the database
    public void close() {
        if (db != null) {
            db.close();
        }
    }

    // Method to retrieve all chapters for a given book and section
    @SuppressLint("Range")
    public List<HadithChapter> getAllChapters(int bookId, int sectionId) {
        List<HadithChapter> chapters = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_CHAPTER, null,
                    COL_BOOK_ID + " = ? AND " + COL_SECTION_ID + " = ?",
                    new String[]{String.valueOf(bookId), String.valueOf(sectionId)},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithChapter chapter = new HadithChapter();
                    chapter.setChapID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    chapter.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    chapter.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    chapter.setChapterBG(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_BG)));
                    chapter.setChapterAR(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_AR)));
                    chapter.setChapterEN(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_EN)));
                    chapter.setStatusActive(cursor.getInt(cursor.getColumnIndex(COL_STATUS_ACTIVE)) == 1);
                    chapters.add(chapter);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return chapters;
    }

    // Method to retrieve a chapter by its ID
    @SuppressLint("Range")
    public HadithChapter getChapterById(int chapterId) {
        HadithChapter chapter = null;
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_CHAPTER, null,
                    COL_CHAPTER_ID + " = ?",
                    new String[]{String.valueOf(chapterId)},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                chapter = new HadithChapter();
                chapter.setChapID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                chapter.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                chapter.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                chapter.setChapterBG(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_BG)));
                chapter.setChapterAR(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_AR)));
                chapter.setChapterEN(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_EN)));
                chapter.setStatusActive(cursor.getInt(cursor.getColumnIndex(COL_STATUS_ACTIVE)) == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return chapter;
    }

    // Method to retrieve all chapters for a given book
    @SuppressLint("Range")
    public List<HadithChapter> getAllChaptersForBook(int bookId) {
        List<HadithChapter> chapters = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_CHAPTER, null,
                    COL_BOOK_ID + " = ?",
                    new String[]{String.valueOf(bookId)},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithChapter chapter = new HadithChapter();
                    chapter.setChapID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    chapter.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    chapter.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    chapter.setChapterBG(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_BG)));
                    chapter.setChapterAR(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_AR)));
                    chapter.setChapterEN(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_EN)));
                    chapter.setStatusActive(cursor.getInt(cursor.getColumnIndex(COL_STATUS_ACTIVE)) == 1);
                    chapters.add(chapter);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return chapters;
    }

    // Method to retrieve all chapters for a given section
    @SuppressLint("Range")
    public List<HadithChapter> getAllChaptersForSection(int sectionId) {
        List<HadithChapter> chapters = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_CHAPTER, null,
                    COL_SECTION_ID + " = ?",
                    new String[]{String.valueOf(sectionId)},
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithChapter chapter = new HadithChapter();
                    chapter.setChapID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    chapter.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    chapter.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    chapter.setChapterBG(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_BG)));
                    chapter.setChapterAR(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_AR)));
                    chapter.setChapterEN(cursor.getString(cursor.getColumnIndex(COL_CHAPTER_EN)));
                    chapter.setStatusActive(cursor.getInt(cursor.getColumnIndex(COL_STATUS_ACTIVE)) == 1);
                    chapters.add(chapter);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return chapters;
    }
}
