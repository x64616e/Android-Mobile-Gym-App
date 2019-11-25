package com.example.jimv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkoutActivityFragment extends Fragment {

    Date dateCurrentlyViewing = Calendar.getInstance().getTime();

    DatabaseReference databaseref;
    FirebaseRecyclerOptions<ExerciseObject> options;
    FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder> adapter;

    private RecyclerView mRecyclerView;
    private WorkoutAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<ExerciseObject> workoutList = new ArrayList<>();
    public ArrayList<ExerciseObject> workouts = new ArrayList<>();
    DatabaseReference databaseExercise;
    private Button Statistics;
    private Button addExercise;
    private Button heartRate;
    public String userId;
    public String queryCurrentUser;
    private static final String TAG = "WorkoutActivityFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workoutv3, container, false);
        //populateArray();
        buildRecylcerView(view);
        Intent intent = getActivity().getIntent();

//        try {
//            Date passedDate2 = (Date) getArguments().getSerializable("passedDate");
//
//        } catch(NullPointerException NPE){}

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        userId = userID.substring(0, Math.min(userID.length(), 6));
        StringBuilder queryUserDate = new StringBuilder(userId);

        if(getActivity().getIntent().hasExtra("com.example.jimv2.PASSDATE")) {
            long passedDate = getActivity().getIntent().getExtras().getLong("com.example.jimv2.PASSDATE");
        //if(savedInstanceState.containsKey("com.example.jimv2.PASSDATE")   ) {//getActivity().getIntent().hasExtra("com.example.jimv2.PASSDATE")) {
        //    long passedDate = getActivity().getIntent().getExtras().getLong("com.example.jimv2.PASSDATE");
            dateCurrentlyViewing.setTime(passedDate);
        }

        if(getActivity().getIntent().hasExtra("com.example.jimv2.CALENDER")) {
            long passedDate = getActivity().getIntent().getExtras().getLong("com.example.jimv2.CALENDER");
            dateCurrentlyViewing.setTime(passedDate);
        }

        SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
        String formattedDate = df.format(dateCurrentlyViewing);
        ExerciseObject exercise = intent.getParcelableExtra("exercise");

        queryUserDate.append(formattedDate);
        queryCurrentUser = queryUserDate.toString();

        databaseref = FirebaseDatabase.getInstance().getReference().child(queryCurrentUser);
        //databaseref = FirebaseDatabase.getInstance().getReference().child(formattedDate);
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();
        

        queryUserDate.append(formattedDate);
        queryCurrentUser = queryUserDate.toString();
        databaseref = FirebaseDatabase.getInstance().getReference().child(queryCurrentUser);

        try {
            if ( exercise != (null)) {
                workouts.add(exercise);
                saveToDatabase();
            }
        } catch (NullPointerException nfe){
            nfe.printStackTrace();
        }



//        databaseExercise = FirebaseDatabase.getInstance().getReference(formattedDate);
//        saveToDatabase();


        adapter = new FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull WorkoutHolder holder, final int position, @NonNull final ExerciseObject model) {
                holder.exerciseName.setText(model.getExerciseName());
                holder.exerciseImage.setImageResource(model.getmImageResource());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference myRef = adapter.getRef(position);
                        Intent intent = new Intent(getActivity(), ExerciseActivity.class);
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

        //layoutManager = new GridLayoutManager(this,2);
        //mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.exerciseRecycleView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);


//        heartIcon = (ImageView) view.findViewById(R.id.heartIcon);
//        heartIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchHeartRate();
//            }
//        });

        Statistics = (Button) view.findViewById(R.id.doneButtonWorkout);
        Statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchStats();
            }
        });
        addExercise = (Button) view.findViewById(R.id.addExerciseButton);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "addExerciseButton: clicked.");

                addExercise();
            }
        });
        heartRate = (Button) view.findViewById(R.id.heartRate);
        heartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHeartRate();
            }
        });
        updateUI();

        return view;
    }

    private void updateUI() {

        if (mAdapter == null ) {
            mAdapter = new WorkoutAdapter(workoutList);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
    public void backToLanding(){
        getActivity().finish();
    }

    public void addExercise(){
        Log.d(TAG, "addExercise: called.");
        Intent intent = AddExercise.newIntent(getActivity(), dateCurrentlyViewing.getTime());
        //intent.putExtra("com.example.jimv2.PASSDATE", dateCurrentlyViewing.getTime());
        startActivity(intent);
    }
    public void launchStats(){
        Intent intent = new Intent(getActivity(), DatabaseWorkout.class);
        intent.putExtra("com.example.jimv2.PASSDATE", dateCurrentlyViewing.getTime());
        startActivity(intent);
    }

    public void launchHeartRate(){
        Intent intent = new Intent(getActivity(), HeartRateMonitor.class);
        startActivity(intent);
    }


    public void buildRecylcerView(View view){
        mRecyclerView = view.findViewById(R.id.exerciseRecycleView); // view
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),2);
        mAdapter = new WorkoutAdapter(workoutList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new WorkoutAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ExerciseActivity.class);
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

    public void onStart(){
        super.onStart();
        if(adapter !=null)
            adapter.startListening();
    }

    @Override
    public void onStop(){
        if(adapter !=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(adapter !=null)
            adapter.startListening();
        updateUI();
    }
}
