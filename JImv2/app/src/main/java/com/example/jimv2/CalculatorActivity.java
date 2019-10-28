package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalculatorActivity extends AppCompatActivity {
    private Button doneButton;
    private static final String TAG = "CalculatorActivity";
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        doneButton = (Button) findViewById(R.id.calcDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });
    }
    public void backToLanding(){
    finish();
    }
}
