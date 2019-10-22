package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseActivity extends AppCompatActivity {


    private Button doneButton;
    private Button calculatorButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        Intent intent = getIntent();

        ExerciseObject exercise = intent.getParcelableExtra("exercise");
        int imageRes = exercise.getmImageResource();
        String name = exercise.getmText();
        ImageView imageview = findViewById(R.id.exercise_pic_exerciseAct);
        imageview.setImageResource(imageRes);
        TextView exerciseName = findViewById(R.id.exercise_name_exerciseAct);
        exerciseName.setText(name);

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