package com.example.jimv2;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import static java.util.Collections.swap;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ExerciseViewHolder> implements ItemTouchHelperAdapter {
    public ArrayList<ExerciseObject> mExerciseList;
    FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder> adapter;

    private OnClickListner mListener;
    private View.OnLongClickListener longListener;
    private static final String TAG = "WorkoutAdapter";


    public interface OnClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnClickListner listner){
        mListener = listner;
    }
    public void setOnLongClickListener(View.OnLongClickListener listener){
        longListener = listener;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        public ImageView exerciseImage;
        public TextView  exerciseName;

        public ExerciseViewHolder(@NonNull View itemView, final OnClickListner  listner) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exercise_pic);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onItemClick(position);
                        }
                    }
                }
            });
        }

            /*
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }

             */
    }

    public WorkoutAdapter(ArrayList<ExerciseObject> exerciseList, FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder> adapter){
        mExerciseList = exerciseList;
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisesquare,parent,false); //card 2
        ExerciseViewHolder evh = new ExerciseViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        ExerciseObject currentExercise = mExerciseList.get(position);
        Picasso.get().load(currentExercise.getImageLink()).into(holder.exerciseImage);
        holder.exerciseImage.setImageResource(currentExercise.getmImageResource());
        holder.exerciseName.setText(currentExercise.getmText());
    }


    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        try {
            mExerciseList.remove(position);
            notifyItemRemoved(position);
        }
        catch (IndexOutOfBoundsException e)
        {
            adapter.getRef(position).removeValue();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        ExerciseObject prev = mExerciseList.remove(fromPosition);
        mExerciseList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }


    public ExerciseObject get(int index) {
        return mExerciseList.get(index);
    }

    public void updateFirebaseAdapter( FirebaseRecyclerAdapter<ExerciseObject, WorkoutHolder> adapter ) {
        this.adapter = adapter;
    }
}
