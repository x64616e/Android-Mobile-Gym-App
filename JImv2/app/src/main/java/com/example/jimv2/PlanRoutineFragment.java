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
    Spinner Mon,Tues,Wed,Thur,Fri,Sat,Sun;
    int currentItem = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_routine, container, false);

        Mon =(Spinner)view.findViewById(R.id.Mon);
        Tues =(Spinner)view.findViewById(R.id.Tues);
        Wed =(Spinner)view.findViewById(R.id.Wed);
        Thur =(Spinner)view.findViewById(R.id.Thur);
        Fri =(Spinner)view.findViewById(R.id.Fri);
        Sat =(Spinner)view.findViewById(R.id.Sat);
        Sun =(Spinner)view.findViewById(R.id.Sun);

        //planExercise = (Button) view.findViewById(R.id.legDay);
        createRoutine = (Button) view.findViewById(R.id.create_routine);
        createRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        Mon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(currentItem==position){
                    return;
                }else{
                    Intent intent = new Intent(getActivity(),PlanWorkoutActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Tues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(currentItem==position){
                    return;
                }else{
                    Intent intent = new Intent(getActivity(),PlanWorkoutActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Wed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(currentItem==position){
                    return;
                }else{
                    Intent intent = new Intent(getActivity(),PlanWorkoutActivity.class);
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
