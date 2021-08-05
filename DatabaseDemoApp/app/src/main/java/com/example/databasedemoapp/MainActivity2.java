package com.example.databasedemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public static RecyclerView recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recycle=findViewById(R.id.recyclerView);

        DatabaseHelper db=new DatabaseHelper(MainActivity2.this);
        ArrayList<Student> arrayList=db.getAll();

        CustomAdapter adapter=new CustomAdapter(MainActivity2.this,arrayList);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(adapter);

    }
}