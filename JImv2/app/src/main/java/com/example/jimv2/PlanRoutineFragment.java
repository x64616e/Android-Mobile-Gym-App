package com.example.jimv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PlanRoutineFragment extends Fragment {


    private Button createRoutine;
    //private Button planExercise;
    private static final String TAG = "PlanRoutineFragment";
    Spinner Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
    int currentItem = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_routine, container, false);

        Monday =(Spinner)view.findViewById(R.id.Mon);
        Tuesday =(Spinner)view.findViewById(R.id.Tues);
        Wednesday =(Spinner)view.findViewById(R.id.Wed);
        Thursday =(Spinner)view.findViewById(R.id.Thur);
        Friday =(Spinner)view.findViewById(R.id.Fri);
        Saturday =(Spinner)view.findViewById(R.id.Sat);
        Sunday =(Spinner)view.findViewById(R.id.Sun);

        //planExercise = (Button) view.findViewById(R.id.legDay);
        createRoutine = (Button) view.findViewById(R.id.create_routine);
        createRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        Monday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(currentItem==position){
                    return;
                }else{
                    Intent intent = new Intent(getActivity(),PlanWorkoutActivity.class);
                    intent.putExtra("day","Monday");
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Tuesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(currentItem==position){
                    return;
                }else{
                    Intent intent = new Intent(getActivity(),PlanWorkoutActivity.class);
                    intent.putExtra("day","Tuesday");
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Wednesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(currentItem==position){
                    return;
                }else{
                    Intent intent = new Intent(getActivity(),PlanWorkoutActivity.class);
                    intent.putExtra("day","Wednesday");
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        planExercise.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toPlanWorkoutActivity();
//            }
//        });

        return view;
    }

    public void openDialog() {
        ExDialog dialog = new ExDialog();
        dialog.show(getFragmentManager()," Routine Created ");
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void toPlanWorkoutActivity(){
        Log.d(TAG, "toPlanWorkoutActivity: called.");


        Intent intent = new Intent(getActivity(), PlanWorkoutActivity.class);
        startActivity(intent);
    }
}
