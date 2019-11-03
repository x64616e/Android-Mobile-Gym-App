package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddExercise extends AppCompatActivity {

    Date dateCurrentlyViewing = Calendar.getInstance().getTime();


    DatabaseReference databaseref;
    public ArrayList<ExerciseObject> exerciseList;
    public ArrayList<ExerciseObject> sendToWorkout;
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    DatabaseReference databaseExercise;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "AddExercise";

    private Button doneButton;

    protected void onCreate(Bundle savedInstanceState) {
        exerciseList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciseaddv2);
        populateList();
        EditText editText = findViewById(R.id.search);
        buildRecylcerView();

        if(getIntent().hasExtra("com.example.jimv2.PASSDATE")) {
            long passedDate = getIntent().getExtras().getLong("com.example.jimv2.PASSDATE");
            dateCurrentlyViewing.setTime(passedDate);
        }

        SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
        String formattedDate = df.format(dateCurrentlyViewing);
        databaseref = FirebaseDatabase.getInstance().getReference().child(formattedDate);

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

        doneButton = (Button) findViewById(R.id.doneButtonAddExercise);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO
//                Intent intent = new Intent(AddExcercise.this,WorkoutActivityV2.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("list", (ArrayList<ExerciseObject>)sendToWorkout);
//                intent.putExtras(bundle);
//                startActivity(intent);

                finish();


            }
        });
    }

    private void filter(String text) {
        ArrayList<ExerciseObject> filteredList = new ArrayList<>();
        for (ExerciseObject item : exerciseList) {
            if (item.getmText().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);

    }

    private void populateList() {
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Pile Squat", 1, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_alternate_bicep_curl_1, "Curl", 2, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_leg_press_2_1024x670, "Leg Press", 3, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_jm_press_2, "Bench Press", 4, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_triceps_kickback_2, "Tricep Kickback", 5, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_good_mornings_1, "Good Mornings", 6, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_hammer_curls_with_rope_2, "Hammer Curl", 7, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_preacher_hammer_curl_1, "Preacher Curl", 8, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_tricep_dips_1, "Tricept Dip", 9, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_prone_incline_biceps_curl_2, "Prone Curls", 10, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_overhead_squat_2, "Overhead Squat", 11, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_lunges_2_2, "Lunges", 12, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_crunches_2, "Crunches", 13, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_chin_ups_1, "Chin Ups", 14, 0, 0, 0));
        exerciseList.add(new ExerciseObject(R.drawable.ic_decline_crunch_2, "Decline Crunch", 15, 0, 0, 0));
    }

    public void buildRecylcerView() {
        Log.d(TAG, "buildRecyclerView: called.");

        mRecyclerView = findViewById(R.id.recyclerView1); // view
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new ExerciseAdapter(exerciseList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new ExerciseAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                ExerciseObject exercise = new ExerciseObject();
                exercise = exerciseList.get(position);
                int number = exercise.getExerciseNumber();
                String id = Integer.toString(number);
                databaseref.child(id).setValue((ExerciseObject)exercise);

                finish();

            }
        });
    }

    }

