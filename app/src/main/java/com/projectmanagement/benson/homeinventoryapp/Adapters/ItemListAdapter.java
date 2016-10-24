package com.projectmanagement.benson.homeinventoryapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.R;


/**
 * Created by Benson on 10/1/2016.
 */

public class ItemListAdapter extends FirebaseListAdapter<Item> {

    public ItemListAdapter(Query ref, Activity activity, int layout) {
        super(activity, Item.class, layout, ref);
    }

    @Override
    protected void populateView(View v, Item model, int position) {
        TextView tv_itemListName = (TextView) v.findViewById(R.id.tv_itemListName);
        tv_itemListName.setText(model.getItemName());
    }

}