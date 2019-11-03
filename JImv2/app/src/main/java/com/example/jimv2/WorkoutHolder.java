package com.example.jimv2;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutHolder extends RecyclerView.ViewHolder{
    public TextView exerciseName;
    TextView exerciseID;
    public ImageView exerciseImage;
    public EditText exerciseSets;
    public EditText exerciseReps;
    public EditText exerciseWeight;
    private static final String TAG = "DatabaseHolder";

    public WorkoutHolder(@NonNull View itemView) {
        super(itemView);
        exerciseName = (TextView) itemView.findViewById(R.id.exercise_name);
        exerciseImage = (ImageView) itemView.findViewById(R.id.exercise_pic);

    }
}
