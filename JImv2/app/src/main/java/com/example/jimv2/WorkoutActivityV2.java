package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;

public class WorkoutActivityV2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private WorkoutAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<ExerciseObject> workoutList = new ArrayList<>();
    private Button doneButton;
    private Button addExercise;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutv3);
        populateArray();
        buildRecylcerView();
        doneButton = (Button) findViewById(R.id.doneButtonWorkout);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLanding();
            }
        });

        addExercise = (Button) findViewById(R.id.addExerciseButton);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });
    }
    public void backToLanding(){
        finish();
    }
    public void addExercise(){
        Intent intent = new Intent(this,AddExcercise.class);
        startActivity(intent);
    }
    public void launchExercise(){
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }

    public void populateArray(){
        workoutList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Pile Squat",1));
        workoutList.add(new ExerciseObject(R.drawable.ic_alternate_bicep_curl_1, "Curl",2));
        workoutList.add(new ExerciseObject(R.drawable.ic_leg_press_2_1024x670, "Leg Press",3));
        workoutList.add(new ExerciseObject(R.drawable.ic_jm_press_2, "Bench Press",4));
        workoutList.add(new ExerciseObject(R.drawable.ic_triceps_kickback_2, "Tricep Kickback",5));
        workoutList.add(new ExerciseObject(R.drawable.ic_good_mornings_1, "Good Mornings",6));
        workoutList.add(new ExerciseObject(R.drawable.ic_hammer_curls_with_rope_2, "Hammer Curl",7));
        workoutList.add(new ExerciseObject(R.drawable.ic_preacher_hammer_curl_1, "Preacher Curl",8));
        workoutList.add(new ExerciseObject(R.drawable.ic_tricep_dips_1, "Tricept Dip",9));
        workoutList.add(new ExerciseObject(R.drawable.ic_prone_incline_biceps_curl_2, "Prone Curls",10));
        workoutList.add(new ExerciseObject(R.drawable.ic_overhead_squat_2, "Overhead Squat",11));
        workoutList.add(new ExerciseObject(R.drawable.ic_lunges_2_2, "Lunges",12));
        workoutList.add(new ExerciseObject(R.drawable.ic_crunches_2, "Crunches",13));
        workoutList.add(new ExerciseObject(R.drawable.ic_chin_ups_1, "Chin Ups",14));
        workoutList.add(new ExerciseObject(R.drawable.ic_decline_crunch_2, "Decline Crunch",15));
    }
    public void buildRecylcerView(){
        mRecyclerView = findViewById(R.id.exerciseRecycleView); // view
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        mAdapter = new WorkoutAdapter(workoutList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new WorkoutAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                workoutList.get(position);
                launchExercise();
            }
        });
    }
}