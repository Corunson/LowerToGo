package com.example.grv5783.lowertogo.grilleWorks;

import android.os.Parcel;
import android.os.Parcelable;

public class GrilleWorksParcel implements Parcelable {

    private int grilleID;
    private String title;
    private String description;
    private double grilleTotal;
    private boolean customizable;
    


    private GrilleWorksParcel(Parcel in) {
        grilleID = in.readInt();
        title = in.readString();
        description = in.readString();
        grilleTotal = in.readDouble();
        customizable = in.readInt() != 0;

    }

    protected GrilleWorksParcel(int burgerID, String title, String description, double burgerTotal, boolean customizable){
        this.grilleID = burgerID;
        this.title = title;
        this.description = description;
        this.grilleTotal = burgerTotal;
        this.customizable = customizable;
    }

    public int getburgerID() {
        return grilleID;
    }

    public void setburgerID(int burgerID) {
        this.grilleID = burgerID;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getDescription() { return description; }

    public void setDescription() { this.description = description; }

    double getGrilleTotal() {
        return grilleTotal;
    }

    public void setBurgerTotal() { this.grilleTotal = grilleTotal; }

    boolean getCustomizable() {return customizable;}

    public void setCustomizable() { this.customizable = customizable;}

    public static final Creator<GrilleWorksParcel> CREATOR = new Creator<GrilleWorksParcel>() {

        @Override
        public GrilleWorksParcel createFromParcel(Parcel in) {
            return new GrilleWorksParcel(in);
        }

        @Override
        public GrilleWorksParcel[] newArray(int size) {
            return new GrilleWorksParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(grilleID);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(grilleTotal);
        dest.writeInt(customizable ? 1 : 0);
    }

}
