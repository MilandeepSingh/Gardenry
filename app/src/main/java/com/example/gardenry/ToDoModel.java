package com.example.gardenry;

public class ToDoModel {
    private int id, status;         //each task have 3 attribute using id we can work on database and execute queries
    private String task;            // actual text of the task           status boolean var but in sql we dont have boolean datatype so we use int type.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
