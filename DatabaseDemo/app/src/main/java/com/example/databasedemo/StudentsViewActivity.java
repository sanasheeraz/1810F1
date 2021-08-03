package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentsViewActivity extends AppCompatActivity {

    ListView lstStudents;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_view);

        lstStudents=findViewById(R.id.lstStudents);

        dbHelper=new DatabaseHelper(StudentsViewActivity.this);
        ArrayList<Student> stList=new ArrayList<Student>();

        Cursor cursor=dbHelper.fetchStudent();
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Col1));
            String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col2));
            String course=cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col3));
            int fees=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Col4));

            Student st=new Student(id,name,course,fees);
            stList.add(st);
        }

        CustomAdapter adapter=new CustomAdapter(StudentsViewActivity.this,R.layout.student_list_item,stList);
        lstStudents.setAdapter(adapter);

        lstStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(StudentsViewActivity.this,stList.get(i).name , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(StudentsViewActivity.this,MainActivity.class);
                intent.putExtra("id",stList.get(i).id);
                intent.putExtra("name",stList.get(i).name);
                intent.putExtra("course",stList.get(i).course);
                intent.putExtra("fees",stList.get(i).fees);

                startActivity(intent);
            }
        });
    }
}