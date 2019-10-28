package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {

    private Button button;
    private static final String TAG = "ReportActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        button = (Button) findViewById(R.id.reportDoneButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });
    }
    public void backToLanding(){
        Log.d(TAG, "backToLanding: called.");

        Intent intent = new Intent(this,landing.class);
        startActivity(intent);
    }
}
