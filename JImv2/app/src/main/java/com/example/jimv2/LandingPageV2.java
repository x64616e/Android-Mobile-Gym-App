package com.example.jimv2;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class LandingPageV2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    private static final String TAG = "Landing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            Log.d(TAG, "No Saved Instance State: default fragment created.");

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WorkoutActivityFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_workout);
        }

        Calendar calendar = Calendar.getInstance();
        String currentDate = java.text.DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.toolbar_text_view_date);
        textViewDate.setText(currentDate);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_calendar:
                Log.d(TAG, "onNavigationItemSelected: nav_calendar clicked.");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalendarFragment()).commit();
                break;
            case R.id.nav_workout:
                Log.d(TAG, "onNavigationItemSelected: nav_workout clicked.");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WorkoutActivityFragment()).commit();
                break;
            case R.id.nav_profile:
                Log.d(TAG, "onNavigationItemSelected: nav_profile clicked.");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_routine:
                Log.d(TAG, "onNavigationItemSelected: nav_routine clicked.");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlanRoutineFragment()).commit();
                break;
            case R.id.nav_report:
                Log.d(TAG, "onNavigationItemSelected: nav_report clicked.");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReportFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}

