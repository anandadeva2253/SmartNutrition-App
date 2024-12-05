package com.example.nutritionapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        listViewUsers = findViewById(R.id.listViewUsers);
        databaseHelper = new DatabaseHelper(this);

        // Retrieve all users from the database
        List<String> users = databaseHelper.getAllUsers();

        // Set the adapter to display the list of users
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        listViewUsers.setAdapter(adapter);
    }
}
