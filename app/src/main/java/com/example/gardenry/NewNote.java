package com.example.gardenry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import static android.content.ContentValues.TAG;

public class NewNote extends AppCompatActivity {
    EditText New_Note;
    Button New_Note_Submit,New_Note_Cancel;
    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        New_Note=findViewById(R.id.edt_note_new_note);
        New_Note_Submit=findViewById(R.id.New_Note_Submit);
        New_Note_Cancel=findViewById(R.id.New_Note_Cancel);
        fb = FirebaseFirestore.getInstance();

        New_Note_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                long timeMilli = date.getTime();
                String note = New_Note.getText().toString();
                String strDateTimeMillies = Long.toString(timeMilli);
                if(note.trim().isEmpty())
                    Toast.makeText(NewNote.this, "Please write some note!", Toast.LENGTH_SHORT).show();
                else{
                    View pbar = findViewById(R.id.ll_pbar_add_new_note);
                    View buttons = findViewById(R.id.ll_buttons_new_note);
                    pbar.setVisibility(View.VISIBLE);
                    buttons.setVisibility(View.GONE);
                fb.collection("Users").document(LOGGED_USER.email)
                        .collection("My Notes").document(strDateTimeMillies)
                        .set(new Note(strDateTimeMillies, note))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Success");
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
            }
            }
        });

        New_Note_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Notes.class));
            }
        });
    }
}