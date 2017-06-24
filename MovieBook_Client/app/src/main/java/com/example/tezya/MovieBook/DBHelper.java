package com.example.tezya.MovieBook;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by xietengxiao on 2017/6/5.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "cinema_db";//数据库名字
    public static String TABLE_NAME = "customer";// 表名
    public static String FIELD_Username = "username";// 列名
    public static String  FIELD_Password= "password";// 列名
    private static final int DB_VERSION = 4;   // 数据库版本

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String  sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + FIELD_Username + " text primary key , " + FIELD_Password + " text not null);";
        db.execSQL(sql);
        String  sql1 = "CREATE TABLE IF NOT EXISTS " + "UserPage" + "(" + FIELD_Username + " text not null , " + "MovieType" + " int not null , "+"MovieDate"+ " int not null , "+"MovieTime "+"int not null , "+"columns text not null , "+"rows text not null);";
        db.execSQL(sql1);



    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//      创建表

        onCreate(db);

    }

}
