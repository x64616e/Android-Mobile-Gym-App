package com.example.jimv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class landing extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseref;
    FirebaseRecyclerOptions <ExerciseObject> options;
    FirebaseRecyclerAdapter <ExerciseObject, DatabaseHolder> adapter;
    Date currentDate = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
    String formattedDate = df.format(currentDate);
    private static final String TAG = "Landing";

    private Button calendarButton;
    private Button statisticsButton;
    private ImageButton userIconButton;
    private Button workoutButton;
    private Button excercise1;
    private ImageButton leftArrow;
    private ImageButton rightArrow;
    private Button friendsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
        getCurrentDate();
        recyclerView = (RecyclerView) findViewById(R.id.landingRecyclerView);
        recyclerView.setHasFixedSize(true);

        //****
        //Recycler View from Database Query
        //***
        databaseref = FirebaseDatabase.getInstance().getReference().child(formattedDate);
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();
        adapter = new FirebaseRecyclerAdapter<ExerciseObject, DatabaseHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DatabaseHolder holder, int position, @NonNull ExerciseObject model) {
//                findViewById(R.id.loadingBarDB).setVisibility(View.GONE);
                holder.exerciseName.setText(model.getExerciseName());
            }

            @NonNull
            @Override
            public DatabaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder: called.");

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landingexercise,parent,false);
                return new DatabaseHolder(view);
            }
        };

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearlayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);



        //
        //
        //***

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

        workoutButton = (Button) findViewById(R.id.workoutButtonLanding);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutScreen();
            }
        });

        leftArrow = (ImageButton) findViewById(R.id.leftArrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousDay();
            }
        });

        rightArrow = (ImageButton) findViewById(R.id.rightArrow);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardDay();
            }
        });



        friendsButton = (Button) findViewById(R.id.friends_button);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFriends();
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

    public void getCurrentDate(){
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.currentDate);
        date.setText(date_n);
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

    public void openCalculatorScreen(){
        Intent intent = new Intent(this,CalculatorActivity.class);
        startActivity(intent);
    }
    public void openWorkoutScreen(){
        Intent intent = new Intent(this,WorkoutActivityV2.class);
        startActivity(intent);
    }
    public void openAddExercise(){
        Intent intent = new Intent(this,AddExcercise.class);
        startActivity(intent);
    }
    public void openExercise(){
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }
    public void previousDay(){
        Intent intent = new Intent(this,landing2.class);
        startActivity(intent);
    }
    public void forwardDay(){
        Intent intent = new Intent(this,landing3.class);
        startActivity(intent);
    }
    public void openFriends(){
        Intent intent = new Intent(this,DatabaseWorkout.class);
        startActivity(intent);
    }
}
