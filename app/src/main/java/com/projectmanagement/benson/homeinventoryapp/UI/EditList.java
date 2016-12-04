package com.projectmanagement.benson.homeinventoryapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.projectmanagement.benson.homeinventoryapp.Adapters.CheckItemListAdapter;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.List;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.io.Serializable;

public class EditList extends AppCompatActivity implements Serializable {

    private CheckItemListAdapter list;  // used to extract the selected keys
    private List listItems;
    private ItemController control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listItems = (List) getIntent().getSerializableExtra("List");
        changeToolbarText(listItems.getListName());

        setButtons();
        displayAllItems();
    }

    private void changeToolbarText(String name) {
        name = "Edit " + name + " List";
        getSupportActionBar().setTitle(name);
    }

    /**
     * Associates and implements the save and cancel buttons from the layout.
     */
    private void setButtons() {
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
                String listName = listItems.getListName();
                listItems = list.getList();      // extracts the selected keys
                listItems.setListName(listName);
                if (!listItems.getListKeys().isEmpty())    // only save the list if it is not empty
                    updateList();
                goBack();
            }
        });
    }

    /**
     * Displays all the items on to a ListView using a custom adapter
     */
    private void displayAllItems() {
        control = new ItemController();

        list = new CheckItemListAdapter(control.getUserItems(), this, R.layout.item_list_row_checked, listItems);
        ListView itemListView = (ListView) findViewById(R.id.itemListViewChecked);

        try {
            itemListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            itemListView.setAdapter(list);

        } catch (NullPointerException e) {
            System.err.print(e.getMessage());
            Toast.makeText(this, "No Items Found :(", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Saves the list of listKeys
     */
    private void updateList() {
        control.updateList(listItems);
    }

    private void goBack() {
        Intent viewListItems = new Intent(this, ViewListItems.class);
        viewListItems.putExtra("ListName", listItems);
        startActivity(viewListItems);
        finish();
    }

    private void goToViewLists() {
        Intent viewLists = new Intent(this, ViewLists.class);
        startActivity(viewLists);
        finish();
    }
}
