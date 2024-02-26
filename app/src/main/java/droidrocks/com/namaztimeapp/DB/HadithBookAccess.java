package droidrocks.com.namaztimeapp.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.HadithEntity.HadithBook;


/** video tutorial here - https://www.youtube.com/watch?v=rziyVBKEU50
 * and other usfull links - https://stackoverflow.com/questions/9280692/android-sqlite-select-query
 * https://github.com/jgilfelt/android-sqlite-asset-helper
 */


public class HadithBookAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static HadithBookAccess instance;
    Cursor cursor = null;


    public HadithBookAccess(Context context) {
        openHelper = new DataBaseOpenHelper(context);
    }

    public static synchronized HadithBookAccess getInstance(Context context){
        if (instance==null){
            instance = new HadithBookAccess(context);
        }
        return instance;
    }

    /**
     * every time you have to @open DB before using it*/
    //to open database
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    /**
     * every time you have to @close DB after using it*/
    //close db connection
    public void close(){
        if (db!=null){
            this.db.close();
        }
    }


    /**
     * Returns a list of HadithBook objects from the database.
     */
    @SuppressLint("Range")
    public List<HadithBook> getAllBooks() {
        List<HadithBook> bookList = new ArrayList<>();
        open();
        // Query the database to get all books
        Cursor cursor = db.rawQuery("SELECT * FROM hadithbook", null);
        if (cursor.moveToFirst()) {
            do {
                HadithBook book = new HadithBook();
                book.setBookID(cursor.getInt(cursor.getColumnIndex("BookID")));
                book.setPubID(cursor.getInt(cursor.getColumnIndex("PubID")));
                book.setBookNameBD(cursor.getString(cursor.getColumnIndex("BookNameBD")));
                book.setBookNameEN(cursor.getString(cursor.getColumnIndex("BookNameEN")));
                book.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
                book.setPriority(cursor.getInt(cursor.getColumnIndex("priority")));
                book.setActive(cursor.getInt(cursor.getColumnIndex("Active")));
                // Add the book to the list
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return bookList;
    }

    @SuppressLint("Range")
    public HadithBook getBookById(int bookId) {
        HadithBook book = null;
        open();
        Cursor cursor = db.rawQuery("SELECT * FROM hadithbook WHERE BookID=?", new String[]{String.valueOf(bookId)});
        if (cursor.moveToFirst()) {
            book = new HadithBook();
            book.setBookID(cursor.getInt(cursor.getColumnIndex("BookID")));
            book.setPubID(cursor.getInt(cursor.getColumnIndex("PubID")));
            book.setBookNameBD(cursor.getString(cursor.getColumnIndex("BookNameBD")));
            book.setBookNameEN(cursor.getString(cursor.getColumnIndex("BookNameEN")));
            book.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
            book.setPriority(cursor.getInt(cursor.getColumnIndex("priority")));
            book.setActive(cursor.getInt(cursor.getColumnIndex("Active")));
        }
        cursor.close();
        close();
        return book;
    }

    // Method to search for books based on book name
    public List<HadithBook> searchBooks(String keyword) {
        List<HadithBook> bookList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query("TABLE_BOOKS", null, "COL_BOOK_NAME_BD" + " LIKE ?", new String[]{"%" + keyword + "%"}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithBook book = cursorToBook(cursor);
                    bookList.add(book);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bookList;
    }

    // Method to count the total number of books
    public int countBooks() {
        int count = 0;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + "TABLE_BOOKS", null);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return count;
    }

    // Method to sort books by book name
    public List<HadithBook> sortBooksByName() {
        List<HadithBook> bookList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query("TABLE_BOOKS", null, null, null, null, null, "COL_BOOK_NAME_BD" + " ASC");
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    HadithBook book = cursorToBook(cursor);
                    bookList.add(book);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bookList;
    }


    // Method to validate book data before insertion
    public boolean isValidBookData(HadithBook book) {
        // Add validation logic as needed
        return book != null && book.getBookNameBD() != null && !book.getBookNameBD().isEmpty();
    }

    // Helper method to convert cursor to book object
    private HadithBook cursorToBook(Cursor cursor) {
        HadithBook book = new HadithBook();
        // Populate book object from cursor
        // Example:
        // book.setBookId(cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID)));
        // book.setBookNameBD(cursor.getString(cursor.getColumnIndex(COL_BOOK_NAME_BD)));
        return book;
    }

    // Other CRUD methods (insertBook, updateBook, deleteBook) can be added similarly
}


