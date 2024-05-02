package droidrocks.com.namaztimeapp.SQLiteDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.Models.RuqyahGroupEntity;

/** video tutorial here - https://www.youtube.com/watch?v=rziyVBKEU50
 * and other usfull links - https://stackoverflow.com/questions/9280692/android-sqlite-select-query
 * https://github.com/jgilfelt/android-sqlite-asset-helper
 */


public class RuqyahDataAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static RuqyahDataAccess instance;
    Cursor cursor = null;


    public RuqyahDataAccess(Context context) {
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static RuqyahDataAccess getInstance(Context context){
        if (instance==null){
            instance = new RuqyahDataAccess(context);
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

    //query data from database
/**
 * this is an example below how to get data
 *
 * public String getData(String name){
        cursor = db.rawQuery("SELECT Address FROM tableName WHERE NAME = '"+name+"'",new String[]{});
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            String address  = cursor.getString(0);
            stringBuffer.append(""+address);
        }
        return stringBuffer.toString();
    }
 */


public List<RuqyahGroupEntity> getRuqyahGroupList(){
    List<RuqyahGroupEntity> ruqyahGroupList = new ArrayList<>();
    if (ruqyahGroupList.size()>0){
        ruqyahGroupList.clear();
    }
    cursor = db.rawQuery("SELECT * FROM ayah_group",null);
    if (cursor.moveToFirst()){
        do {
            int id = cursor.getInt(0);
            String subtitle = cursor.getString(1);
            String title = cursor.getString(2);
            ruqyahGroupList.add(new RuqyahGroupEntity(id,subtitle,title));
        }while (cursor.moveToNext());
    }
    return ruqyahGroupList;
}



/*    public List<PoetDataModel> getPoetList(){
        List<PoetDataModel> kobilList = new ArrayList<>();
        if (kobilList.size()>0){
            kobilList.clear();
        }
        cursor = db.rawQuery("SELECT id,author_name,aid FROM author_table",null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String author = cursor.getString(1);
                String aid = cursor.getString(2);
                kobilList.add(new PoetDataModel(id,author,aid));
            }while (cursor.moveToNext());
        }
        return kobilList;
    }

    public List<PoemDataModel> getPoemList(){
        List<PoemDataModel> kobitaList = new ArrayList<>();
        if (kobitaList.size()>0){
            kobitaList.clear();
        }
        cursor = db.rawQuery("SELECT id,poem_title,pid,poem_author,poem_lines,poem_linecount FROM poem_table",null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String poem_title = cursor.getString(1);
                String pid = cursor.getString(2);
                String poem_author = cursor.getString(3);
                String poem_lines = cursor.getString(4);
                String poem_linecount = cursor.getString(5);
                kobitaList.add(new PoemDataModel(id,poem_title,pid,poem_author,poem_lines,poem_linecount));
            }while (cursor.moveToNext());
        }
        return kobitaList;
    }*/





}
