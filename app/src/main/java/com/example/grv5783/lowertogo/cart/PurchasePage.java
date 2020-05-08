package com.example.grv5783.lowertogo.cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grv5783.lowertogo.R;

import java.util.ArrayList;

import static com.example.grv5783.lowertogo.AccountPages.HomePage.DEBUG_TAG;


public class PurchasePage extends AppCompatActivity implements View.OnClickListener {

    private int optionChosen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_page);
        Button selectOption = (Button) findViewById(R.id.selectButton);

        final ArrayList<String> options = new ArrayList<>();
        options.add("Credit Card/Debit Card");
        options.add("At Store");


        ArrayAdapter<String> purchaseList = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        purchaseList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner purchaseOption =  findViewById(R.id.purchaseOptions);
        purchaseOption.setAdapter(purchaseList);

        purchaseOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if(item != null) {
                    switch(position) {
                        case 0:
                            optionChosen = 1;
                            Log.i(DEBUG_TAG, "Credit "+ optionChosen);
                            break;
                        case 1:
                            optionChosen = 2;
                            Log.i(DEBUG_TAG, "At Store "+ optionChosen);
                            break;

                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        selectOption.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectButton:
                Intent purchaseIntent;
                if (optionChosen == 1) {
                    Log.i(DEBUG_TAG, "Credit Card/Debit Card");
                    purchaseIntent = new Intent(this, CardPage.class);
                    startActivity(purchaseIntent);
                    break;
                } else if (optionChosen == 2) {
                    Log.i(DEBUG_TAG, "At Store");
                    purchaseIntent = new Intent(this, OrderPage.class);
                    startActivity(purchaseIntent);
                    break;
                } else
                    Log.i(DEBUG_TAG, "Help " + optionChosen);
                break;
        }
    }

}


