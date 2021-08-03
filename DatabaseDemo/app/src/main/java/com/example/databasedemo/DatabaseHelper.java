package com.example.databasedemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String Database_name="Institute.db";
    public static String Table_Name="Student";
    public static String Col1="St_Id";
    public static String Col2="St_Name";
    public static String Col3="St_Course";
    public static String Col4="St_Fees";

    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("Create table Student (St_Id INTEGER PRIMARY KEY AUTOINCREMENT,St_Name TEXT,St_Course TEXT,St_Fees INTEGER)");
        sqLiteDatabase.execSQL("Create table "+Table_Name+" ("+Col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Col2+" TEXT,"+Col3+" TEXT,"+Col4+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+Table_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertStudent(String name,String course,int fees)
    {
        ContentValues content=new ContentValues();
        content.put(Col2,name);
        content.put(Col3,course);
        content.put(Col4,fees);

        long result=db.insert(Table_Name,null,content);
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor fetchStudent()
    {
        Cursor cursor=db.rawQuery("select * from "+Table_Name,null);
        return cursor;
    }

    public boolean updateStudent(int id,String name,String course,int fees)
    {
        ContentValues content=new ContentValues();
        content.put(Col2,name);
        content.put(Col3,course);
        content.put(Col4,fees);

        long result=db.update(Table_Name,content,Col1+"=?",new String[]{String.valueOf(id)});
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public int deleteStudent(int id)
    {
        int result=db.delete(Table_Name,Col1+"=?",new String[]{String.valueOf(id)});
        return result;
    }

}
