package com.anand.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DB_NAME="Ratings.db";
    public static  final String DB_TABLE="Ratings_Table";

    private static final String ID="ID";
    private static final String MIN ="MIN";

    private static final String CREATE_TABLE="CREATE TABLE "+DB_TABLE+" ( " +
            MIN+ " TEXT " + " )";



    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+ DB_TABLE);
        onCreate(sqLiteDatabase);
    }
    public  boolean insertData(String min)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MIN,min);


        long result=db.insert(DB_TABLE,null,cv);
        return result!=-1;
    }
    public Cursor viewData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="Select * from "+DB_TABLE;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }



}
