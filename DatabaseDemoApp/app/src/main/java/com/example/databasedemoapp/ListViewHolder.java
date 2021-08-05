package com.example.databasedemoapp;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    LinearLayout linear;
    TextView txtId,txtName,txtCourse,txtFees;
    Button btnUpdate,btnDelete;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        linear=itemView.findViewById(R.id.linearLayout);
        txtId=itemView.findViewById(R.id.txtId);
        txtName=itemView.findViewById(R.id.txtName);
        txtCourse=itemView.findViewById(R.id.txtCourse);
        txtFees=itemView.findViewById(R.id.txtFees);
        btnUpdate=itemView.findViewById(R.id.btnUpdate);
        btnDelete=itemView.findViewById(R.id.btnDelete);
    }
}
