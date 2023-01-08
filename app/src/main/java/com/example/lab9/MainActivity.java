package com.example.lab9;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.android_orm_lab9.R;
import com.example.lab9.database.AppDatabase;
import com.example.lab9.model.Student;
import com.example.lab9.model.StudentDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addBtn, searchBtn;
    private EditText nameEditText, ageEditText, searchEditText;
    private CheckBox studyingCheckBox;
    private ListView listView;
    private ArrayList<String> dynamicList;
    private ArrayAdapter<String> listAdapter;
    private AppDatabase db;
    private StudentDAO studentDAO;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "students-db-1").allowMainThreadQueries().build();
        studentDAO = db.studentDAO();
        dynamicList = new ArrayList<>();
        listView = findViewById(R.id.resultListView);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dynamicList);
        listView.setAdapter(listAdapter);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        searchEditText = findViewById(R.id.nameInput);
        studyingCheckBox = findViewById(R.id.isStudyingCB);
        addBtn = findViewById(R.id.saveButton);
        searchBtn = findViewById(R.id.searchButton);
    }

    public void addButtonListener(View view) {
        String ageValue = ageEditText.getText().toString();
        int age = Integer.parseInt(ageValue);
        boolean studying = studyingCheckBox.isChecked();
        String name = nameEditText.getText().toString();

        if (ageValue.isEmpty() || name.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please add age and name", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Student student = new Student(age, name, studying);
            studentDAO.insertAll(student);
            Toast saveSuccessToast = Toast.makeText(getApplicationContext(), "Added to database", Toast.LENGTH_SHORT);
            saveSuccessToast.show();
        }
    }
    public void showAllButton(View view) {
        List<Student> students = studentDAO.getAll();
        if (students.size() > 0) {
            dynamicList.clear();
            listAdapter.notifyDataSetChanged();
            for (Student student : students) {
                dynamicList.add(student.toString());
            }
            listAdapter.notifyDataSetChanged();
        }
    }


    public void searchButtonListener(View view) {
        String name = searchEditText.getText().toString();
        if (name.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Student student = studentDAO.findByName(name);
            dynamicList.clear();
            if(student != null) {
                dynamicList.add(student.toString());
                listAdapter.notifyDataSetChanged();
            }
        }
    }
}

