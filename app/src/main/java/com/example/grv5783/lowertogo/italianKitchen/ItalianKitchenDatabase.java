package com.example.grv5783.lowertogo.italianKitchen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItalianKitchenDatabase {

    HashMap<String, ItalianKitchenParcel> italianItems = new HashMap<>();

    protected ItalianKitchenDatabase() {
        addItem(new ItalianKitchenParcel(1,"Pepperoni Slice", "1 Slice", 1.02));
        addItem(new ItalianKitchenParcel(2,"Cheese Slice", "1 Slice no toppings", 2.03));
        addItem(new ItalianKitchenParcel(3,"Stromboli", "Packed with ham, pepperoni, and cheese",3.04));
        addItem(new ItalianKitchenParcel(4,"2 Pepperoni Slices", "Comes with a drink", 4.05));
        addItem(new ItalianKitchenParcel(5,"French Fries", "These shouldn't be here", 0.99));
    }

    protected void addItem(ItalianKitchenParcel italianItem) { italianItems.put(italianItem.getTitle(), italianItem); }

    public ItalianKitchenParcel getType(int italianID){ return italianItems.get(italianID); }

    protected ItalianKitchenParcel getItem(String title) { return italianItems.get(title); }

    public ItalianKitchenParcel getDescription(String description) { return italianItems.get(description); }

    public ItalianKitchenParcel getPrice(double italianTotal){ return italianItems.get(italianTotal); }

    protected List<String> getTitles()
    {
        if (italianItems.size() == 0)
            return null;
        Set<Map.Entry<String, ItalianKitchenParcel>> entrySet = italianItems.entrySet();

        Iterator<Map.Entry<String, ItalianKitchenParcel>> iterator = entrySet.iterator();

        List<String> titles = new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<String, ItalianKitchenParcel> entry = iterator.next();
            titles.add(entry.getKey());
        }
        return titles;
    }
}
