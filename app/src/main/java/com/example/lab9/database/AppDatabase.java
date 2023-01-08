package com.example.lab9.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab9.model.Student;
import com.example.lab9.model.StudentDAO;

@Database(entities = {Student.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
