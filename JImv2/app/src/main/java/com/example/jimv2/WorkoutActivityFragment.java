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
import java.util.Locale;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import android.view.GestureDetector.OnGestureListener;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WorkoutActivityFragment extends Fragment {

    Date dateCurrentlyViewing = Calendar.getInstance().getTime();

    DatabaseReference databaseref;
    FirebaseRecyclerOptions<ExerciseObject> options;
    FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder> adapter;

    private RecyclerView mRecyclerView;
    private WorkoutAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<ExerciseObject> workoutList = new ArrayList<>();
    private Button addExercise;
    private Button hearRate;
    private TextView date;
    public String userId;
    public String queryCurrentUser;
    private static final String TAG = "WorkoutActivityFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workoutv3, container, false);
        buildRecylcerView(view);


        if(getActivity().getIntent().hasExtra("com.example.jimv2.PASSDATE")) {
            long passedDate = getActivity().getIntent().getExtras().getLong("com.example.jimv2.PASSDATE");
            dateCurrentlyViewing.setTime(passedDate);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        date = (TextView) view.findViewById(R.id.currentDate);
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateCurrentlyViewing.getTime());
        date.setText(date_n);

        SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
        String formattedDate = df.format(dateCurrentlyViewing);
        String userID = user.getUid();
        userId = userID.substring(0, Math.min(userID.length(), 6));

        StringBuilder queryUserDate = new StringBuilder(userId);
        queryUserDate.append(formattedDate);
        queryCurrentUser = queryUserDate.toString();

        databaseref = FirebaseDatabase.getInstance().getReference().child(queryCurrentUser);
        options = new FirebaseRecyclerOptions.Builder<ExerciseObject>().setQuery(databaseref,ExerciseObject.class).build();


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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisesquare,parent,false);
                return new WorkoutHolder(view);
            }


        };

        mRecyclerView = (RecyclerView) view.findViewById(R.id.exerciseRecycleView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);


        hearRate = (Button) view.findViewById(R.id.heartRateButton);
        hearRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHeartRate();
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


    public void addExercise(){
        Log.d(TAG, "addExercise: called.");
        Intent intent = AddExercise.newIntent(getActivity(), dateCurrentlyViewing.getTime());
        startActivity(intent);
    }

    public void launchExercise(){
        Intent intent = new Intent(getActivity(), ExerciseActivity.class);
        intent.putExtra("com.example.jimv2.PASSDATE", dateCurrentlyViewing.getTime());
        startActivity(intent);
    }

    public void launchHeartRate(){
        Intent intent = new Intent(getActivity(), HeartRateMonitor.class);
        startActivity(intent);
    }


    public void buildRecylcerView(View view) {
        mRecyclerView = view.findViewById(R.id.exerciseRecycleView); // view
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        mAdapter = new WorkoutAdapter(workoutList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new WorkoutAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ExerciseActivity.class);
                intent.putExtra("exercise", workoutList.get(position));
                intent.putExtra("com.example.jimv2.USER", userId);
                startActivity(intent);
            }

        });

    }

//    public void previousDay(){
//        Intent intent = new Intent(this,WorkoutActivityFragment.class);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(dateCurrentlyViewing);
//        cal.add(Calendar.DATE, -1);
//        Date passDate = cal.getTime();
//        intent.putExtra("com.example.jimv2.PASSDATE", passDate.getTime());
//        startActivity(intent);
//    }
//    public void forwardDay(){
//        Intent intent = new Intent(this,WorkoutActivityFragment.class);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(dateCurrentlyViewing);
//        cal.add(Calendar.DATE, 1);
//        Date passDate = cal.getTime();
//        intent.putExtra("com.example.jimv2.PASSDATE", passDate.getTime());
//        startActivity(intent);
//    }

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
