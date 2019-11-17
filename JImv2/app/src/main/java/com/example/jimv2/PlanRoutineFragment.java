package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PlanRoutineFragment extends Fragment {


    private Button doneButton;
    private Button planExercise;
    private static final String TAG = "PlanRoutineFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_routine, container, false);

        planExercise = (Button) view.findViewById(R.id.legDay);

        planExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPlanWorkoutActivity();
            }
        });

        return view;
    }

    public void toPlanWorkoutActivity(){
        Log.d(TAG, "toPlanWorkoutActivity: called.");


        Intent intent = new Intent(getActivity(), PlanWorkoutActivity.class);
        startActivity(intent);
    }
}
