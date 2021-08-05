package com.example.databasedemoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<ListViewHolder> {
    ArrayList<Student> StArray;
    Context context;
    public CustomAdapter(Context context,ArrayList<Student> stArray) {
        StArray = stArray;
        this.context=context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View lstItem=layoutInflater.inflate(R.layout.item_list,parent,false);
        ListViewHolder viewHolder=new ListViewHolder(lstItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(StArray.get(position).Id));
        holder.txtName.setText(StArray.get(position).Name);
        holder.txtCourse.setText(StArray.get(position).Course);
        holder.txtFees.setText(String.valueOf(StArray.get(position).Fees));
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,MainActivity.class);
                intent.putExtra("Id",String.valueOf(StArray.get(position).Id));
                intent.putExtra("Name",StArray.get(position).Name);
                intent.putExtra("Course",StArray.get(position).Course);
                intent.putExtra("Fees",String.valueOf(StArray.get(position).Fees));
                context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result=MainActivity.db.deleteData(StArray.get(position).Id);
                if(result)
                {
                    Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
                    StArray.remove(position);
                    MainActivity2.recycle.removeViewAt(position);
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Record not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return StArray.size();
    }
}
