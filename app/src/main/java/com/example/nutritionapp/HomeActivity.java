package com.example.nutritionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnCreateProfile = findViewById(R.id.btnCreateProfile);
        Button btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        Button btnCalorieRecommendation = findViewById(R.id.btnCalorieRecommendation);
        Button btnViewUsers = findViewById(R.id.btnViewUsers);  // New Button to view users

        // Set up onClick listener for "Create Profile" button
        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateProfileActivity to enter user details
                Intent intent = new Intent(HomeActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set up onClick listener for "View Users" button
        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ViewUsersActivity to display all users
                Intent intent = new Intent(HomeActivity.this, ViewUsersActivity.class);
                startActivity(intent);
            }
        });

        // Set up onClick listener for "Calculate BMI" button
        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CalculateBMIActivity to calculate the BMI
                Intent intent = new Intent(HomeActivity.this, CalculateBMIActivity.class);
                startActivity(intent);
            }
        });

        // Set up onClick listener for "Calorie Recommendation" button
        btnCalorieRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CalorieRecommendationActivity to calculate daily calorie intake
                Intent intent = new Intent(HomeActivity.this, CalorieRecommendationActivity.class);
                startActivity(intent);
            }
        });
    }
}
