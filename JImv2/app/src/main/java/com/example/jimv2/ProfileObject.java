package com.example.jimv2;

import java.util.Calendar;

public class ProfileObject {

    private String profileUID;
    private String profileName;
    private String profileMonth;
    private String profileDay;
    private String profileYear;
    private String profileWeight;
    private String profileHeight;
    private int profileExperience;
    private int profileTraining;

    public ProfileObject() {
        //?
    }

    public ProfileObject(String uid, String name, String month, String day, String year, String weight, String height, int experience, int training) {
        profileUID = uid;
        profileName = name;
        profileMonth = month;
        profileDay = day;
        profileYear = year;
        profileWeight = weight;
        profileHeight = height;
        profileExperience = experience;
        profileTraining = training;
    }

    public void setName(String name) { profileName = name; }
    public void setMonth(String month) { profileMonth = month; }
    public void setDay(String day) { profileDay = day; }
    public void setYear(String year) { profileYear = year; }
    public void setHeight(String height) { profileHeight = height; }
    public void setWeight(String weight) { profileWeight = weight; }
    public void setExperience(int experience) { profileExperience = experience; }
    public void setTraining(int training) { profileTraining = training; }

    public String getName() { return profileName; }
    public String getMonth() { return profileMonth; }
    public String getDay() { return profileDay; }
    public String getYear() { return profileYear; }
    public String getHeight() { return profileHeight; }
    public String getWeight() { return profileWeight; }
    public int getExperience() { return profileExperience; }
    public int getTraining() { return profileTraining; }

}
