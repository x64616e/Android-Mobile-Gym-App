package com.example.jimv2;

import android.os.Parcel;
import android.os.Parcelable;


public class ExerciseObject implements Parcelable {

    private int exerciseImage;
    private int exerciseID;
    private String exerciseName;
    public boolean isSelected;
    public int exerciseSets;
    public int excerciseReps;
    public double exerciseWeight;

    public ExerciseObject(int imageSource, String text, int number, int sets, int reps, double weight){
        exerciseImage = imageSource;
        exerciseName = text;
        exerciseID = number;
        exerciseSets = sets;
        excerciseReps = reps;
        exerciseWeight = weight;
    }

    protected ExerciseObject(Parcel in) {
        exerciseImage = in.readInt();
        exerciseID = in.readInt();
        exerciseName = in.readString();
        isSelected = in.readByte() != 0;
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
    public int getExcerciseReps() {return  excerciseReps;}
    public double getExerciseWeight() {return  exerciseWeight;}

    public String getmText(){
        return exerciseName;
    }

    public boolean getSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
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
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }


}
