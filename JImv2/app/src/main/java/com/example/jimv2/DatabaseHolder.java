package com.example.jimv2;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseHolder extends RecyclerView.ViewHolder {

    public TextView exerciseName;
    TextView exerciseID;
    public EditText exerciseImage;
    public EditText exerciseSets;
    public EditText exerciseReps;
    public EditText exerciseWeight;

    public DatabaseHolder(@NonNull View itemView) {
        super(itemView);
        exerciseName = (TextView) itemView.findViewById(R.id.exerciseNameDB);
        exerciseImage = (EditText) itemView.findViewById(R.id.exerciseImageDB);
        exerciseSets = (EditText) itemView.findViewById(R.id.exerciseSetsDB);
        exerciseReps = (EditText) itemView.findViewById(R.id.exerciseRepsDB);
        exerciseWeight = (EditText) itemView.findViewById(R.id.exerciseWeightDB);
    }
}
