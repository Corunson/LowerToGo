package com.example.grv5783.lowertogo.grilleWorks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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

public class GrilleWorks extends AppCompatActivity implements View.OnClickListener {

    public static Button grilleCart;

    private GrilleWorksDatabase grilleDatabase = new GrilleWorksDatabase();

    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public static ArrayList<String> itemContainer = CartContainer.getInstance().itemContainer;
    public static ArrayList<Double> itemPrices = CartContainer.getInstance().itemPrices;
    public static ArrayList<String> cartButtons = CartContainer.getInstance().itemButtons;
    public static ArrayList<Boolean> itemCustomized = CartContainer.getInstance().itemCustomized;
    public static ArrayList<String> itemCustomOptions = CartContainer.getInstance().itemOptions;


    boolean isCheeseExtra, isCheeseCustomized, isCustomized = false;
    boolean americanOption, swissOption, pepperjackOption, lettuceOption, carrotsOption, onionOption, baconOption, hamOption, pattyOption = false;
    ArrayList<Boolean> bool = new ArrayList<>();

    String[] itemOptions;
    String options = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bool.add(americanOption);
        Resources res = getResources();
        itemOptions = res.getStringArray(R.array.itemOptions);


        //TODO LIST
        //1. HAVE ALL ALERT FUNCTIONS WORK PROPERLY
        //2. PROPER PRICE UPDATES BASED ON CUSTOMIZATION
        //3. ADD IMAGES OF FOOD TO ALERT
        //4. IMPLEMENT BETTER GRAPHICS

        setContentView(R.layout.activity_grille_works_menu);
        grilleCart = findViewById(R.id.Cart);
        grilleCart.setText("Cart (" + CartValues.getCartAmount() + ")");
        grilleCart.setOnClickListener(this);


        final AlertDialog.Builder alert = new AlertDialog.Builder(this);


        final GrilleWorksAdapter grilleWorksAdapter = new GrilleWorksAdapter(this, R.layout.menus_listview, grilleDatabase);
        final ListView listView = findViewById(R.id.grilleWorksMenu);


        listView.setAdapter(grilleWorksAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                bool.add(0, americanOption);
                bool.add(1, swissOption);
                bool.add(2, pepperjackOption);
                bool.add(3, lettuceOption);
                bool.add(4, carrotsOption);
                bool.add(5, onionOption);
                bool.add(6, baconOption);
                bool.add(7, hamOption);
                bool.add(8, pattyOption);

                final String title = (String) parent.getItemAtPosition(position);
                final GrilleWorksParcel grilleItem = grilleDatabase.getItem(title);
                boolean itemCustom = grilleItem.getCustomizable();
                final double price = grilleItem.getGrilleTotal();
                final String desc = grilleItem.getDescription();

                //TODO add custom order tax

                Log.i(DEBUG_TAG, "" + itemCustom);
                Log.i(DEBUG_TAG, "" + grilleItem.getCustomizable());

                Log.i(DEBUG_TAG, "nono");
                if (grilleItem.getCustomizable()) {
                    Log.i(DEBUG_TAG, "customizeable");
                    alert.setTitle(null);
                    alert.setMessage(null);
                    alert.setNegativeButton(null, null);
                    alert.setPositiveButton(null, null);
                    alert.setNeutralButton(null, null);
                    alert.setTitle("Customize: " + title + "?\nCheese");
                    alert.setSingleChoiceItems(R.array.cheems, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Log.i(DEBUG_TAG, "american");
                                    isCheeseCustomized = true;
                                    americanOption = true;
                                    swissOption = false;
                                    pepperjackOption = false;
                                    bool.set(0, americanOption);
                                    bool.set(1, swissOption);
                                    bool.set(2, pepperjackOption);
                                    break;
                                case 1:
                                    Log.i(DEBUG_TAG, "swiss");
                                    isCheeseCustomized = true;
                                    americanOption = false;
                                    swissOption = true;
                                    pepperjackOption = false;
                                    bool.set(0, americanOption);
                                    bool.set(1, swissOption);
                                    bool.set(2, pepperjackOption);
                                    break;
                                case 2:
                                    Log.i(DEBUG_TAG, "pepper jack");
                                    isCheeseCustomized = true;
                                    americanOption = false;
                                    swissOption = false;
                                    pepperjackOption = true;
                                    bool.set(0, americanOption);
                                    bool.set(1, swissOption);
                                    bool.set(2, pepperjackOption);
                                    break;

                            }
                        }

                    });
                    alert.setNeutralButton("ORDER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(isCheeseCustomized) {
                                extraCheese(title, price);
                            } else {
                                onOrder(title, price);
                            }
                        }
                    });
                    alert.setPositiveButton("VEGGIES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            veggieAlert(title, price);
                        }
                    });
                    alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onCancel();
                        }
                    });
                    alert.setCancelable(false);
                    alert.show();
                    Log.i(DEBUG_TAG, "tom");
                } else if (!grilleItem.getCustomizable()) {
                    alert.setTitle(null);
                    alert.setMessage(null);
                    alert.setNegativeButton(null, null);
                    alert.setPositiveButton(null, null);
                    alert.setNeutralButton(null, null);
                    alert.setTitle("Order: " + title);
                    alert.setMessage(desc + "\n" + price);
                    alert.setNeutralButton("ORDER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onOrder(title, price);
                        }
                    });
                    alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            closeContextMenu();
                            onCancel();
                        }
                    });
                    alert.show();
                }


            }
        });

    }

    private void veggieAlert(final String title, final double price) {

        AlertDialog.Builder veggie = new AlertDialog.Builder(GrilleWorks.this);
        veggie.setCancelable(false);
        veggie.setTitle("Customize Veggies");
        Log.i(DEBUG_TAG, "veg cus");
        veggie.setMultiChoiceItems(R.array.veg, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                switch (which) {
                    case 0:
                        if (isChecked) {
                            lettuceOption = true;
                            bool.set(3, lettuceOption);
                        } else {
                            lettuceOption = false;
                            bool.set(3, lettuceOption);
                        }
                        break;
                    case 1:
                        if (isChecked) {
                            carrotsOption = true;
                            bool.set(4, carrotsOption);
                        } else {
                            carrotsOption = false;
                            bool.set(4, carrotsOption);

                        }
                        break;
                    case 2:
                        if (isChecked) {
                            onionOption = true;
                            bool.set(5, onionOption);
                        } else {
                            onionOption = false;
                            bool.set(5, onionOption);
                        }
                        break;
                }

            }

        });
        veggie.setPositiveButton("MEAT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                meatAlert(title, price);
            }

        });
        veggie.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
                onCancel();
            }
        });

        veggie.setNeutralButton("ORDER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isCheeseCustomized) {
                    extraCheese(title, price);
                } else {
                    onOrder(title, price);
                }
            }
        });
        veggie.show();
        Log.i(DEBUG_TAG, "veg tom");
    }

    private void meatAlert(final String title, final double price) {

        AlertDialog.Builder meat = new AlertDialog.Builder(GrilleWorks.this);
        meat.setCancelable(false);
        meat.setTitle("Customize Meat");
        meat.setMultiChoiceItems(R.array.meat, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                switch (which) {
                    case 0:
                        if (isChecked) {
                            baconOption = true;
                            bool.set(6, baconOption);
                        } else {
                            baconOption = false;
                            bool.set(6, baconOption);
                        }
                        break;
                    case 1:
                        if (isChecked) {
                            hamOption = true;
                            bool.set(7, hamOption);
                        } else {
                            hamOption = false;
                            bool.set(7, hamOption);
                        }
                        break;
                    case 2:
                        if (isChecked) {
                            pattyOption = true;
                            bool.set(8, pattyOption);
                        } else {
                            pattyOption = false;
                            bool.set(8, pattyOption);
                        }
                        break;
                }

            }
        });
        meat.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
                onCancel();
            }
        });
        meat.setNeutralButton("ORDER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isCheeseCustomized) {
                    extraCheese(title, price);
                } else {
                    onOrder(title, price);
                }
            }
        });
        meat.show();
    }

    private void extraCheese(final String title, final double price) {

        AlertDialog.Builder extraCheese = new AlertDialog.Builder(GrilleWorks.this);
        extraCheese.setCancelable(false);
        extraCheese.setTitle("Add Extra Cheese? (+$1.00)");
        extraCheese.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
                onOrder(title, price);
            }
        });
        extraCheese.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
                isCheeseExtra = true;
                onOrder(title , price);
            }
        });
        extraCheese.show();
    }

    private void onOrder(String title, double price) {

        if(bool.contains(true)) {
            isCustomized = true;
        }

        if(isCheeseExtra) {
            price = price + 1.00;
        }

        CartValues.addItem();
        CartValues.addTotal(price);

        grilleCart.setText("Cart (" + CartValues.getCartAmount() + ")");
        homeCart.setText("Cart (" + CartValues.getCartAmount() + ")");

        Log.i(DEBUG_TAG, "grilleCart Amount: " + CartValues.getCartAmount());
        Log.i(DEBUG_TAG, "Total Amount: " + formatter.format(CartValues.getCartTotal()));

        //itemButton.setText("Title: " + title + "\n Price: " + price);
        Log.i(DEBUG_TAG, "" + title + " " + price);

        for (int i = 0; i < bool.size(); i++) {
            if (bool.get(i)) {
                String option = itemOptions[i - 1] + " ";
                options = options + option;
                Log.i(DEBUG_TAG, options);
            } else {
                Log.i(DEBUG_TAG, "not added");
            }
        }

        itemContainer.add(title);
        itemPrices.add(price);
        itemCustomized.add(isCustomized);
        itemCustomOptions.add(options);
        addButton(title, price);

        options = "";
        isCheeseExtra = false;
        isCheeseCustomized = false;
        isCustomized = false;
        americanOption = false;
        swissOption = false;
        pepperjackOption = false;
        lettuceOption = false;
        carrotsOption = false;
        onionOption = false;
        baconOption = false;
        hamOption = false;
        pattyOption = false;
        bool.set(0, americanOption);
        bool.set(1, swissOption);
        bool.set(2, pepperjackOption);
        bool.set(3, lettuceOption);
        bool.set(4, carrotsOption);
        bool.set(5, onionOption);
        bool.set(6, baconOption);
        bool.set(7, hamOption);
        bool.set(8, pattyOption);


        Log.i(DEBUG_TAG, Arrays.toString(itemContainer.toArray()));
        Log.i(DEBUG_TAG, Arrays.toString(itemPrices.toArray()));
        Toast.makeText(getApplicationContext(),
                "Order added to Cart", Toast.LENGTH_SHORT).show();
    }

    private void onCancel() {
        options = "";
        isCustomized = false;
        americanOption = false;
        swissOption = false;
        pepperjackOption = false;
        lettuceOption = false;
        carrotsOption = false;
        onionOption = false;
        baconOption = false;
        hamOption = false;
        pattyOption = false;
        bool.set(0, americanOption);
        bool.set(1, swissOption);
        bool.set(2, pepperjackOption);
        bool.set(3, lettuceOption);
        bool.set(4, carrotsOption);
        bool.set(5, onionOption);
        bool.set(6, baconOption);
        bool.set(7, hamOption);
        bool.set(8, pattyOption);
        closeContextMenu();
        Toast.makeText(getApplicationContext(),
                "Order cancelled", Toast.LENGTH_SHORT).show();
    }

    public String addButton(String title, Double price) {
        String itemButton;
        if (isCheeseExtra) {
            itemButton = "Title: " + title + "\nPrice: " + price + "\nCustomized: Yes, Extra Cheese: Yes";
            cartButtons.add(itemButton);
            Log.i(DEBUG_TAG, "123 " + options);
            return itemButton;
        } else if (isCustomized) {
            itemButton = "Title: " + title + "\nPrice: " + price + "\nCustomized: Yes, Extra Cheese: No";
            Log.i(DEBUG_TAG, "456 " + options);

        } else {
            itemButton = "Title: " + title + "\nPrice: " + price + "\nCustomized: No";
            Log.i(DEBUG_TAG, "456 " + options);
        }
        cartButtons.add(itemButton);
        Log.i(DEBUG_TAG, "123 " + options);
        return itemButton;
    }

    @Override
    protected void onResume() {
        grilleCart.setText("Cart (" + CartValues.getCartAmount() + ")");
        super.onResume();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Cart:
                Log.i(DEBUG_TAG, "grilleCart Clicked");
                Intent intent = new Intent(this, Cart.class);
                intent.putExtra("ITEMS", itemContainer);
                intent.putExtra("PRICES", itemPrices);
                intent.putExtra("BUTTONS", cartButtons);
                intent.putExtra("CUSTOMIZED", itemCustomized);
                intent.putExtra("OPTIONS", itemCustomOptions);
                startActivity(intent);
                break;

        }

    }
}

