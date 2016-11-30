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

import java.util.ArrayList;

/**
 * Created by Benson on 11/12/2016.
 */

public class CheckItemListAdapter extends ItemListAdapter {

    private List listKeys;
    private List oldList;

    public CheckItemListAdapter(Query ref, Activity activity, int layout) {
        super(ref, activity, layout);
        listKeys = new List();
    }

    public CheckItemListAdapter(Query ref, Activity activity, int layout, List list) {
        super(ref, activity, layout);
        oldList = list;
        listKeys = new List();
    }

    @Override
    protected void populateView(View v, final Item model, int position) {
        TextView tv_itemListName = (TextView) v.findViewById(R.id.tv_itemListNameCheck);
        tv_itemListName.setText(model.getItemName());

        CheckBox cb_listItemName = (CheckBox) v.findViewById(R.id.cb_listItemName);

        final String itemKey = model.getKey();

        if (oldList != null && isKeyInList(oldList, itemKey)) {
            cb_listItemName.setChecked(true);
            listKeys.addKey(itemKey);
        }

        cb_listItemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    listKeys.addKey(itemKey);
                }
                else {
                    listKeys.removeKey(itemKey);
                }
            }
        });
    }

    private boolean isKeyInList(List list, String key) {
        for(String s : list.getListKeys())
            if (s.equals(key))
                return true;
        return false;
    }

    public List getList() {
        return listKeys;
    }
}
