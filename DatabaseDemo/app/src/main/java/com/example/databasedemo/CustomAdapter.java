package com.example.databasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Student> {
    Activity context;
    int resource;
    ArrayList<Student> stList;

    public CustomAdapter(@NonNull Activity context, int resource, ArrayList<Student> stList) {
        super(context, resource,stList);
        this.context=context;
        this.resource=resource;
        this.stList=stList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.student_list_item,null,false);
        TextView txtId= rowView.findViewById(R.id.txtId);
        TextView txtName= rowView.findViewById(R.id.txtName);
        TextView txtCourse= rowView.findViewById(R.id.txtCourse);
        TextView txtFees= rowView.findViewById(R.id.txtFees);
        TextView txtClose= rowView.findViewById(R.id.txtClose);

        txtId.setText(String.valueOf(stList.get(position).id));
        txtName.setText(stList.get(position).name);
        txtCourse.setText(stList.get(position).course);
        txtFees.setText(String.valueOf(stList.get(position).fees));

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.dbHelper.deleteStudent(stList.get(position).id)!=-1)
                {
                    stList.remove(stList.get(position));
                    notifyDataSetChanged();
                }else
                {
                    Toast.makeText(context, "Record not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rowView;
    }
}
