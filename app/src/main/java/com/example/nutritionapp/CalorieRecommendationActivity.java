package com.example.nutritionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalorieRecommendationActivity extends AppCompatActivity {

    private EditText edtName;
    private Spinner spinnerActivityFactor, spinnerGoal;
    private TextView txtCalorieResult;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_recommendation);

        edtName = findViewById(R.id.edtName);
        spinnerActivityFactor = findViewById(R.id.spinnerActivityFactor);
        spinnerGoal = findViewById(R.id.spinnerGoal);
        txtCalorieResult = findViewById(R.id.txtCalorieResult);
        databaseHelper = new DatabaseHelper(this);

        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(this,
                R.array.activity_factors, android.R.layout.simple_spinner_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityFactor.setAdapter(activityAdapter);

        ArrayAdapter<CharSequence> goalAdapter = ArrayAdapter.createFromResource(this,
                R.array.goals, android.R.layout.simple_spinner_item);
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoal.setAdapter(goalAdapter);

        findViewById(R.id.btnCalculateCalories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                float[] details = databaseHelper.getHeightAndWeight(name);

                if (details != null) {
                    float height = details[0];
                    float weight = details[1];
                    String activityFactor = spinnerActivityFactor.getSelectedItem().toString();
                    String goal = spinnerGoal.getSelectedItem().toString();

                    float bmr = 10 * weight + 6.25f * height - 5 * 25; // Example age 25
                    float calorieRequirement = getActivityMultiplier(activityFactor) * bmr;
                    calorieRequirement = adjustForGoal(calorieRequirement, goal);
                    txtCalorieResult.setText("Calorie Requirement: " + calorieRequirement + " kcal");
                } else {
                    Toast.makeText(CalorieRecommendationActivity.this, "Profile Not Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private float getActivityMultiplier(String activityFactor) {
        switch (activityFactor) {
            case "Sedentary": return 1.2f;
            case "Lightly Active": return 1.375f;
            case "Moderately Active": return 1.55f;
            case "Very Active": return 1.725f;
            case "Extremely Active": return 1.9f;
            default: return 1.0f;
        }
    }

    private float adjustForGoal(float calorieRequirement, String goal) {
        switch (goal) {
            case "Weight Loss": return calorieRequirement - 500;
            case "Weight Gain": return calorieRequirement + 500;
            default: return calorieRequirement;
        }
    }
}
