package com.example.grv5783.lowertogo.italianKitchen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grv5783.lowertogo.R;
import com.example.grv5783.lowertogo.cart.Cart;
import com.example.grv5783.lowertogo.cart.CartContainer;
import com.example.grv5783.lowertogo.cart.CartValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static com.example.grv5783.lowertogo.AccountPages.HomePage.DEBUG_TAG;
import static com.example.grv5783.lowertogo.AccountPages.HomePage.homeCart;

public class ItalianKitchen extends AppCompatActivity implements View.OnClickListener {

    public static Button italianCart;

    private ItalianKitchenDatabase italianDatabase = new ItalianKitchenDatabase();

    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public static ArrayList<String> cartContainer = CartContainer.getInstance().itemContainer;
    public static ArrayList<Double> cartPrices = CartContainer.getInstance().itemPrices;
    public static ArrayList<String> cartButtons = CartContainer.getInstance().itemButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.italian_kitchen_menu);

        italianCart = (Button) findViewById(R.id.Cart);
        italianCart.setText("Cart (" + CartValues.getCartAmount() + ")");
        italianCart.setOnClickListener(this);


        final AlertDialog alert = new AlertDialog.Builder(this).create();

        final ItalianKitchenAdapter italianKitchenAdapter = new ItalianKitchenAdapter(this, R.layout.menus_listview, italianDatabase);

        final ListView listView = (ListView) findViewById(R.id.italianWorksMenu);
        listView.setAdapter(italianKitchenAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String italianTitles = (String) parent.getItemAtPosition(position);

                final ItalianKitchenParcel italianItem = italianDatabase.getItem(italianTitles);

                alert.setTitle(italianTitles);
                alert.setMessage("" + italianItem.getDescription() + "\n" + formatter.format(italianItem.getItalianTotal()));
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "ORDER", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        CartValues.addItem();
                        CartValues.addTotal(italianItem.getItalianTotal());

                        italianCart.setText("Cart (" + CartValues.getCartAmount() + ")");
                        homeCart.setText("Cart (" + CartValues.getCartAmount() + ")");

                        Log.i(DEBUG_TAG, "Cart Amount: " + CartValues.getCartAmount());
                        Log.i(DEBUG_TAG, "Total Amount: " + formatter.format(CartValues.getCartTotal()));

                        String title = italianTitles;
                        Double price = italianItem.getItalianTotal();


                        cartContainer.add(title);
                        cartPrices.add(price);
                        addButton(title, price);

                        Log.i(DEBUG_TAG, Arrays.toString(cartContainer.toArray()));

                        Toast.makeText(getApplicationContext(),
                                "Order added to Cart", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeContextMenu();

                        Toast.makeText(getApplicationContext(),
                                "Order cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        });

    }

    public String addButton(String title, Double price){
        String itemButton = "Title: " + title + "\nPrice: " + price;
        cartButtons.add(itemButton);
        return itemButton;
    }

    @Override
    protected void onResume() {
        italianCart.setText("Cart ("+CartValues.getCartAmount()+")");
        super.onResume();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Cart:
                Log.i(DEBUG_TAG, "grilleCart Clicked");
                Intent intent = new Intent(this, Cart.class);
                intent.putExtra("ITEMS", cartContainer);
                intent.putExtra("PRICES", cartPrices);
                intent.putExtra("BUTTONS", cartButtons);
                startActivity(intent);
                break;

        }

    }

}
