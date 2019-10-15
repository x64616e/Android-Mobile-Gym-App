package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlanRoutineActivity extends AppCompatActivity {


    private Button doneButton;
    private Button planExercise;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_routine);

        doneButton = (Button) findViewById(R.id.planRoutineDone);
        planExercise = (Button) findViewById(R.id.legDay);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        planExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPlanWorkoutActivity();
            }
        });
    }

    public void toPlanWorkoutActivity(){
        Intent intent = new Intent(this, PlanWorkoutActivity.class);
        startActivity(intent);
    }
}
