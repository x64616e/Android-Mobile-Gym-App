package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class landing extends AppCompatActivity {


    private Button calendarButton;
    private Button statisticsButton;
    private ImageButton userIconButton;
    private ImageButton leftArrow;
    private ImageButton rightArrow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
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
}
