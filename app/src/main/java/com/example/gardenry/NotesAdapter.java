package com.example.gardenry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.holder> {
    Context context;
    ArrayList<Note> notes;

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater.from(parent.getContext()));
        View view=inflater.inflate(R.layout.note_item,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Note noteObject = notes.get(position);
        Long DateTimeMillies = Long.parseLong(noteObject.getStrDateTimeMillis());
        String note = noteObject.getNote();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date(DateTimeMillies));
        String time = new SimpleDateFormat("HH:mm"/*:ss*/, Locale.getDefault()).format(new Date(DateTimeMillies));

        holder.date.setText(date);
        holder.time.setText(time);
        holder.text.setText(note);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditNote.class);
                intent.putExtra("timestamp", noteObject.getStrDateTimeMillis());
                intent.putExtra("position", position);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void filterNotes(ArrayList<Note> filteredNotes){
        notes=filteredNotes;
        notifyDataSetChanged();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView date,time,text;
        View view;
        public holder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            text=itemView.findViewById(R.id.text);
            view = itemView;
        }
    }

}
