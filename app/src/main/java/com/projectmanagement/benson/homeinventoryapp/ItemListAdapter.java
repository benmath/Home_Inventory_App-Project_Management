package com.projectmanagement.benson.homeinventoryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Benson on 10/1/2016.
 */

public class ItemListAdapter extends FirebaseListAdapter<Item> {
    // The mUsername for this client. We use this to indicate the name of the item
    private String itemName;

    public ItemListAdapter(Query ref, Activity activity, int layout, String name) {
        super(activity, Item.class, layout, ref);
        itemName = name;
    }

    @Override
    protected void populateView(View v, Item model, int position) {
        TextView tv_itemListName = (TextView) v.findViewById(R.id.tv_itemListName);
        tv_itemListName.setText(model.getItemName());
    }

}