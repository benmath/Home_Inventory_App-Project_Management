package com.projectmanagement.benson.homeinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Benson on 10/1/2016.
 */

public class ItemListAdapter extends ArrayAdapter<Item> {
    public ItemListAdapter(Context context, ArrayList<Item> itemName) {
        super(context, R.layout.item_list_row, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.item_list_row, parent, false); //set it to false if not to inflate to parent

        Item item = getItem(position);
        TextView tutorName = (TextView) customView.findViewById(R.id.tv_itemListName);

        tutorName.setText(item.getItemName());

        return customView;
    }
}