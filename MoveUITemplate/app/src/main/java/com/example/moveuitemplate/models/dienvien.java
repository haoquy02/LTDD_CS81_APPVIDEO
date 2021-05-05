package com.example.moveuitemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

public class dienvien implements Parcelable{
    private String name;
    private String profile_path;

    protected dienvien(Parcel in) {
        name = in.readString();
        profile_path = in.readString();
    }
    public dienvien(String name, String profile_path) {
        this.name = name;
        this.profile_path = profile_path;
    }
    public static final Creator<dienvien> CREATOR = new Creator<dienvien>() {
        @Override
        public dienvien createFromParcel(Parcel in) {
            return new dienvien(in);
        }

        @Override
        public dienvien[] newArray(int size) {
            return new dienvien[size];
        }
    };
    public String getName()
    {
        return name;
    }
    public String getImages ()
    {
        return profile_path;
    }
    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(profile_path);

    }
}
