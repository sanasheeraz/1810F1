package com.example.databasedemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String Database_Name="Institute.db";
    public static String Table_Name="Student";
    public static String Column1="St_Id";
    public static String Column2="St_Name";
    public static String Column3="St_Course";
    public static String Column4="St_Fees";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //sqLiteDatabase.execSQL("Create table Student (St_Id INTEGER PRIMARY KEY AUTOINCREMENT, St_Name TEXT, St_Course TEXT,St_Fees INTEGER)");
            sqLiteDatabase.execSQL("Create table "+Table_Name+" ( "+Column1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Column2+" TEXT,"+Column3+" TEXT,"+Column4+" INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table "+Table_Name);
        onCreate(sqLiteDatabase);
    }
    public boolean InsertData(String name,String course,int fees)
    {
        ContentValues content=new ContentValues();
        content.put(Column2,name);
        content.put(Column3,course);
        content.put(Column4,fees);

        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.insert(Table_Name,null,content);
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public ArrayList<Student> getAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Table_Name,null);

        ArrayList<Student> arrayList=new ArrayList<Student>();

        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(Column1));
            String name=cursor.getString(cursor.getColumnIndex(Column2));
            String course=cursor.getString(cursor.getColumnIndex(Column3));
            int fees=cursor.getInt(cursor.getColumnIndex(Column4));
            Student st=new Student(id,name,course,fees);
            arrayList.add(st);
        }

        return arrayList;
    }
    public boolean updateData(int Id,String Name,String Course,int Fees)
    {
        ContentValues content=new ContentValues();
        content.put(Column1,Id);
        content.put(Column2,Name);
        content.put(Column3,Course);
        content.put(Column4,Fees);

        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.update(Table_Name,content,Column1+"=?",new String[]{String.valueOf(Id)});
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
                    }
    }

    public boolean deleteData(int Id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(Table_Name,Column1+"=?",new String[]{String.valueOf(Id)});
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }
    
}
