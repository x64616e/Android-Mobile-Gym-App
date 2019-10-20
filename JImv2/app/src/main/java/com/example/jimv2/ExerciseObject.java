package com.example.jimv2;

public class ExerciseObject {

    private int exerciseImage;
    private int exerciseID;
    private String exerciseName;
    public boolean isSelected;

    public ExerciseObject(int imageSource, String text, int number){
        exerciseImage = imageSource;
        exerciseName = text;
        exerciseID = number;
    }
    public int getmImageResource(){
        return exerciseImage;
    }
    public int getExerciseNumber(){
        return exerciseImage;
    }
    public String getmText(){
        return exerciseName;
    }

    public boolean getSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
