package com.example.jimv2;

import android.os.Parcel;
import android.os.Parcelable;


public class ExerciseObject implements Parcelable {

    private int exerciseImage;
    private int exerciseID;
    private String exerciseName;
    public int exerciseSets;
    public int exerciseReps;
    public int exerciseWeight;

    public ExerciseObject(int imageSource, String text, int number, int sets, int reps, int weight){
        exerciseImage = imageSource;
        exerciseName = text;
        exerciseID = number;
        exerciseSets = sets;
        exerciseReps = reps;
        exerciseWeight = weight;
    }

    public int getExerciseImage() {
        return exerciseImage;
    }
    public void setExerciseImage(int exerciseImage) {
        this.exerciseImage = exerciseImage;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseSets(int exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public void setExcerciseReps(int exerciseReps) {
        this.exerciseReps = exerciseReps;
    }

    public void setExerciseWeight(int exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }

    public ExerciseObject() {
    }

    protected ExerciseObject(Parcel in) {
        exerciseImage = in.readInt();
        exerciseID = in.readInt();
        exerciseName = in.readString();
    }

    public static final Creator<ExerciseObject> CREATOR = new Creator<ExerciseObject>() {
        @Override
        public ExerciseObject createFromParcel(Parcel in) {
            return new ExerciseObject(in);
        }

        @Override
        public ExerciseObject[] newArray(int size) {
            return new ExerciseObject[size];
        }
    };

    public int getmImageResource(){
        return exerciseImage;
    }
    public int getExerciseNumber(){
        return exerciseID;
    }
    public int getExerciseSets() {return  exerciseSets;}
    public int getExcerciseReps() {return  exerciseReps;}
    public int getExerciseWeight() {return  exerciseWeight;}

    public String getmText(){
        return exerciseName;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(exerciseImage);
        dest.writeInt(exerciseID);
        dest.writeString(exerciseName);
    }


}
