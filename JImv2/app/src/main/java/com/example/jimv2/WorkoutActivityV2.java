package com.example.jimv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.widget.ImageButton;
import java.util.Date;
import java.util.Calendar;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkoutActivityV2 extends AppCompatActivity {

    Date currentDate = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
    String formattedDate = df.format(currentDate);

    DatabaseReference databaseref;
    FirebaseRecyclerOptions<ExerciseObject> options;
    FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder> adapter;

    private RecyclerView mRecyclerView;
    private WorkoutAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<ExerciseObject> workoutList = new ArrayList<>();
    public ArrayList<ExerciseObject> workouts = new ArrayList<>();
    DatabaseReference databaseExercise;
    private Button doneButton;
    private Button addExercise;
    private ImageView heartIcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutv3);
        //populateArray();
        buildRecylcerView();
        Intent intent = getIntent();
        ExerciseObject exercise = intent.getParcelableExtra("exercise");
        databaseref = FirebaseDatabase.getInstance().getReference().child(formattedDate);
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();

        try {
            if (!exercise.equals(null)) {
                workouts.add(exercise);
                saveToDatabase();
            }
        }
        catch (NullPointerException nfe){
            nfe.printStackTrace();
        }



//        databaseExercise = FirebaseDatabase.getInstance().getReference(formattedDate);
//        saveToDatabase();


        adapter = new FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull WorkoutHolder holder, final int position, @NonNull final ExerciseObject model) {
                holder.exerciseName.setText(model.getExerciseName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference myRef = adapter.getRef(position);

                        //myRef.removeValue();
                        //adapter.notifyDataSetChanged();
                        //ExerciseObject exercise = (ExerciseObject) new ExerciseObject(myRef.getDatabase());
                        Intent intent = new Intent(WorkoutActivityV2.this,ExerciseActivity.class);
                        intent.putExtra("exercise",model);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //numberOfExercises = adapter.getItemCount();

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisesquare,parent,false);
                return new WorkoutHolder(view);

            }


        };

        layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);


        heartIcon = (ImageView) findViewById(R.id.heartIcon);
        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHeartRate();
            }
        });

        doneButton = (Button) findViewById(R.id.doneButtonWorkout);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        Intent intent = new Intent(this,AddExercise.class);
        startActivity(intent);
    }
    public void launchExercise(){
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }

    public void launchHeartRate(){
        Intent intent = new Intent(this,HeartRateActivity.class);
        startActivity(intent);
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
                Intent intent = new Intent(WorkoutActivityV2.this,ExerciseActivity.class);
                intent.putExtra("exercise",workoutList.get(position));
                startActivity(intent);
            }
        });
    }

    public void saveToDatabase(){
        for (ExerciseObject exercise: workouts) {
            int number = exercise.getExerciseNumber();
            String id = Integer.toString(number);
            //String id = databaseExercise.push().getKey();
            databaseExercise.child(id).setValue(exercise);
        }
    }

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
