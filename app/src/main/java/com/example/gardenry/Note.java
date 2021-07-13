package com.example.gardenry;

public class Note {
    String strDateTimeMillis;
    String note;

    public String getStrDateTimeMillis() {
        return strDateTimeMillis;
    }

    public void setStrDateTimeMillis(String strDateTimeMillis) {
        this.strDateTimeMillis = strDateTimeMillis;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Note() {
    }

    public Note(String strDateTimeMillis, String note) {
        this.strDateTimeMillis = strDateTimeMillis;
        this.note = note;
    }
}
