package com.example.gardenry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Notes extends AppCompatActivity {
    FloatingActionButton New_Note;
    RecyclerView Gardener_Recycle;
    static ArrayList<Note> notes;
    FirebaseFirestore fb;
    static NotesAdapter notesAdapter;
    ImageButton info;
    AlertDialog.Builder builder;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        New_Note=findViewById(R.id.fab_gardening_note);
        info = findViewById(R.id.Btn_Help);
        searchView = findViewById(R.id.search_view_notes);
        notes = new ArrayList<>();
        fb = FirebaseFirestore.getInstance();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


        fb.collection("Users").document("Irish")
                .collection("My Notes").orderBy("strDateTimeMillis", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot document : queryDocumentSnapshots){
                            Note note = document.toObject(Note.class);
                            notes.add(note);
                            notesAdapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d("Error: ", e.getMessage());
            }
        });


        Gardener_Recycle=findViewById(R.id.Gardener_Recycle);
        //Gardener_Recycle.setLayoutManager(new LinearLayoutManager(this));
        Gardener_Recycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(getApplicationContext(), notes);
        Gardener_Recycle.setAdapter(notesAdapter);



        New_Note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Toast.makeText(GardeningNote.this, "New Note Added", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(), NewNote.class);
                startActivity(intent);
            }
        });


        builder = new AlertDialog.Builder(Notes.this);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addItem(position, holder);

                        builder.setCancelable(false);
                        builder.setMessage("Gardening notes lets you write your daily experiences"+
                                " so you can refer them later to have a smile over the " +
                                "harvests you made last season or become aware of the last " +
                                "year plant diseases in advance" +
                                "Overall, these can help you compare your progress against earlier experiences" +
                                "\n Happy Gardenry!")

                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setMessage(getString(R.string.why_notes_heading)+getString(R.string.why_notes));
                        alertDialog.show();
                    }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_g_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_new_note_g_notes) {
            startActivity(new Intent(getApplicationContext(), NewNote.class));
            return true;
        }
//        } else if(id == R.id.menu_about_us_my_plants){
//            startActivity(new Intent(getApplicationContext(), AboutUs.class));
//            return true;
//        }
//        else if(id == R.id.menu_request_new_plant_my_plants){
//            startActivity(new Intent(getApplicationContext(), RequestPlant.class));
//        }
        return super.onOptionsItemSelected(item);

}

    private void filter(String newText){
        ArrayList<Note> filteredNotes = new ArrayList<>();

        for(Note note: notes){
            if(note.getNote().toLowerCase().contains(newText))
                filteredNotes.add(note);
        }

        if(filteredNotes.isEmpty()){
            Toast.makeText(getApplicationContext(), "No match found!", Toast.LENGTH_SHORT).show();
        }
        else {
            notesAdapter.filterNotes(filteredNotes);
        }
    }

}