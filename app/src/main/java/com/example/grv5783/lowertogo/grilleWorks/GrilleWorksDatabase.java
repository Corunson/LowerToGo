package com.example.grv5783.lowertogo.grilleWorks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrilleWorksDatabase {

    HashMap<String, GrilleWorksParcel> grilleItems = new HashMap<>();

    GrilleWorksDatabase() {
        addItem(new GrilleWorksParcel(1,"Southwest Burger", "YeeHaw", 1.01,true));
        addItem(new GrilleWorksParcel(2,"Classic Burger", "Classic", 2.02,true));
        addItem(new GrilleWorksParcel(3,"American Burger", "Freedom",3.03,true));
        addItem(new GrilleWorksParcel(4,"Veggie Burger", "Healthy", 4.04,false));
        addItem(new GrilleWorksParcel(5,"Turkey Burger", "November already?", 5.05,false));
        addItem(new GrilleWorksParcel(6,"Salsa Burger", "I'm not a taco", 6.06,false));
    }

    private void addItem(GrilleWorksParcel grilleItem) { grilleItems.put(grilleItem.getTitle(), grilleItem); }

    GrilleWorksParcel getType(int grilleID){ return grilleItems.get(grilleID); }

    GrilleWorksParcel getItem(String title) { return grilleItems.get(title); }

    GrilleWorksParcel getDescription(String description) { return grilleItems.get(description); }

    GrilleWorksParcel getPrice(double grilleTotal){ return grilleItems.get(grilleTotal); }

    GrilleWorksParcel getCustom(boolean customizable){return grilleItems.get(customizable); }





    List<String> getTitles()
    {
        if (grilleItems.size() == 0)
            return null;
        Set<Map.Entry<String, GrilleWorksParcel>> entrySet = grilleItems.entrySet();

        Iterator<Map.Entry<String,GrilleWorksParcel>> iterator = entrySet.iterator();

        List<String> titles = new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<String, GrilleWorksParcel> entry = iterator.next();
            titles.add(entry.getKey());
        }
        return titles;
    }

}
