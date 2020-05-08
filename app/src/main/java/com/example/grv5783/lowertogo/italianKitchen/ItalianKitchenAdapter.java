package com.example.grv5783.lowertogo.italianKitchen;

import android.content.Context;
import android.widget.ArrayAdapter;


public class ItalianKitchenAdapter extends ArrayAdapter<String> {
    protected ItalianKitchenAdapter(Context context, int resid, ItalianKitchenDatabase italianKitchenDatabase) {
        super(context, resid, italianKitchenDatabase.getTitles());
    }
}
