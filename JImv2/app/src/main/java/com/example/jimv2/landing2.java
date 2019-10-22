package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class landing2 extends AppCompatActivity {


    private Button calendarButton;
    private Button statisticsButton;
    private ImageButton userIconButton;

    private Button workoutButton;
    private Button excercise1;
    private Button quickAddExer;
    private Button calculatorButton;
    private ImageButton leftArrow;
    private ImageButton rightArrow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing2);
        calendarButton = (Button) findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarScreen();
            }
        });

        statisticsButton = (Button) findViewById(R.id.statistics_button);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticsScreen();
            }
        });

        workoutButton = (Button) findViewById(R.id.workoutButtonLanding);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutScreen();
            }
        });

        leftArrow = (ImageButton) findViewById(R.id.leftArrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousDay();
            }
        });

        rightArrow = (ImageButton) findViewById(R.id.rightArrow);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardDay();
            }
        });

        excercise1 = (Button) findViewById(R.id.excerciseList1);
        excercise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExercise();
            }
        });

//        quickAddExer = (Button) findViewById(R.id.quickAddExerciseButton);
//        quickAddExer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAddExercise();
//            }
//        });

        userIconButton = (ImageButton) findViewById(R.id.userIcon);
        userIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileScreen();
            }
        });


    }

    public void openCalendarScreen(){
        Intent intent = new Intent(this,CalendarActivity.class);
        startActivity(intent);
    }

    public void openStatisticsScreen(){
        Intent intent = new Intent(this,ReportActivity.class);
        startActivity(intent);
    }

    public void openProfileScreen(){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    public void openCalculatorScreen(){
        Intent intent = new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }
    public void openWorkoutScreen(){
        Intent intent = new Intent(this,WorkoutActivityV2.class);
        startActivity(intent);
    }
    public void openAddExercise(){
        Intent intent = new Intent(this,AddExcercise.class);
        startActivity(intent);
    }
    public void openExercise(){
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }
    public void previousDay(){
        Intent intent = new Intent(this,landing2.class);
        startActivity(intent);
    }
    public void forwardDay(){
        Intent intent = new Intent(this,landing.class);
        startActivity(intent);
    }
}