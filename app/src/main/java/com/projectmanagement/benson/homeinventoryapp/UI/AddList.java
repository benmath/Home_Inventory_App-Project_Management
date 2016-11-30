package com.projectmanagement.benson.homeinventoryapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.projectmanagement.benson.homeinventoryapp.Adapters.CheckItemListAdapter;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.List;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.util.ArrayList;

public class AddList extends AppCompatActivity {

    private String listName;    // name of the list
    private List listKeys;      // A list of item keys (references)
    private CheckItemListAdapter list;  // used to extract the selected keys

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        listName = getIntent().getStringExtra("ListName");

        buttons();
        displayAllItems();
    }

    /**
     * Associates and implements the save and cancel buttons from the layout.
     */
    private void buttons() {
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        Button btn_save = (Button) findViewById(R.id.btn_save);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listKeys = list.getList();      // extracts the selected keys
                listKeys.setListName(listName);
                if (!listKeys.getListKeys().isEmpty())    // only save the list if it is not empty
                    saveList();
                finish();
            }
        });
    }

    /**
     * Saves the list of listKeys
     */
    private void saveList() {
        ItemController c = new ItemController();
        c.addList(listKeys);
    }

    /**
     * Displays all the items on to a ListView using a custom adapter
     */
    private void displayAllItems() {
        ItemController control = new ItemController();

        list = new CheckItemListAdapter(control.getUserItems(), this, R.layout.item_list_row_checked);
        ListView itemListView = (ListView) findViewById(R.id.itemListViewChecked);

        try {
            itemListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            itemListView.setAdapter(list);

        } catch (NullPointerException e) {
            System.err.print(e.getMessage());
            Toast.makeText(this, "No Items Found :(", Toast.LENGTH_SHORT).show();
        }
    }
}
