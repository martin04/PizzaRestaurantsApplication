package com.martin.pizzarestaurantsapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Martin on 8.2.16.
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String DBNAME = "pizzas.db";
    private static final int DBVERSION = 1;

    public static final String TABLENAME = "restaurants";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_DISTANCE = "distance";


    private static final String createTable = String.format("create table %s(%s integer primary key autoincrement, %s text not null, %s real not null, %s real not null, %s real not null);", TABLENAME, COLUMN_ID, COLUMN_NAME, COLUMN_LATITUDE, COLUMN_LONGITUDE, COLUMN_DISTANCE);



    public DbHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }
}
