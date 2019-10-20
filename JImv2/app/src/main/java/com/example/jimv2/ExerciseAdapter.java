package com.example.jimv2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    public ArrayList<ExerciseObject> mExerciseList;
    public static class ExerciseViewHolder extends RecyclerView.ViewHolder{

        public ImageView exerciseImage;
        public TextView  exerciseName;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exercise_pic);
            exerciseName = itemView.findViewById(R.id.exercise_name);
        }
    }

    public ExerciseAdapter(ArrayList<ExerciseObject> exerciseList){
        mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisecard,parent,false);
        ExerciseViewHolder evh = new ExerciseViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseObject currentExercise = mExerciseList.get(position);
        holder.exerciseImage.setImageResource(currentExercise.getmImageResource());
        holder.exerciseName.setText(currentExercise.getmText());
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }
}