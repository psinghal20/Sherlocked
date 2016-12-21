package com.example.psing.sherlocked.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelper extends SQLiteOpenHelper {
    public static final String Dbname="sherlock.db";
    public static final String tablename="SEASONS";
    public static final String col1="ID";
    public static final String col2="SEASON";

    public databasehelper(Context context){
        super(context, Dbname ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + tablename + " ( " +
                col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col2 + " TEXT NOT NULL);";

        db.execSQL(createTable);    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tablename);
        onCreate(db);
    }
    public boolean insertdata(String season){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,season);
        long result=db.insert(tablename,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
}
