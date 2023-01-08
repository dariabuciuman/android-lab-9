package com.example.lab9.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    long uid;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "isStudying")
    private boolean isStudying;

    public Student() {
    }

    public Student(int age, String name, boolean isStudying) {
        this.age = age;
        this.name = name;
        this.isStudying = isStudying;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStudying() {
        return isStudying;
    }

    public void setStudying(boolean studying) {
        isStudying = studying;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " of age " + age + " is studying: "+ isStudying;
    }
}
