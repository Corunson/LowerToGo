package com.example.grv5783.lowertogo.cart;

public class CartValues {

    private static int cartAmount;
    public static double cartTotal;

    public CartValues (int amount, double total) {
        cartTotal = total;
    }

    public static void addItem(){ ++cartAmount; }

    public static void removeItem(){--cartAmount;}

    public static void addTotal(double total) { cartTotal += total;}

    public static void subtractCartTotal(double removedTotal) {cartTotal -= removedTotal;}

    public static int getCartAmount(){
        return cartAmount;
    }

    public static double getCartTotal() {
        return cartTotal;
    }
}
