package in.samarthdigital.studentinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "studentinfo.db";
    public static final String Table_Name = "studentinfo";
    public static final String COL_1 = "enroll";
    public static final String COL_2 = "fname";
    public static final String COL_3 = "lname";
    public static final String COL_4 = "dept";

    public DataBaseHelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + Table_Name + "(id integer primary key autoincrement,enroll integer unique,fname text,lname text,dept text)");
        Log.d("DATABASE CREATION : ", "Success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + Table_Name);
        onCreate(db);
    }

    public boolean insertData(Double enroll, String fname, String lname, String dept) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, enroll);
        values.put(COL_2, fname);
        values.put(COL_3, lname);
        values.put(COL_4, dept);
        long i = db.insert(Table_Name, null, values);
        if (i == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String dept) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res;
        res = db.rawQuery("select * from " + Table_Name + " where dept = \'" + dept + "\'order by enroll", null);
        return res;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_Name+" order by enroll",null);
        return res;
    }

    public int deleteData(String enroll){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(Table_Name,"enroll = ?",new String[] {enroll});
    }
}
