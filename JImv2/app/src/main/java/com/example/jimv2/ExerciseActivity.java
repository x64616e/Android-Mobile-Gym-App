package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExerciseActivity extends AppCompatActivity {

    Date dateCurrentlyViewing = Calendar.getInstance().getTime();
    StringBuilder completeDate = new StringBuilder("complete");
    private Button doneButton;
    private Button setExercise;
    private Button calculatorButton;
    public TextView exerciseName;
    public ImageView imageview;
    public EditText exerciseSets;
    public EditText exerciseReps;
    public EditText exerciseWeight;

    DatabaseReference databaseExercise;

    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    public String name;

    private long mStartTimeInMillis;
    private long mTimeLeftInMillis = mStartTimeInMillis;
    private long mEndTime;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        Intent intent = getIntent();

        if(getIntent().hasExtra("com.example.jimv2.PASSDATE")) {
            long passedDate = getIntent().getExtras().getLong("com.example.jimv2.PASSDATE");
            dateCurrentlyViewing.setTime(passedDate);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        userID = userID.substring(0, Math.min(userID.length(), 6));

        SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
        String formattedDate = df.format(dateCurrentlyViewing);
        completeDate.append(userID);
        completeDate.append(formattedDate);
        String Date = completeDate.toString();
        databaseExercise = FirebaseDatabase.getInstance().getReference(Date);

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

        mEditTextInput = findViewById(R.id.edit_txt_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditTextInput.getText().toString();
                if ( input.length() == 0){
                    Toast.makeText(ExerciseActivity.this, "Cant Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 1000;
                if ( millisInput == 0){
                    Toast.makeText(ExerciseActivity.this, "Enter Positive Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                mEditTextInput.setText("");

            }
        });

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

    private void setTime( long milliseconds){
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
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
                updateWatchInterface();


            }
        }.start();

        mTimerRunning = true;
        updateWatchInterface();

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis/1000) / 60;
        int seconds = (int) (mTimeLeftInMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (mTimerRunning){
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility((View.INVISIBLE));
            mButtonStartPause.setText("Pause");
        }else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");

            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            }else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if (mTimeLeftInMillis < mStartTimeInMillis){
                mButtonReset.setVisibility(View.VISIBLE);
            }else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if ( view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences( "prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer != null ){
            mCountDownTimer.cancel();
        }
    }
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences( "prefs", MODE_PRIVATE);

        //mStartTimeInMillis = prefs.getLong("millisleft", 60000);
        //mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        //mTimerRunning = prefs.getBoolean("timerRunning", false );

        updateCountDownText();
    }
    public void backToWorkout(){
        finish();
    }
    public void openCalculatorScreen(){
        Intent intent = new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }

    private void addExercise() {
        try {
            String name = exerciseName.getText().toString().trim();
            String sets = exerciseSets.getText().toString();
            int exerciseSets = Integer.parseInt(sets);
            String weight = exerciseWeight.getText().toString();
            int exerciseWeight = Integer.parseInt(weight);
            String reps = exerciseReps.getText().toString();
            int exerciseReps = Integer.parseInt(reps);

            if (!TextUtils.isEmpty(sets) && !TextUtils.isEmpty(weight) && !TextUtils.isEmpty(reps)) {
                String id = databaseExercise.push().getKey();
                ExerciseObject exercise = new ExerciseObject(1, name, exerciseSets, 1, exerciseReps, exerciseWeight);
                databaseExercise.child(id).setValue(exercise);
                Toast.makeText(this, "Statistics Recorded", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please Enter the Info", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        catch (NumberFormatException nfe){
            Toast.makeText(this, "Please Enter the Info", Toast.LENGTH_LONG).show();
            nfe.printStackTrace();
        }
    }
}
