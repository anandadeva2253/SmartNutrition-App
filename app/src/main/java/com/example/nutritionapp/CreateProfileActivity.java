package com.example.nutritionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateProfileActivity extends AppCompatActivity {

    EditText edtName, edtAge, edtGender, edtHeight, edtWeight;
    Button btnSaveProfile;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        databaseHelper = new DatabaseHelper(this);

        btnSaveProfile.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            int age = Integer.parseInt(edtAge.getText().toString());
            String gender = edtGender.getText().toString();
            float height = Float.parseFloat(edtHeight.getText().toString());
            float weight = Float.parseFloat(edtWeight.getText().toString());

            // Insert the profile into the database
            boolean success = databaseHelper.insertProfile(name, age, gender, height, weight);
            if (success) {
                Toast.makeText(CreateProfileActivity.this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CreateProfileActivity.this, "Failed to save profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
