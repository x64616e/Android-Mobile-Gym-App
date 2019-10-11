package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarActivity extends AppCompatActivity {

    private Button doneButton;
    private Button planRoutine;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        doneButton = (Button) findViewById(R.id.backButtonCalendar);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });

        planRoutine = (Button) findViewById(R.id.planRoutineButton);
        planRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlanRoutine();
            }
        });
    }
    public void backToLanding(){
        Intent intent = new Intent(this,landing.class);
        startActivity(intent);
    }
    public void openPlanRoutine(){
        Intent intent = new Intent(this,PlanRoutineActivity.class);
        startActivity(intent);
    }
}
