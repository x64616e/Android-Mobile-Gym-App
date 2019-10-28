package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CompletedDayActivity extends AppCompatActivity {

    private static final String TAG = "CompletedDayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_workout);
    }
}
