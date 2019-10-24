package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class ExerciseActivity extends AppCompatActivity {


    private Button doneButton;
    private Button setExercise;
    private Button calculatorButton;
    public TextView exerciseName;
    public ImageView imageview;
    public EditText exerciseSets;
    public EditText exerciseReps;
    public EditText exerciseWeight;

    DatabaseReference databaseExercise;
    private static final long START_TIME_IN_MILLS = 30000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    public String name;
    private long mTimeLeftInMillis = START_TIME_IN_MILLS;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        Intent intent = getIntent();

        databaseExercise = FirebaseDatabase.getInstance().getReference("exercises");

        ExerciseObject exercise = intent.getParcelableExtra("exercise");
        int imageRes = exercise.getmImageResource();
        name = exercise.getmText();

        imageview = findViewById(R.id.exercise_pic_exerciseAct);
        imageview.setImageResource(imageRes);
        exerciseName = findViewById(R.id.exercise_name_exerciseAct);
        exerciseName.setText(name);
        exerciseReps = findViewById(R.id.exercisereps);
        exerciseSets = findViewById(R.id.exercisesets);
        exerciseWeight = findViewById(R.id.exerciseweight);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mTimerRunning){
                    pauseTimer();
                }else
                    startTimer();
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

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

        setExercise = (Button) findViewById(R.id.setExercise);
        setExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addExercise();
            }
        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis/1000) / 60;
        int seconds = (int) (mTimeLeftInMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void backToWorkout(){
        finish();
    }
    public void openCalculatorScreen(){
        Intent intent = new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }

    private void addExercise(){
        String name = exerciseName.getText().toString().trim();
        String sets = exerciseSets.getText().toString();
        int exerciseSets = Integer.parseInt(sets);
        String weight = exerciseWeight.getText().toString();
        int exerciseWeight = Integer.parseInt(weight);
        String reps = exerciseReps.getText().toString();
        int exerciseReps = Integer.parseInt(reps);

        if(!TextUtils.isEmpty(sets) && !TextUtils.isEmpty(weight)&& !TextUtils.isEmpty(reps)){
            String id = databaseExercise.push().getKey();
            ExerciseObject exercise = new ExerciseObject(1,name,exerciseSets,1,exerciseReps,exerciseWeight);
            databaseExercise.child(id).setValue(exercise);
            Toast.makeText(this, "Exercise Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please Enter the Info", Toast.LENGTH_LONG).show();
        }
    }
}
