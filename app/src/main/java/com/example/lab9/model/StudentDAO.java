package com.example.lab9.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM student")
    List<Student> getAll();


    @Query("SELECT * FROM student WHERE name LIKE :name")
    Student findByName(String name);

    @Insert
    void insertAll(Student... students);

}
