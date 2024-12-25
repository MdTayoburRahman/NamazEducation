package droidrocks.com.namaztimeapp.SQLiteDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import droidrocks.com.namaztimeapp.Models.RuqyahAyahEntity;
import droidrocks.com.namaztimeapp.Models.RuqyahGroupEntity;

/** video tutorial here - https://www.youtube.com/watch?v=rziyVBKEU50
 * and other usfull links - https://stackoverflow.com/questions/9280692/android-sqlite-select-query
 * https://github.com/jgilfelt/android-sqlite-asset-helper
 */


public class RuqyahDataAccess {
    public static final String TAG = "RuqyahDataAccess";
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

public List<RuqyahAyahEntity> getRuqyahAyahListByGroupId(RuqyahGroupEntity ruqyahGroupEntity) {
    List<RuqyahAyahEntity> ruqyahAyahList = new ArrayList<>();
    if (ruqyahAyahList.size()>0){
        ruqyahAyahList.clear();
    }
    cursor = db.rawQuery("SELECT * FROM ruqyah_ayah WHERE group_id = "+ruqyahGroupEntity.getId(),null);
    if (cursor.moveToFirst()){
        do {

            int id = cursor.getInt(0);
            int group_id = cursor.getInt(1);
            int ayah_number = cursor.getInt(2);
            String ayah_title = cursor.getString(3);
            String ayah_arabic = cursor.getString(4);
            String ayah_bangla = cursor.getString(5);
            String ayah_note = cursor.getString(6);
            String audiopath = cursor.getString(7);

            RuqyahAyahEntity entity = new RuqyahAyahEntity(audiopath,ayah_arabic,ayah_bangla,ayah_note,ayah_number,ayah_title,group_id,id);
            ruqyahAyahList.add(entity);
            Log.d(TAG, "getRuqyahAyahListByGroupId: --------- "+entity);
        }while (cursor.moveToNext());
    }
    return ruqyahAyahList;
}








}
