package com.example.jimv2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PlanWorkoutActivity extends AppCompatActivity {

    private Button doneButton;
    private Button addExercise;
    private static final String TAG = "PlanWorkoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_workout);

        doneButton = (Button) findViewById(R.id.planWorkoutDone);
        addExercise = (Button) findViewById(R.id.addExerciseToWorkout);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddExercise();
            }
        });
    }

    public void toAddExercise(){
        Log.d(TAG, "toAddExercise: called.");

        Intent intent = new Intent(this, AddExercise.class);
        startActivity(intent);
    }
}


