package com.example.jimv2;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseWorkout extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseref;
    FirebaseRecyclerOptions <ExerciseObject> options;
    FirebaseRecyclerAdapter <ExerciseObject, DatabaseHolder> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databaseworkout);
        recyclerView = (RecyclerView) findViewById(R.id.databaseRecyclerView);
        recyclerView.setHasFixedSize(true);

        databaseref = FirebaseDatabase.getInstance().getReference().child("exercises");

        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();

        adapter = new FirebaseRecyclerAdapter<ExerciseObject, DatabaseHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DatabaseHolder holder, int position, @NonNull ExerciseObject model) {
                findViewById(R.id.loadingBarDB).setVisibility(View.GONE);
              holder.exerciseName.setText(model.getExerciseName());
              holder.exerciseWeight.setText(model.getExerciseWeight() + "");
            holder.exerciseReps.setText(model.getExcerciseReps() + "");
            holder.exerciseSets.setText(model.getExerciseSets() + "");
            holder.exerciseImage.setText(model.getExerciseImage()+ "");
             holder.exerciseWeight.setText(model.getExerciseWeight()+ "");
            }

            @NonNull
            @Override
            public DatabaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.databaseexercise,parent,false);
                return new DatabaseHolder(view);
            }
        };

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearlayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);


        }

    @Override
    protected void onStart(){
        super.onStart();
        if(adapter !=null)
            adapter.startListening();
    }

    @Override
    protected void onStop(){
        if(adapter !=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(adapter !=null)
            adapter.startListening();
    }

}
