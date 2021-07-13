package com.example.gardenry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyRequestAdapter extends RecyclerView.Adapter<MyRequestAdapter.myViewholder> {
    Context context;
ArrayList<Request> arr;


    public MyRequestAdapter(Context context, ArrayList<Request> arr) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item,parent,false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        holder.plant_name.setText(arr.get(position).getName());
        holder.Description.setText(arr.get(position).getDesc());
        String remarks = arr.get(position).getRemarks();
        if(remarks.trim().isEmpty())
            remarks="Not yet given...";
        holder.remarks.setText(remarks);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class myViewholder extends RecyclerView.ViewHolder {
            TextView plant_name, Description,remarks;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            plant_name = itemView.findViewById(R.id.txt_name_request_item);
            Description = itemView.findViewById(R.id.txt_description_request_item);
            remarks=itemView.findViewById(R.id.txt_remarks_request_item);
        }
    }
}
