package com.projectmanagement.benson.homeinventoryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projectmanagement.benson.homeinventoryapp.R;

import java.util.ArrayList;

/**
 * Created by Benson on 10/21/2016.
 */

public class CategoryAdapter extends ArrayAdapter<String> {

    public CategoryAdapter(Context context, ArrayList<String> itemName) {
        super(context, R.layout.categories_row, itemName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.categories_row, parent, false); //set it to false if not to inflate to parent

        String singleItem = getItem(position);
        TextView myText = (TextView) customView.findViewById(R.id.tv_categories_name);

        myText.setText(singleItem);     //sets the text dynamically

        return customView;
    }
}