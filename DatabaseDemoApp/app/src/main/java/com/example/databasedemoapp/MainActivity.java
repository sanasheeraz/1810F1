package com.example.databasedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edName,edCourse,edFees,edId;
    Button btnInsert,btnView,btnEdit;
    public static DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edId=findViewById(R.id.txtId);
        edName=findViewById(R.id.txtName);
        edCourse=findViewById(R.id.txtCourse);
        edFees=findViewById(R.id.txtFees);
        btnInsert=findViewById(R.id.btnInsert);
        btnView=findViewById(R.id.btnView);
        btnInsert.setText("Insert");
        //btnEdit=findViewById(R.id.btnEdit);

        Intent intent=getIntent();
        if(intent.getStringExtra("Name")!=null)
        {
            edId.setText(intent.getStringExtra("Id"));
            edName.setText(intent.getStringExtra("Name"));
            edCourse.setText(intent.getStringExtra("Course"));
            edFees.setText(intent.getStringExtra("Fees"));
           // btnEdit.setVisibility(View.VISIBLE);
            btnInsert.setText("Update");
        }


        db = new DatabaseHelper(MainActivity.this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnInsert.getText().toString().equals("Insert"))
                {
                    String name=edName.getText().toString();
                    String course=edCourse.getText().toString();
                    int fees=Integer.parseInt(edFees.getText().toString());

                    boolean res=db.InsertData(name,course,fees);
                    if(res)
                    {
                        Toast.makeText(MainActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                        clearAll();
                    }else
                    {
                        Toast.makeText(MainActivity.this, "Record not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(btnInsert.getText().toString().equals("Update"))
                {
                    int id=Integer.parseInt(edId.getText().toString());
                    String name=edName.getText().toString();
                    String course=edCourse.getText().toString();
                    int fees=Integer.parseInt(edFees.getText().toString());

                    boolean res=db.updateData(id,name,course,fees);
                    if(res)
                    {
                        Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                        clearAll();
                    }else
                    {
                        Toast.makeText(MainActivity.this, "Record not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });



//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int id=Integer.parseInt(edId.getText().toString());
//                String name=edName.getText().toString();
//                String course=edCourse.getText().toString();
//                int fees=Integer.parseInt(edFees.getText().toString());
//
//                boolean res=db.updateData(id,name,course,fees);
//                if(res)
//                {
//                    Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
//                }else
//                {
//                    Toast.makeText(MainActivity.this, "Record not Updated", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    public void clearAll()
    {
        edName.setText("");
        edCourse.setText("");
        edFees.setText("");
    }
}