package com.example.grv5783.lowertogo.italianKitchen;

import android.os.Parcel;
import android.os.Parcelable;

public class ItalianKitchenParcel implements Parcelable {


    private int italianID;
    private String title;
    private String description;
    private double italianTotal;


    protected ItalianKitchenParcel(Parcel in) {
        italianID = in.readInt();
        title = in.readString();
        description = in.readString();
        italianTotal = in.readDouble();

    }

    public ItalianKitchenParcel(int italianID, String title, String description, double italianTotal) {
        this.italianID = italianID;
        this.title = title;
        this.description = description;
        this.italianTotal = italianTotal;
    }

    public int getItalianID() {
        return italianID;
    }

    public void setItalianID(int italianID) {
        this.italianID = italianID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        this.description = description;
    }

    public double getItalianTotal() {
        return italianTotal;
    }

    public void setItalianTotal() {
        this.italianTotal = italianTotal;
    }


    public static final Creator<ItalianKitchenParcel> CREATOR = new Creator<ItalianKitchenParcel>() {

        @Override
        public ItalianKitchenParcel createFromParcel(Parcel in) {
            return new ItalianKitchenParcel(in);
        }

        @Override
        public ItalianKitchenParcel[] newArray(int size) {
            return new ItalianKitchenParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(italianID);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(italianTotal);
    }

}
