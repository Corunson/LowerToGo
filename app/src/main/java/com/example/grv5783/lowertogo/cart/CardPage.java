package com.example.grv5783.lowertogo.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.example.grv5783.lowertogo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.grv5783.lowertogo.AccountPages.HomePage.DEBUG_TAG;


public class CardPage extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase fire;
    DatabaseReference dbrf;

    public static ArrayList<String> itemContainer = CartContainer.getInstance().itemContainer;
    public static ArrayList<Double> itemPrices = CartContainer.getInstance().itemPrices;
    public static ArrayList<String> cartButtons = CartContainer.getInstance().itemButtons;
    public static ArrayList<Boolean> itemCustomized = CartContainer.getInstance().itemCustomized;
    public static ArrayList<String> itemCustomOptions = CartContainer.getInstance().itemOptions;
    public static ArrayList<String> itemOrders = CartContainer.getInstance().itemOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_page);

        Intent recieveItems = getIntent();
        ArrayList<String> ordersList = recieveItems.getStringArrayListExtra("ORDERS");

        CardForm cardForm = findViewById(R.id.card_form);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(false)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(CardPage.this);

        Button purchase = findViewById(R.id.purchaseButton);
        purchase.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.purchaseButton:
                Log.i(DEBUG_TAG,"CLICKED");
                String orders = itemOrders.toString();
                Log.i(DEBUG_TAG,"Orders: " + orders);
                Log.i(DEBUG_TAG,"ItemOrders: " + orders);
                //fire = FirebaseDatabase.getInstance();
               // dbrf = fire.getInstance().getReference("Orders");
               // String id = dbrf.push().getKey();
               // dbrf.child(id).setValue(orders);

        }
    }
}
