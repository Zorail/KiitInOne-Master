package app.kiit.kshitijsharma.allinone.sqdatabase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by kshitij sharma on 1/5/2017.
 */

public class FoodDB extends ContentProvider {
    Context c;
    SQLiteDatabase db;
    @Override
    public boolean onCreate() {
        c= getContext();
        db =  c.openOrCreateDatabase("CampusInfo",c.MODE_PRIVATE,null);
        db.execSQL("create table if not exists food(Rno Int primary key,name varchar[20],contact long,lat double, longi double)");
        try {
        }catch(Exception e){
            //Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return false;
    }
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        c= getContext();
        db= c.openOrCreateDatabase("CampusInfo", c.MODE_PRIVATE,null);
        db.insert("Campuses",null,contentValues);
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        c= getContext();
        db= c.openOrCreateDatabase("CampusInfo", c.MODE_PRIVATE,null);
        int i= db.delete("Cmpuses",s,strings);
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        c= getContext();
        db= c.openOrCreateDatabase("CampusInfo", c.MODE_PRIVATE,null);
        int i = db.update("Campuses",contentValues,s,strings);
        return i;
    }
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        c= getContext();
        db= c.openOrCreateDatabase("CampusInfo", c.MODE_PRIVATE,null);
        String q="select * from Campuses";
        Cursor cur= db.rawQuery(q,null);
        return cur;
    }
  /*  public Cursor getCampusList() {
        c= getContext();
        db= c.openOrCreateDatabase("CampusInfo", c.MODE_PRIVATE,null);
        String q="select * from Campuses";
        Cursor cur= db.rawQuery(q,null);
        return cur;
    }*/

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }
}