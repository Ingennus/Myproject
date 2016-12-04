package com.example.ingennus.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ingennus on 2016/11/29.
 */
//数据库类

public class notedb extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String PATH ="path";
    public static final String VIDEO ="video";
    public static final String ID ="_id" ;
    public static final String TIME = "time";
    public  notedb db;

    public notedb(Context context) {
        super(context,"notes", null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL("CREATE TABLE"+ TABLE_NAME+"(" +ID
                +"INTEGER PRIMARY KEY AUTOINCREMENT," +CONTENT
                +" TEXT NOT NULL,"
                + PATH + " TEXT NOT NULL," + VIDEO +" TEXT NOT NULL,"
                +TIME + " TEXT NOT NULL)");

    }




}

