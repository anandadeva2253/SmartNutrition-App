package com.example.nutritionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateBMIActivity extends AppCompatActivity {

    EditText edtName;
    Button btnCalculate;
    TextView txtResult;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        edtName = findViewById(R.id.edtName);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
        databaseHelper = new DatabaseHelper(this);

        btnCalculate.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(CalculateBMIActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get the height and weight from the database
            float[] details = databaseHelper.getHeightAndWeight(name);

            if (details != null) {
                float height = details[0];  // in cm
                float weight = details[1];  // in kg

                // Calculate BMI
                float bmi = weight / ((height / 100) * (height / 100));
                String bmiCategory = getBMICategory(bmi);

                // Display the result
                txtResult.setText("Your BMI is: " + bmi + "\nYou are: " + bmiCategory);
            } else {
                Toast.makeText(CalculateBMIActivity.this, "Profile not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
