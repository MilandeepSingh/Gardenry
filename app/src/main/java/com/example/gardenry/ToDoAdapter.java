package com.example.gardenry;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private ToDo activityToDo;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, ToDo activityToDo){
        this.db =  db;
        this.activityToDo = activityToDo;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull final ViewHolder holder, int position){ //bind data to the view
        db.openDatabase();
        final ToDoModel item = todoList.get(position);    //get position from interface and search item in todolist

        holder.task.setText(item.getTask());        //showing on screen after getting task from item

        holder.task.setChecked(toBoolean(item.getStatus())); //

        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(), 1); //if checkbox is checked
                }
                else{
                    db.updateStatus(item.getId(), 0); //if not checked
                }
            }
        });
    }

    private boolean toBoolean(int n){
        return n!=0;
    } //return true if no not equal to 0 its a helper function used

    @Override
    public int getItemCount(){
        return todoList.size();              //retrieve size from todolist
    }  //required so that the recycler view know how many items need to print

    public Context getContext(){
        return activityToDo;
    }  //why

    public void setTasks(List<ToDoModel> todoList){ //able to set task
        this.todoList = todoList;           //passing list in adapter
        notifyDataSetChanged();           //so recyclerview is updated
    }


    public void deleteItem(int position){               // deleting item
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());        //will delete task from database
        todoList.remove(position);          //removed from the list also
        notifyItemRemoved(position);        //update recycler view after item is deleted
    }

                                            //edit task
    public void editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);      //used to pass a bundle
        fragment.show(activityToDo.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox); //here we hold checkbox
        }

    }

}
