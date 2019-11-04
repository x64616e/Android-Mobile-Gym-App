package com.example.jimv2;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private static final String TAG = "ExerciseAdapter";
    public ArrayList<ExerciseObject> mExerciseList;
    private ExerciseAdapter.OnClickListner mListener;
    private Context mContext;

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder{
        public ImageView exerciseImage;
        public TextView  exerciseName;
        public CheckBox exerciseCheckbox;
        public RelativeLayout parentLayout;
        // sparse boolean array for checking the state of the items
        private SparseBooleanArray itemStateArray = new SparseBooleanArray();

        public ExerciseViewHolder(@NonNull View itemView, final ExerciseAdapter.OnClickListner listner) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exercise_pic);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            exerciseCheckbox = (CheckBox) itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onItemClick(position);
                        }
                        if (!itemStateArray.get(position, false)) {
                            exerciseCheckbox.setChecked(true);
                            itemStateArray.put(position, true);
                        } else  {
                            exerciseCheckbox.setChecked(false);
                            itemStateArray.put(position, false);
                        }
                    }
                }
            });


        }

        void bind(int position) {
            // use the sparse boolean array to check
            if (!itemStateArray.get(position, false)) {
                exerciseCheckbox.setChecked(false);
            } else {
                exerciseCheckbox.setChecked(true);
            }
        }
    }


    public ExerciseAdapter( ArrayList<ExerciseObject> exerciseList){
        mExerciseList = exerciseList;
        //mContext = context;
    }
    public interface OnClickListner{
        void onItemClick(int position);

    }
    public void setOnItemClickListner(ExerciseAdapter.OnClickListner listner){
        mListener = listner;
    }
    
    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercisecard,parent,false);
        ExerciseViewHolder evh = new ExerciseViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.bind(position);
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.exerciseImage);
        final ExerciseObject currentExercise = mExerciseList.get(position);
        holder.exerciseImage.setImageResource(currentExercise.getmImageResource());
        holder.exerciseName.setText(currentExercise.getmText());

        //TODO
        /* holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + currentExercise.getmText());

                //Toast.makeText(mContext, mExerciseList.get(position), Toast.LENGTH_SHORT).show();
            }
        }); */


    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    public void filterList(ArrayList<ExerciseObject> filteredList){
        mExerciseList = filteredList;
        notifyDataSetChanged();
    }
}
