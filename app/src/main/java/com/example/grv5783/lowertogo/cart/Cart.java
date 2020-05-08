package com.example.grv5783.lowertogo.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grv5783.lowertogo.R;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.grv5783.lowertogo.AccountPages.HomePage.DEBUG_TAG;

public class Cart extends AppCompatActivity implements View.OnClickListener{

    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    private ArrayList<String> cartItems = CartContainer.getInstance().itemButtons;
    private ArrayList<Double> cartPrices = CartContainer.getInstance().itemPrices;
    private ArrayList<Boolean> cartCustomized = CartContainer.getInstance().itemCustomized;
    private  ArrayList<String> cartOptions = CartContainer.getInstance().itemOptions;
    private ArrayList<String> cartOrders = CartContainer.getInstance().itemOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mLayout;
        setContentView(R.layout.activity_cart);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);

        Intent recieveItems = getIntent();
        ArrayList<Double> cartItemPrices = (ArrayList<Double>) recieveItems.getSerializableExtra("PRICES");
        ArrayList<String> itemButtons = recieveItems.getStringArrayListExtra("BUTTONS");
        ArrayList<Boolean> itemCustomized = (ArrayList<Boolean>) recieveItems.getSerializableExtra("CUSTOMIZED");
        ArrayList<String> itemOptions =   recieveItems.getStringArrayListExtra("OPTIONS");

        Button checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(this);


       for (int i = 0; i < itemButtons.size(); i++) {
           int j = i;
           int k = i;
           int l = i;
           Log.i(DEBUG_TAG,itemButtons.get(i));
           mLayout.addView(addCartButtons(itemButtons.get(i), cartItemPrices.get(j), itemCustomized.get(k), itemOptions.get(l)));
           Log.i(DEBUG_TAG,"YOOOOOO");
        }

    }

    private Button addCartButtons(final String i, final double j, final boolean k, final String l) {
        final Button item = new Button(this);
        final AlertDialog alert = new AlertDialog.Builder(this).create();
        item.setText(i);
        item.setBackgroundResource(R.drawable.buttonboi);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setTitle("Removing:");
                alert.setMessage("Are you sure you want to remove?\n"+ i + "\nOptions: " + l);
                alert.setButton(AlertDialog.BUTTON_POSITIVE,"YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    Log.i(DEBUG_TAG,"YESSSSSS");
                                    closeContextMenu();
                                    removeItem(i,item, j, k, l);
                            }
                        });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(DEBUG_TAG,"NOOOOOOOOOO");
                        closeContextMenu();
                    }
                });
                alert.show();
            }
        });
        Log.i(DEBUG_TAG,"HERE");
        return item;
    }

    private void removeItem(String i, Button item, double j, boolean k, String l) {
        Double priceChange = j;
        Log.i(DEBUG_TAG, "one" + priceChange);
        cartItems.remove(i);
        cartPrices.remove(j);
        cartCustomized.remove(k);
        cartOptions.remove(l);
        item.setVisibility(View.GONE);
        Log.i(DEBUG_TAG, "two " + cartItems);
        Log.i(DEBUG_TAG,"three "+ cartPrices);
        Log.i(DEBUG_TAG,"four " + formatter.format(CartValues.cartTotal));
        CartValues.subtractCartTotal(priceChange);
        CartValues.removeItem();
        //TODO program crashes when updating text in these buttons
        Log.i(DEBUG_TAG,"five " + formatter.format(CartValues.cartTotal));

    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())) {
            case R.id.checkout:
                if (cartItems.size() == 0) {
                    AlertDialog dialog = new AlertDialog.Builder(this).create();
                    dialog.setTitle("Cart must contain items in order to purchase");
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            closeContextMenu();
                        }

                    });
                    dialog.show();
                } else {
                    for(int i = 0; i < cartItems.size(); i++) {
                        String orderItem = cartItems.get(i);
                        String orderCustom = cartOptions.get(i);
                        cartOrders.add(orderItem  + ": " + orderCustom);
                        Log.i(DEBUG_TAG,"COMPLETE ORDER: " + cartOrders.get(i));
                    }

                    Intent cartIntent = new Intent(this, PurchasePage.class);
                    cartIntent.putExtra("ORDERS", cartOrders);
                    startActivity(cartIntent);
                    break;
                }
        }

    }
}


