package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlanRoutineActivity extends AppCompatActivity {


    private Button doneButton;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_routine);

        doneButton = (Button) findViewById(R.id.planRoutineDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //public void backToCalendar(){
    //    Intent intent = new Intent(this,CalendarActivity.class);
    //    startActivity(intent);
    //}
}
