package com.example.jimv2;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CalendarFragment extends Fragment {

    private Button doneButton;
    private Button planRoutine;
    private Button completedButton;
    public static final String TAG = "CalenderActivity";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar, container, false);

        planRoutine = (Button) view.findViewById(R.id.planRoutineButton);
        planRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlanRoutine();
            }
        });

        completedButton = (Button) view.findViewById(R.id.completedDays);
        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCompletedDay();
            }
        });

        return view;
    }

    public void openPlanRoutine() {
        Log.d(TAG, "openPlanRoutine: called.");

        Intent intent = new Intent(getActivity(), PlanRoutineFragment.class);
        startActivity(intent);
    }

    public void openCompletedDay() {
        Log.d(TAG, "openCompletedDay: called.");

        Intent intent = new Intent(getActivity(), CompletedDayActivity.class);
        startActivity(intent);
    }
}
