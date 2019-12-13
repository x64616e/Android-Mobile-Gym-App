package com.example.jimv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public long passedDate3;
    private RecyclerView mRecyclerView;
    private WorkoutAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //public ArrayList<ExerciseObject> workoutList = new ArrayList<>();
    public ArrayList<ExerciseObject> workouts = new ArrayList<>();
    DatabaseReference databaseExercise;
    private Button Statistics;
    private Button addExercise;
    private Button heartRate;
    public EditText dates;
    public String userId;
    public String queryCurrentUser;
    private static final String TAG = "WorkoutActivityFragment";
    private Button nextDay;
    private Button previousDay;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workoutv3, container, false);
        buildRecylcerView(view);

        Intent intent = getActivity().getIntent();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        userId = userID.substring(0, Math.min(userID.length(), 6));
        StringBuilder queryUserDate = new StringBuilder(userId);

        try {
            LandingPageV2 activity = (LandingPageV2) getActivity();
            long passedDate = activity.getMyData();
            dateCurrentlyViewing.setTime(passedDate);

        } catch (NullPointerException nfe){
            nfe.printStackTrace();
        }


        Bundle bundle=getArguments();

        if(bundle !=null) {

            if (bundle.containsKey("intentDate")) {
                Toast.makeText(getActivity(),
                        "Passed", Toast.LENGTH_SHORT).show();

                passedDate3 = bundle.getLong("intentDate");
                dateCurrentlyViewing.setTime(passedDate3);

                String passedDate5 = String.valueOf(dateCurrentlyViewing);
                //dates.setText("test");
                Log.d(TAG, "intentDate: received.");

                TextView textViewDate = getActivity().findViewById(R.id.toolbar_text_view_date);
                String currentDate = java.text.DateFormat.getDateInstance().format(dateCurrentlyViewing.getTime());
                textViewDate.setText(currentDate);
            }
        }
        /*
        if(getActivity().getIntent().hasExtra("intentDate")) {
            Toast.makeText(getActivity(),
                    "Passed", Toast.LENGTH_SHORT).show();

            passedDate3 = getActivity().getIntent().getExtras().getLong("intentDate");
            dateCurrentlyViewing.setTime(passedDate3);

            String passedDate5 = String.valueOf(dateCurrentlyViewing);
            //dates.setText("test");
            Log.d(TAG, "intentDate: received.");
        }
        if(getActivity().getIntent().hasExtra("com.example.jimv2.PASSDATE")) {

        }
        */


        nextDay = (Button) view.findViewById(R.id.NextDay);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof WorkoutActivityFragment) {
                    addDay();
                    Bundle arg = currentFragment.getArguments();
                    arg.putLong("intentDate", dateCurrentlyViewing.getTime());
                    FragmentTransaction fragTransaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();
                    Log.d(TAG, "nextDay: clicked.");
                    //updateUI();
                }
            }
        });

        previousDay = (Button) view.findViewById(R.id.PreviousDay);
        previousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof WorkoutActivityFragment) {
                    substractDay();
                    Bundle arg = currentFragment.getArguments();
                    arg.putLong("intentDate", dateCurrentlyViewing.getTime());
                    FragmentTransaction fragTransaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();
                    Log.d(TAG, "PreviousDay: clicked.");
                    //updateUI();
                }
            }
        });


        SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
        String formattedDate = df.format(dateCurrentlyViewing);
        ExerciseObject exercise = intent.getParcelableExtra("exercise");

        queryUserDate.append(formattedDate);
        queryCurrentUser = queryUserDate.toString();

        databaseref = FirebaseDatabase.getInstance().getReference().child(queryCurrentUser);
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();
        
        adapter = new FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull WorkoutHolder holder, final int position, @NonNull final ExerciseObject model)
            {
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisesquare,parent,false);
                return new WorkoutHolder(view);

            }


        };

        mAdapter.updateFirebaseAdapter(adapter);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.exerciseRecycleView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);



        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);




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
            //mAdapter = new WorkoutAdapter(workoutList);
            mAdapter = new WorkoutAdapter(new ArrayList<ExerciseObject>(), adapter);
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
        //mAdapter = new WorkoutAdapter(workoutList);
        mAdapter = new WorkoutAdapter(new ArrayList<ExerciseObject>(), adapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new WorkoutAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ExerciseActivity.class);
                //intent.putExtra("exercise",workoutList.get(position));
                intent.putExtra("exercise",mAdapter.get(position));
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
    public void addDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateCurrentlyViewing);
        cal.add(Calendar.DATE, 1);
        dateCurrentlyViewing = cal.getTime();
    }

    public void substractDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateCurrentlyViewing);
        cal.add(Calendar.DATE, -1);
        dateCurrentlyViewing = cal.getTime();
    }

    public WorkoutActivityFragment() {
        this.setArguments(new Bundle());
    }
}


