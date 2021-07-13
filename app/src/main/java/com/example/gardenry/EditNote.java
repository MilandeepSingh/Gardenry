package com.example.gardenry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import static android.content.ContentValues.TAG;

public class EditNote extends AppCompatActivity {

    EditText edtNoteText;
    Button btnUpdate;
    Button btnCancel;
    FirebaseFirestore fb;
    ImageButton imgbtnDel;
    AlertDialog.Builder builder;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        edtNoteText = findViewById(R.id.edt_note_edit_note);
        btnUpdate = findViewById(R.id.btn_update_edit_note);
        btnCancel = findViewById(R.id.btn_cancel_edit_note);
        imgbtnDel = findViewById(R.id.btn_del_edit_note);

        fb = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String strDateTimeMillies = intent.getStringExtra("timestamp");
        position = intent.getIntExtra("position",-1);


        fb.collection("Users").document("Irish")
                .collection("My Notes").document(strDateTimeMillies)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Note note = documentSnapshot.toObject(Note.class);
                edtNoteText.setText(note.getNote());
                (findViewById(R.id.ll_pbar_edt_edit_note)).setVisibility(View.GONE);
                edtNoteText.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d("Error :", e.getMessage());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = edtNoteText.getText().toString();
                if(note.trim().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please write some note!", Toast.LENGTH_SHORT).show();
                else{
                    View pbar = findViewById(R.id.ll_pbar_update_edit_note);
                    View buttons = findViewById(R.id.ll_buttons_edit_note);
                    pbar.setVisibility(View.VISIBLE);
                    buttons.setVisibility(View.GONE);
                fb.collection("Users").document("Irish")
                        .collection("My Notes").document(strDateTimeMillies)
                        .update("note", note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "update successful");
                        startActivity(new Intent(getApplicationContext(), Notes.class));
                        pbar.setVisibility(View.GONE);
                        buttons.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                });
            }}
        });

        builder = new AlertDialog.Builder(EditNote.this);
        //addItem(position, holder);
        imgbtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setCancelable(false);
                builder.setMessage("Are you sure to delete this item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        fb.collection("Users").document("Irish")
                                .collection("My Notes").document(strDateTimeMillies)
                                .delete().addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(EditNote.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                        Notes.notes.remove(position);
                        Notes.notesAdapter.notifyItemRemoved(position);
                        Notes.notesAdapter.notifyItemRangeChanged(position, Notes.notes.size());
                        startActivity(new Intent(getApplicationContext(), Notes.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        //Toast.makeText(context, "You chose no action...", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setMessage("Are you sure to delete this item?");
                alertDialog.show();
            }

        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Notes.class));
            }
        });
    }
}