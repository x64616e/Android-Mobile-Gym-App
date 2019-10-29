package com.example.jimv2;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseWorkout extends AppCompatActivity {
    Date currentDate = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
    String formattedDate = df.format(currentDate);
    StringBuilder completeDate = new StringBuilder("complete");

    RecyclerView recyclerView;
    DatabaseReference databaseref;
    FirebaseRecyclerOptions <ExerciseObject> options;
    FirebaseRecyclerAdapter <ExerciseObject, DatabaseHolder> adapter;
    public int numberOfExercises;
    TextView numberBox;
    private static final String TAG = "DatabaseWorkout";



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databaseworkout);
        recyclerView = (RecyclerView) findViewById(R.id.databaseRecyclerView);
        recyclerView.setHasFixedSize(true);
        getCurrentDate();
        completeDate.append(formattedDate);
        String Date = completeDate.toString();
        numberBox = findViewById(R.id.numberOfExercises);
        databaseref = FirebaseDatabase.getInstance().getReference().child(Date);
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();
        adapter = new FirebaseRecyclerAdapter<ExerciseObject, DatabaseHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DatabaseHolder holder, int position, @NonNull ExerciseObject model) {
                findViewById(R.id.loadingBarDB).setVisibility(View.GONE);
                holder.exerciseName.setText(model.getExerciseName());
                holder.exerciseWeight.setText(model.getExerciseWeight() + "");
                holder.exerciseReps.setText(model.getExcerciseReps() + "");
                holder.exerciseSets.setText(model.getExerciseSets() + "");
                //holder.exerciseImage.setText(model.getExerciseImage()+ "");
                holder.exerciseWeight.setText(model.getExerciseWeight()+ "");
                numberBox.setText(String.valueOf(position+1));

            }

            @NonNull
            @Override
            public DatabaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder: called.");
                //numberOfExercises = adapter.getItemCount();

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.databaseexercise,parent,false);
                return new DatabaseHolder(view);

            }
        };

        LinearLayoutManager linearlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearlayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        //numberBox.setText(numberOfExercises + "");

        }

    @Override
    protected void onStart(){
        Log.d(TAG, "onStart: called.");
        super.onStart();
        if(adapter !=null)
            adapter.startListening();
    }

    @Override
    protected void onStop(){
        Log.d(TAG, "onStop: called.");
        if(adapter !=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume(){
        Log.d(TAG, "onResume: called.");
        super.onResume();
        if(adapter !=null)
            adapter.startListening();
    }
    public void getCurrentDate(){
        Log.d(TAG, "getCurrentDate: called.");
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.currentDate);
        date.setText(date_n);
    }
}
