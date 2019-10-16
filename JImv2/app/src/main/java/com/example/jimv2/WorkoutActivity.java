package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WorkoutActivity extends AppCompatActivity {

    private Button doneButton;
    private Button addExercise;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout2);

        ImageView mImageView1 = (ImageView)findViewById(R.id.exercise_pic2);
        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExercise();
            }
        });

        ImageView mImageView2 = (ImageView)findViewById(R.id.exercise_pic3);
        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExercise();
            }
        });

        ImageView mImageView3 = (ImageView)findViewById(R.id.exercise_pic4);
        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExercise();
            }
        });

        ImageView mImageView4 = (ImageView)findViewById(R.id.exercise_pic1);
        mImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExercise();
            }
        });


        ImageView mImageView = (ImageView)findViewById(R.id.heartIcon);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHearRate();
            }
        });

        doneButton = (Button) findViewById(R.id.doneButtonWorkout);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });

        addExercise = (Button) findViewById(R.id.addExerciseButton);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });
    }

    public void openExercise(){
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }
    public void openHearRate(){
        Intent intent = new Intent(this,HeartRateActivity.class);
        startActivity(intent);
    }

    public void backToLanding(){
        Intent intent = new Intent(this,landing.class);
        startActivity(intent);
    }
    public void addExercise(){
        Intent intent = new Intent(this,AddExcercise.class);
        startActivity(intent);
    }
}
