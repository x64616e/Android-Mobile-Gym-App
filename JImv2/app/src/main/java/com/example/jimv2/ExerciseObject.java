package com.example.jimv2;

public class ExerciseObject {

    private int exerciseImage;
    private String exerciseName;
    public boolean isSelected;

    public ExerciseObject(int imageSource, String text){
        exerciseImage = imageSource;
        exerciseName = text;

    }
    public int getmImageResource(){
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
