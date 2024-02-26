package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.HadithEntity.HadithMain;

public class HadithMainAccess {

    private static final String TABLE_MAIN = "hadithmain";
    private static final String COL_HADITH_ID = "HadithID";
    private static final String COL_DATE_INSERT = "DateInsert";
    private static final String COL_DATE_UPDATE = "DateUpdate";
    private static final String COL_ENTRY_BY = "EntryBy";
    private static final String COL_UPDATE_BY = "UpdateBy";
    private static final String COL_CHECK_STATUS = "CheckStatus";
    private static final String COL_RABI_ID = "RabiID";
    private static final String COL_BOOK_ID = "BookID";
    private static final String COL_SOURCE_ID = "SourceID";
    private static final String COL_CHAPTER_ID = "chapterID";
    private static final String COL_PART_ID = "PartID";
    private static final String COL_SECTION_ID = "SectionID";
    private static final String COL_HADITH_NO = "HadithNo";
    private static final String COL_ARABIC_HADITH = "ArabicHadith";
    private static final String COL_BANGLA_HADITH = "BanglaHadith";
    private static final String COL_ENGLISH_HADITH = "EnglishHadith";
    private static final String COL_HADITH_IMAGE = "HadithImage";
    private static final String COL_HADITH_NOTE = "HadithNote";
    private static final String COL_HADITH_ACTIVE = "HadithActive";
    private static final String COL_HADITH_STATUS = "HadithStatus";
    private static final String COL_HADITH_TAG = "hadithTag";
// Define other column names here...


    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithMainAccess instance;

    private HadithMainAccess(Context context) {
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static synchronized HadithMainAccess getInstance(Context context) {
        if (instance == null) {
            instance = new HadithMainAccess(context);
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

    @SuppressLint("Range")
    public List<HadithMain> getAllHadithMain() {
        List<HadithMain> hadithMainList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));

                    // Set other attributes here...
                    hadithMainList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return hadithMainList;
    }


    @SuppressLint("Range")
    public HadithMain getHadithMainById(long id) {
        HadithMain hadithMain = null;
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_HADITH_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                hadithMain = new HadithMain();
                hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithMain;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainByDate(String date) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_DATE_INSERT + " = ?", new String[]{date}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainByBookId(int bookId) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_BOOK_ID + " = ?", new String[]{String.valueOf(bookId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainByChapterId(int chapterId) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_CHAPTER_ID + " = ?", new String[]{String.valueOf(chapterId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainByPartId(int partId) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_PART_ID + " = ?", new String[]{String.valueOf(partId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainBySectionId(int sectionId) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_SECTION_ID + " = ?", new String[]{String.valueOf(sectionId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainBySourceId(int sourceId) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_SOURCE_ID + " = ?", new String[]{String.valueOf(sourceId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainByTag(String tag) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MAIN, null, COL_HADITH_TAG + " = ?", new String[]{tag}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> getHadithMainByDateRange(String startDate, String endDate) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String selection = COL_DATE_INSERT + " BETWEEN ? AND ?";
            String[] selectionArgs = {startDate, endDate};
            cursor = db.query(TABLE_MAIN, null, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }


    @SuppressLint("Range")
    public List<HadithMain> searchHadithMainByKeyword(String keyword) {
        List<HadithMain> hadithList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String selection = COL_ARABIC_HADITH + " LIKE ? OR " +
                    COL_BANGLA_HADITH + " LIKE ? OR " +
                    COL_ENGLISH_HADITH + " LIKE ?";
            String[] selectionArgs = {"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"};
            cursor = db.query(TABLE_MAIN, null, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithMain hadithMain = new HadithMain();
                    hadithMain.setHadithID(cursor.getLong(cursor.getColumnIndex(COL_HADITH_ID)));
                    hadithMain.setDateInsert(cursor.getString(cursor.getColumnIndex(COL_DATE_INSERT)));
                    hadithMain.setDateUpdate(cursor.getString(cursor.getColumnIndex(COL_DATE_UPDATE)));
                    hadithMain.setEntryBy(cursor.getInt(cursor.getColumnIndex(COL_ENTRY_BY)));
                    hadithMain.setUpdateBy(cursor.getInt(cursor.getColumnIndex(COL_UPDATE_BY)));
                    hadithMain.setCheckStatus(cursor.getInt(cursor.getColumnIndex(COL_CHECK_STATUS)));
                    hadithMain.setRabiID(cursor.getInt(cursor.getColumnIndex(COL_RABI_ID)));
                    hadithMain.setBookID(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
                    hadithMain.setSourceID(cursor.getInt(cursor.getColumnIndex(COL_SOURCE_ID)));
                    hadithMain.setChapterID(cursor.getInt(cursor.getColumnIndex(COL_CHAPTER_ID)));
                    hadithMain.setPartID(cursor.getInt(cursor.getColumnIndex(COL_PART_ID)));
                    hadithMain.setSectionID(cursor.getInt(cursor.getColumnIndex(COL_SECTION_ID)));
                    hadithMain.setHadithNo(cursor.getInt(cursor.getColumnIndex(COL_HADITH_NO)));
                    hadithMain.setArabicHadith(cursor.getString(cursor.getColumnIndex(COL_ARABIC_HADITH)));
                    hadithMain.setBanglaHadith(cursor.getString(cursor.getColumnIndex(COL_BANGLA_HADITH)));
                    hadithMain.setEnglishHadith(cursor.getString(cursor.getColumnIndex(COL_ENGLISH_HADITH)));
                    hadithMain.setHadithImage(cursor.getString(cursor.getColumnIndex(COL_HADITH_IMAGE)));
                    hadithMain.setHadithNote(cursor.getString(cursor.getColumnIndex(COL_HADITH_NOTE)));
                    hadithMain.setHadithActive(cursor.getInt(cursor.getColumnIndex(COL_HADITH_ACTIVE)));
                    hadithMain.setHadithStatus(cursor.getInt(cursor.getColumnIndex(COL_HADITH_STATUS)));
                    hadithMain.setHadithTag(cursor.getString(cursor.getColumnIndex(COL_HADITH_TAG)));
                    // Set values for other columns similarly
                    hadithList.add(hadithMain);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return hadithList;
    }

    public int getHadithMainCount() {
        int count = 0;
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_MAIN, null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return count;
    }



}

