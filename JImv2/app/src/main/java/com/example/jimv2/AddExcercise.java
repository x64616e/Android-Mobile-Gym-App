package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;

public class AddExcercise extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button doneButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciseaddv2);
        ArrayList<ExerciseObject> exerciseList = new ArrayList<>();

        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Line 1"));


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new ExerciseAdapter(exerciseList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        doneButton = (Button) findViewById(R.id.doneButtonAddExercise);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoWorkout();
            }
        });
    }
    public void addtoWorkout(){
        Intent intent = new Intent(this,WorkoutActivity.class);
        finish();
    }
}
