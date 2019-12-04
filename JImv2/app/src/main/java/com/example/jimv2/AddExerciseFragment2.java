package com.example.jimv2;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.view.View;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddExerciseFragment2 extends Fragment {

    Date dateCurrentlyViewing = Calendar.getInstance().getTime();

    DatabaseReference databaseref;
    public ArrayList<ExerciseObject> exerciseList;
    public ArrayList<ExerciseObject> sendToWorkout;
    private RecyclerView mRecyclerView;
    private ExerciseAdapter mAdapter;
    DatabaseReference databaseExercise;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "AddExerciseFragment";
    public String userId;
    public String queryCurrentUser;
    private Button doneButton;

    public AddExerciseFragment2(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called.");
        exerciseList = new ArrayList<>();
        View view = inflater.inflate(R.layout.exerciseaddv2, container, false);
        populateList();
        EditText editText = view.findViewById(R.id.search);
        buildRecylcerView(view);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        userId = userID.substring(0, Math.min(userID.length(), 6));
        StringBuilder queryUserDate = new StringBuilder();

        if(getActivity().getIntent().hasExtra("com.example.jimv2.PASSDATE")) {
            long passedDate = getActivity().getIntent().getExtras().getLong("com.example.jimv2.PASSDATE");
            dateCurrentlyViewing.setTime(passedDate);
        }

        SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");


        String formattedDate = df.format(dateCurrentlyViewing);
        queryUserDate.append("plan");
        queryUserDate.append(formattedDate);
        queryCurrentUser = queryUserDate.toString();
        databaseref = FirebaseDatabase.getInstance().getReference().child(queryCurrentUser);

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

        doneButton = (Button) view.findViewById(R.id.doneButtonAddExercise);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO
//                Intent intent = new Intent(AddExcercise.this,WorkoutActivityFragment.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("list", (ArrayList<ExerciseObject>)sendToWorkout);
//                intent.putExtras(bundle);
//                startActivity(intent);

                getActivity().finish();


            }
        });

        return view;
    }

    private void filter(String text) {
        Log.d(TAG, "filter: called.");
        ArrayList<ExerciseObject> filteredList = new ArrayList<>();
        for (ExerciseObject item : exerciseList) {
            if (item.getmText().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);

    }

    private void populateList() {
        Log.d(TAG, "buildpopulateList: called.");
        exerciseList.add(new ExerciseObject(R.drawable.ic_pile_squat_1, "Pile Squat", 1, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Pile-squat-1.png?alt=media&token=ec7c03d2-989f-4c6b-895d-93ca31d63448"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_alternate_bicep_curl_1, "Curl", 2, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Biceps-curl-2.png?alt=media&token=647820be-4af6-48a4-b84d-c1d128a17c3a"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_leg_press_2_1024x670, "Leg Press", 3, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Leg-press-2-1024x670.png?alt=media&token=23e2a234-bbec-45cc-82ef-4a748c166512"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_jm_press_2, "Bench Press", 4, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Bench-press-2.png?alt=media&token=266ec475-9369-4097-b06c-80916e1d0868"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_triceps_kickback_2, "Tricep Kickback", 5, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Triceps-kickback-2.png?alt=media&token=1e116d30-a955-4b54-93ae-7384d2e421af"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_good_mornings_1, "Good Mornings", 6, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Good-mornings-1.png?alt=media&token=bc69a008-477b-4b46-b374-f0806b36e0a9"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_hammer_curls_with_rope_2, "Hammer Curl", 7, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Hammer-curls-with-rope-2.png?alt=media&token=79e5f20d-c7c1-465e-b859-66da39a1b7f7"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_preacher_hammer_curl_1, "Preacher Curl", 8, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Preacher-hammer-curl-1.png?alt=media&token=3e1a9a78-d5b9-4924-b048-17b26027f5de"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_tricep_dips_1, "Tricept Dip", 9, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Tricep-dips-1_icon.png?alt=media&token=d36db32d-66db-48e8-b96c-cee650e6da38"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_prone_incline_biceps_curl_2, "Prone Curls", 10, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Prone-incline-biceps-curl-1.png?alt=media&token=d7834228-7bcf-4180-9295-73372a481faa"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_overhead_squat_2, "Overhead Squat", 11, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Overhead-squat-2.png?alt=media&token=a89b235a-f096-4b3f-b6ad-0a09ba1e64b1"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_lunges_2_2, "Lunges", 12, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Lunges-1.png?alt=media&token=1f3b7bbf-87b8-4acf-b8c3-40ea4f962aab"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_crunches_2, "Crunches", 13, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Crunches-1.png?alt=media&token=31ae77fe-7407-4dfa-8da5-cb8e3e3fd451"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_chin_ups_1, "Chin Ups", 14, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Chin-ups-2.png?alt=media&token=7d76c613-7f3e-4d5f-ac32-9b24bb6037f4"));
        exerciseList.add(new ExerciseObject(R.drawable.ic_decline_crunch_2, "Decline Crunch", 15, 0, 0, 0,"https://firebasestorage.googleapis.com/v0/b/jym350-de9ff.appspot.com/o/Decline-crunch-2.png?alt=media&token=338f3400-78d6-4bc8-904c-5c88270a316a"));
    }

    public void buildRecylcerView(View view) {
        Log.d(TAG, "buildRecyclerView: called.");

        mRecyclerView = view.findViewById(R.id.recyclerView1);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ExerciseAdapter(exerciseList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListner(new ExerciseAdapter.OnClickListner() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "Exercise Item Clicked: called.");
                ExerciseObject exercise = new ExerciseObject();
                exercise = exerciseList.get(position);
                int number = exercise.getExerciseNumber();
                String id = Integer.toString(number);
                databaseref.child(id).setValue((ExerciseObject)exercise);
                getActivity().finish();
            }
        });

    }

}

