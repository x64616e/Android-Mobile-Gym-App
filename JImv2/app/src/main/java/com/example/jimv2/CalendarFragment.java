package com.example.jimv2;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarFragment extends Fragment {

    private Button doneButton;
    private Button planRoutine;
    private Button completedButton;
    Date nowDate = new Date();
    Date dateCurrentlyViewing = Calendar.getInstance().getTime();
    public Calendar calendar = Calendar.getInstance();

    public static final String TAG = "CalenderActivity";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar, container, false);
        final CalendarView calendarView = (CalendarView) view.findViewById(R.id.simpleCalendarView);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Intent intent = new Intent(getContext(), WorkoutActivityFragment.class);

                //startActivity(intent);
                calendar = new GregorianCalendar( year, month, dayOfMonth );

                SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
                String formattedDate = df.format(calendar.getTime());
                Date passedDate = calendar.getTime();
                intent.putExtra("com.example.jimv2.CALENDER", passedDate);
                Toast.makeText(getActivity(),
                        ""+ passedDate, Toast.LENGTH_SHORT).show();
                //startActivity(intent);

            }

        });







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

        Intent intent = new Intent(getActivity(), PlanWorkoutActivity.class);
        startActivity(intent);
    }

    public void openCompletedDay() {
        Log.d(TAG, "openCompletedDay: called.");

        Intent intent = new Intent(getActivity(), CompletedDayActivity.class);
        startActivity(intent);
    }
}
