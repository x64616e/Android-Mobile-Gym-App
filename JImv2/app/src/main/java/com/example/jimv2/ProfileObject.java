package com.example.jimv2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class ProfileObject implements Parcelable {

    private String profileUID;
    private String profileName;
    private Calendar profileDOB;
    private double profileWeight;
    private double profileHeight;
    private int profileExperience;
    private int profileTraining;

    public ProfileObject(String name, Calendar dob, double weight, double height, int experience, int training) {
        profileName = name;
        profileUID = name + "_dummyUID";
        profileDOB = dob;
        profileWeight = weight;
        profileHeight = height;
        profileExperience = experience;
        profileTraining = training;
    }

    public void setName(String name) { profileName = name; }
    public void setDOB(Calendar dob) { profileDOB = dob; }
    public void setHeight(double height) { profileHeight = height; }
    public void setWeight(double weight) { profileWeight = weight; }
    public void setExperience(int experience) { profileExperience = experience; }
    public void setTraining(int training) { profileTraining = training; }

    public String getName() { return profileName; }
    public Calendar getDOB() { return profileDOB; }
    public double getHeight() { return profileHeight; }
    public double getWeight() { return profileWeight; }
    public int getExperience() { return profileExperience; }
    public int getTraining() { return profileTraining; }

    public ProfileObject() { }

    protected ProfileObject(Parcel in) { }

    public static final Creator<ProfileObject> CREATOR = new Creator<ProfileObject>() {
        @Override
        public ProfileObject createFromParcel(Parcel in) {
            return new ProfileObject(in);
        }

        @Override
        public ProfileObject[] newArray(int size) {
            return new ProfileObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
