package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edName,edCourse,edFees;
    Button btnAdd,btnView;
    public static DatabaseHelper dbHelper;
    static int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName=findViewById(R.id.txtName);
        edCourse=findViewById(R.id.txtCourse);
        edFees=findViewById(R.id.txtFees);
        btnAdd=findViewById(R.id.btnAdd);
        btnView=findViewById(R.id.btnView);

        if(getIntent().getIntExtra("id",0)>0)
        {
            Intent intent=getIntent();
            btnAdd.setText("Update");
            id=intent.getIntExtra("id",0);
            edName.setText(intent.getStringExtra("name"));
            edCourse.setText(intent.getStringExtra("course"));
            edFees.setText(String.valueOf(intent.getIntExtra("fees",0)));
        }

        dbHelper=new DatabaseHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edName.getText().toString();
                String course=edCourse.getText().toString();
                int fees=Integer.parseInt(edFees.getText().toString());

                if(btnAdd.getText().toString().equals("Add Data"))
                {
                    boolean result=dbHelper.insertStudent(name,course,fees);
                    if(result)
                    {
                        Toast.makeText(MainActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(MainActivity.this, "Record Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }else if(btnAdd.getText().toString().equals("Update"))
                {
                    boolean result=dbHelper.updateStudent(id,name,course,fees);
                    if(result)
                    {
                        Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                        clearFeilds();
                        btnAdd.setText("Add Data");
                        Intent intent=new Intent(MainActivity.this,StudentsViewActivity.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(MainActivity.this, "Record Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,StudentsViewActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clearFeilds()
    {
        edName.setText("");
        edCourse.setText("");
        edFees.setText("");
    }
}