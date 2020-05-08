package com.example.grv5783.lowertogo.cart;

import android.os.Parcel;
import android.os.Parcelable;

public class CartParcel implements Parcelable {

    private String title;
    private int cartItemAmount;
    private double cartTotalAmount;


    protected CartParcel(Parcel in) {
        title = in.readString();
        cartItemAmount = in.readInt();
        cartTotalAmount = in.readDouble();

    }

    public CartParcel(String title, int resID){
        this.title = title;
        this.cartItemAmount = cartItemAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCartItemAmount() {
        return cartItemAmount;
    }

    public void setCartItemAmount(int cartItemAmount) {
        this.cartItemAmount = cartItemAmount;
    }

    public static final Creator<CartParcel> CREATOR = new Creator<CartParcel>() {
        @Override
        public CartParcel createFromParcel(Parcel in) {
            return new CartParcel(in);
        }

        @Override
        public CartParcel[] newArray(int size) {
            return new CartParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(cartItemAmount);
        dest.writeDouble(cartTotalAmount);
    }

}
