package com.example.grv5783.lowertogo.grilleWorks;

import android.content.Context;
import android.widget.ArrayAdapter;

public class GrilleWorksAdapter extends ArrayAdapter<String> {

    protected GrilleWorksAdapter(Context context, int resid, GrilleWorksDatabase grilleWorksDatabase) {
            super(context, resid, grilleWorksDatabase.getTitles());
    }
}
