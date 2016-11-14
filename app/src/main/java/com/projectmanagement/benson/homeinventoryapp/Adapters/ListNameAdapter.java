package com.projectmanagement.benson.homeinventoryapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;
import com.projectmanagement.benson.homeinventoryapp.Models.List;
import com.projectmanagement.benson.homeinventoryapp.R;

/**
 * Created by Benson on 11/13/2016.
 */

public class ListNameAdapter extends FirebaseListAdapter<List> {

    public ListNameAdapter(Query ref, Activity activity, int layout) {
        super(activity, List.class, layout, ref);
    }

    // We're using the categories row layout to list the list names
    @Override
    protected void populateView(View v, List model, int position) {
        TextView tv_itemListName = (TextView) v.findViewById(R.id.tv_categories_name);
        tv_itemListName.setText(model.getListName());
    }
}