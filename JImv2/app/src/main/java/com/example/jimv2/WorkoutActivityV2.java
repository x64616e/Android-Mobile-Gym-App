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

    private Button doneButton;
    private Button addExercise;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutv3);
        final ArrayList<ExerciseObject> exerciseList = new ArrayList<>();

        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Pile Squat",1));
        exerciseList.add(new ExerciseObject(R.drawable.ic_alternate_bicep_curl_1, "Curl",2));
        exerciseList.add(new ExerciseObject(R.drawable.ic_leg_press_2_1024x670, "Leg Press",3));
        exerciseList.add(new ExerciseObject(R.drawable.ic_jm_press_2, "Bench Press",4));
        exerciseList.add(new ExerciseObject(R.drawable.ic_triceps_kickback_2, "Tricep Kickback",5));
        exerciseList.add(new ExerciseObject(R.drawable.ic_good_mornings_1, "Good Mornings",6));
        exerciseList.add(new ExerciseObject(R.drawable.ic_hammer_curls_with_rope_2, "Hammer Curl",7));
        exerciseList.add(new ExerciseObject(R.drawable.ic_preacher_hammer_curl_1, "Preacher Curl",8));
        exerciseList.add(new ExerciseObject(R.drawable.ic_tricep_dips_1, "Tricept Dip",9));
        exerciseList.add(new ExerciseObject(R.drawable.ic_prone_incline_biceps_curl_2, "Prone Curls",10));
        exerciseList.add(new ExerciseObject(R.drawable.ic_overhead_squat_2, "Overhead Squat",11));
        exerciseList.add(new ExerciseObject(R.drawable.ic_lunges_2_2, "Lunges",12));
        exerciseList.add(new ExerciseObject(R.drawable.ic_crunches_2, "Crunches",13));
        exerciseList.add(new ExerciseObject(R.drawable.ic_chin_ups_1, "Chin Ups",14));
        exerciseList.add(new ExerciseObject(R.drawable.ic_decline_crunch_2, "Decline Crunch",15));


        mRecyclerView = findViewById(R.id.exerciseRecycleView); // view
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        mAdapter = new WorkoutAdapter(exerciseList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new WorkoutAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                exerciseList.get(position);
                launchExercise();
            }
        });

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

}
