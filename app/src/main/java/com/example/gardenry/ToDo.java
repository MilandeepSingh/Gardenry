package com.example.gardenry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class ToDo extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    ImageButton info;

    private List<ToDoModel> taskList;  //list of type todomodel to store all our task


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //getSupportActionBar().hide();


        db = new DatabaseHandler(this);
        db.openDatabase();

        // taskList = new ArrayList<>(); //we have defined task list which is declared above

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter (db, ToDo.this);
        tasksRecyclerView.setAdapter(tasksAdapter); //set adapter to recyclerview
        info = findViewById(R.id.btn_help_todo);



        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab_todo);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);

            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addItem(position, holder);

                AlertDialog.Builder builder = new AlertDialog.Builder(ToDo.this);
                builder.setTitle("What to do here");
                builder.setMessage("Add here all your gardening tasks you wanna do soon...\n" +
                        "Check the task after it is done\n" +
                        "Swipe right to edit some tasks\n" +
                        "Swipe left to delete any task");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList); //called setTasks method declared in todoAdapter
        tasksAdapter.notifyDataSetChanged(); //notify adapter that data set changed
    }
}