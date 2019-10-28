package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CalendarActivity extends AppCompatActivity {

    private Button doneButton;
    private Button planRoutine;
    private Button completedButton;
    public static final String TAG = "CalanderActivity";

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

        completedButton = (Button) findViewById(R.id.completedDays);
        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCompletedDay();
            }
        });
    }
    public void backToLanding(){
        Log.d(TAG, "backToLanding: called.");

        Intent intent = new Intent(this,landing.class);
        startActivity(intent);
    }
    public void openPlanRoutine(){
        Log.d(TAG, "openPlanRoutine: called.");

        Intent intent = new Intent(this,PlanRoutineActivity.class);
        startActivity(intent);
    }
    public void openCompletedDay(){
        Log.d(TAG, "openCompletedDay: called.");

        Intent intent = new Intent(this,CompletedDayActivity.class);
        startActivity(intent);
    }
}
