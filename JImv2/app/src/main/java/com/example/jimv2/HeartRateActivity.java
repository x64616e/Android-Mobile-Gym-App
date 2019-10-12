package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HeartRateActivity extends AppCompatActivity {


    private Button doneButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heartrate);

        doneButton = (Button) findViewById(R.id.heartRateDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });
    }

    public void backToLanding(){
        Intent intent = new Intent(this,WorkoutActivity.class);
        startActivity(intent);
    }
}
