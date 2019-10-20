package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ExerciseActivity extends AppCompatActivity {


    private Button doneButton;
    private Button calculatorButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        doneButton = (Button) findViewById(R.id.exerciseDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToWorkout();
            }
        });

        calculatorButton = (Button) findViewById(R.id.weightCalcButton);
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalculatorScreen();
            }
        });

    }
    public void backToWorkout(){
    finish();
    }
    public void openCalculatorScreen(){
        Intent intent = new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }
}