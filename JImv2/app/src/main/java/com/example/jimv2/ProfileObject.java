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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(0);
        dest.writeInt(0);
        dest.writeString("0");
    }
}
