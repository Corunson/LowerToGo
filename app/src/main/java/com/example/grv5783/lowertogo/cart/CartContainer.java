package com.example.grv5783.lowertogo.cart;

import android.widget.Button;

import java.util.ArrayList;

public class CartContainer {

    private static CartContainer instance;
    public final ArrayList<String> itemContainer = new ArrayList();
    public final ArrayList<Double> itemPrices = new ArrayList<>();
    public final ArrayList<String> itemButtons = new ArrayList<>();
    public final ArrayList<Boolean> itemCustomized = new ArrayList<>();
    public final ArrayList<String> itemOptions = new ArrayList<>();
    public final ArrayList<String> itemOrders = new ArrayList<>();

    private CartContainer() {}

    public static CartContainer getInstance() {
        if( instance == null ) {
            instance = new CartContainer();
        }
        return instance;
    }
}

