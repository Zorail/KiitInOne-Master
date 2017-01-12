package app.kiit.kshitijsharma.allinone.sqdatabase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by kshitij sharma on 12/12/2016.
 */

public class mycontentprovider extends ContentProvider {


    Context c;
    SQLiteDatabase db;
    @Override
    public boolean onCreate() {
        c= getContext();
        db =  c.openOrCreateDatabase("CampusInfo",c.MODE_PRIVATE,null);
        db.execSQL("create table if not exists Campuses(campus Int primary key,about varchar[200],lat double, longi double)");
        try {
            db.execSQL("Insert into Campuses values('1','1.School of computer Application\n 2.Koel Campus',20.3461975,85.8212554)");
            db.execSQL("Insert into Campuses values('2','1. Kiit Polytechnic\n 2.Boys Hostel(Polytechnic)',20.3541555,85.8206315)");
            db.execSQL("Insert into Campuses values('3','1. School Of Civil Engineering\n 2. School Of Electrical Engineering\n 3. D-Block\n 4. Girls Hostel(QC 1,2,3,4) 5. Biju Patnaik indoor Stadium',20.352735,85.816384)");
            db.execSQL("Insert into Campuses values('4','1. Chintan Building\n 2. Boys Hostel(KP 1,2,3)',20.354306,85.819484)");
            db.execSQL("Insert into Campuses values('5','1. KIMS Campus\n 2. Pradyuma Bal Memorial Hospital\n 3. School Of Nursing Sciences\n 4. School Of Dental Science\n 5. KIMS Hostel(Boys & Girls)\n 6.Staff Quarter\n 7. KIMS Auditorium & Playground',20.352083,85.813973)");
            db.execSQL("Insert into Campuses values('6','1. Guest House\n 2. Auditorium\n 3. Central Library\n 4. Central Canteen\n 5. ANNEX- A,B',20.353001,85.819723)");
            db.execSQL("Insert into Campuses values('7','1. School Of Management\n 2. School Of Rural Management\n 3. Hostel Boys & Girls(SOM & SRM)',20.350057,85.820246)");
            db.execSQL("Insert into Campuses values('8','1. School Of Mechanical Engineering\n 2. Science College\n 3. School Of Fashion & Technology\n  4. School Of Film & Media Sciences\n 5. Boys Hostel(KP 12)\n 6. Basketball, Volleyball & Lawn Tennis courts\n 7. Gym & swimming Pool',20.351411,85.820143)");
            db.execSQL("Insert into Campuses values('9','1. Kiit International School\n 2. School Hostel (Boys & Girls)',20.353163,85.810756)");
            db.execSQL("Insert into Campuses values('10','KISS',20.362612,85.810224)");
            db.execSQL("Insert into Campuses values('11','1. School Of Bio-Technology\n 2. Auditorium\n 3. Hostel(Boys- KP8 & Girls)',20.359719,85.822902)");
            db.execSQL("Insert into Campuses values('12','1. School Of Electronics Engineering\n 2. Rose Garden\n 3. Hostel (KP5)',20.355727,85.820414)");
            db.execSQL("Insert into Campuses values('13','1. Student Activity Centre(KSAC)\n 2. Kiit Stadium',20.357072,85.818318)");
            db.execSQL("Insert into Campuses values('14','1. KIIT ITI\n 2. Hostel(KP 10)',20.355621,85.815132)");
            db.execSQL("Insert into Campuses values('15','1. School Of Computer Engineering\n 2. Basketball, Lawn tennis Court\n 3. Indoor Stadium\n 4. Hostel (KP 6,9)',20.348936,85.815729)");
            db.execSQL("Insert into Campuses values('16','1. School Of LAW\n 2. Hostel(Boys & Girls)',20.362108,85.822020)");
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
