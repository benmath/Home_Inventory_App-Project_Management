package com.projectmanagement.benson.homeinventoryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.util.ArrayList;

/**
 * Created by Benson on 11/13/2016.
 */

public class ListItemsAdapter extends ArrayAdapter<Item> {

    public ListItemsAdapter(Context context, ArrayList<Item> itemName) {
        super(context, R.layout.categories_row, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.item_list_row, parent, false); //set it to false if not to inflate to parent

        Item singleItem = getItem(position);
        TextView myText = (TextView) customView.findViewById(R.id.tv_itemListName);

        myText.setText(singleItem.getItemName());     //sets the text dynamically

        return customView;
    }
}
