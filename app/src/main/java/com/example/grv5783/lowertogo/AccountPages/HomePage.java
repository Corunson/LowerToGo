package com.example.grv5783.lowertogo.AccountPages;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grv5783.lowertogo.R;
import com.example.grv5783.lowertogo.cart.Cart;
import com.example.grv5783.lowertogo.cart.CartValues;
import com.example.grv5783.lowertogo.grilleWorks.GrilleWorks;
import com.example.grv5783.lowertogo.italianKitchen.ItalianKitchen;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.grv5783.lowertogo.grilleWorks.GrilleWorks.cartButtons;
import static com.example.grv5783.lowertogo.grilleWorks.GrilleWorks.itemContainer;
import static com.example.grv5783.lowertogo.grilleWorks.GrilleWorks.itemCustomOptions;
import static com.example.grv5783.lowertogo.grilleWorks.GrilleWorks.itemCustomized;
import static com.example.grv5783.lowertogo.grilleWorks.GrilleWorks.itemPrices;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    public static final String DEBUG_TAG ="Lower ToGo";

    public static Button homeCart;

    private FirebaseAuth.AuthStateListener homeAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        homeCart = findViewById(R.id.cart);
        homeCart.setText("Cart (" + CartValues.getCartAmount()+ ")");
        homeCart.setOnClickListener(this);

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);

        Button BurgerMenu = findViewById(R.id.grilleMenu);
        BurgerMenu.setOnClickListener(this);

        Button italianMenu = findViewById(R.id.italianMenu);
        italianMenu.setOnClickListener(this);

        ImageButton BurgerInfo = (ImageButton) findViewById(R.id.grilleInfo);
        BurgerInfo.setOnClickListener(this);

        ImageButton PizzaInfo = (ImageButton) findViewById(R.id.italianInfo);
        PizzaInfo.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        homeCart.setText("Cart ("+CartValues.getCartAmount()+")");
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        Log.i(DEBUG_TAG,"onClick Reached");
        switch (view.getId()) {
            case R.id.grilleMenu:
                Log.i(DEBUG_TAG,"Burger HomePage Clicked");
                Intent grilleIntent = new Intent(this, GrilleWorks.class);
                startActivity(grilleIntent);
                break;

            case R.id.grilleInfo:
                Log.i(DEBUG_TAG,"Burger Info Clicked");
                AlertDialog alertBurger = new AlertDialog.Builder(this).create();
                alertBurger.setTitle("About Grille Works");
                alertBurger.setMessage("Primarily sells burgers");
                alertBurger.show();
                break;

            case R.id.italianMenu:
                Log.i(DEBUG_TAG,"Pizza HomePage Clicked");
                Intent italianIntent = new Intent(this, ItalianKitchen.class);
                startActivity(italianIntent);
                break;

            case R.id.italianInfo:
                Log.i(DEBUG_TAG,"Pizza Info Clicked");
                AlertDialog alertPizza = new AlertDialog.Builder(this).create();
                alertPizza.setTitle("About Italian Kitchen");
                alertPizza.setMessage("Primarily sells italian cuisine" + "\n" + "Creator's note: Not actually the correct restaurant logo");
                alertPizza.show();
                break;

            case R.id.cart:
                Log.i(DEBUG_TAG,"Cart Clicked");
                Intent cartIntent = new Intent(this, Cart.class);
                cartIntent.putExtra("ITEMS", itemContainer);
                cartIntent.putExtra("PRICES", itemPrices);
                cartIntent.putExtra("BUTTONS", cartButtons);
                cartIntent.putExtra("CUSTOMIZED", itemCustomized);
                cartIntent.putExtra("OPTIONS", itemCustomOptions);
                startActivity(cartIntent);
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(HomePage.this, AccountPage.class);
                startActivity(logout);
                break;

        }

    }
}
