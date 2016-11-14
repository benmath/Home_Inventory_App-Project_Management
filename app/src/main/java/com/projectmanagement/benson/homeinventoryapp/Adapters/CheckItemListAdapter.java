package com.projectmanagement.benson.homeinventoryapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.Models.List;
import com.projectmanagement.benson.homeinventoryapp.R;

/**
 * Created by Benson on 11/12/2016.
 */

public class CheckItemListAdapter extends ItemListAdapter {

    private List listKeys;

    public CheckItemListAdapter(Query ref, Activity activity, int layout) {
        super(ref, activity, layout);
        listKeys = new List();
    }

    @Override
    protected void populateView(View v, final Item model, int position) {
        TextView tv_itemListName = (TextView) v.findViewById(R.id.tv_itemListNameCheck);
        tv_itemListName.setText(model.getItemName());
        //item = model;

        CheckBox cb_listItemName = (CheckBox) v.findViewById(R.id.cb_listItemName);

        cb_listItemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String itemKey = model.getKey();
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    listKeys.addKey(itemKey);
                    System.out.println("IS CHECKED!!!");
                }
                else {
                    listKeys.removeKey(itemKey);
                    System.out.println("IS NOT CHECKED");
                }
            }
        });
    }

    public List getList() {
        return listKeys;
    }
}
