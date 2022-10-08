package com.example.myprojectjavaonkotlin.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class JavaCounterEntity implements Parcelable {

    private final String name;
    private int counter;

    public JavaCounterEntity(String name, int startCounter) {
        this.name = name;
        counter = startCounter;
    }

    protected JavaCounterEntity(Parcel in) {
        counter = in.readInt ();
        name = in.readString ();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt ( counter );
        dest.writeString ( name );
    }

    public static final Creator<JavaCounterEntity> CREATOR = new Creator<JavaCounterEntity> () {
        @Override
        public JavaCounterEntity createFromParcel(Parcel in) {
            return new JavaCounterEntity( in );
        }

        @Override
        public JavaCounterEntity[] newArray(int size) {
            return new JavaCounterEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void increment() {
        counter++;
    }

    public void decrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}