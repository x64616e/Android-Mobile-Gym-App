package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class AddExcercise extends AppCompatActivity {
    public  ArrayList<ExerciseObject> exerciseList;
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button doneButton;
    protected void onCreate(Bundle savedInstanceState) {
        exerciseList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciseaddv2);
        populateList();
        EditText editText = findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            filter(s.toString());
            }
        });


        mRecyclerView = findViewById(R.id.recyclerView1); // view
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new ExerciseAdapter(exerciseList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        doneButton = (Button) findViewById(R.id.doneButtonAddExercise);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void filter(String text){
        ArrayList<ExerciseObject> filteredList = new ArrayList<>();
        for(ExerciseObject item:exerciseList){
            if(item.getmText().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);

    }

    private void populateList(){
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
    }

}
