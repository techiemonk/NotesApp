package com.example.notesapp.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "notes")
public class Note implements Serializable {
    // Fields, constructor and methods
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String content;
    private long date;
//
//    public Note() {
//        // No-arg constructor for Room
//    }
//    @Ignore
//    public Note(String title, String content, long date) {
//        this.title = title;
//        this.content = content;
//        this.date = date;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @TypeConverter
    public static long dateToTimestamp(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date timestampToDate(long timestamp) {
        return new Date(timestamp);
    }
}