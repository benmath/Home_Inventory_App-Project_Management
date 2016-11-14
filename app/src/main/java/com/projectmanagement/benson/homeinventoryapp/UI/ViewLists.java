package com.projectmanagement.benson.homeinventoryapp.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projectmanagement.benson.homeinventoryapp.Adapters.ListNameAdapter;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.List;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.io.Serializable;

public class ViewLists extends AppCompatActivity implements Serializable {

    private ItemController control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lists");
        setSupportActionBar(toolbar);


        control = new ItemController();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addListDialogBox();
            }
        });

        displayListNames();
    }

    private void displayListNames() {
        ListAdapter list = new ListNameAdapter(control.findListNames(), this, R.layout.categories_row);
        ListView itemListView = (ListView) findViewById(R.id.item_list_view);

        try {
            itemListView.setAdapter(list);

            itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    List list = (List) parent.getItemAtPosition(position);
                    goToItemList(list);
                }
            });
        } catch (NullPointerException e) {
            System.err.print(e.getMessage());
            Toast.makeText(this, "No Lists Found :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void addListDialogBox() {
        final EditText editText = new EditText(this);
        editText.setHint("Enter List Name");

        new AlertDialog.Builder(this)
                .setTitle(null)
                .setMessage("Enter a name for a new list:")
                .setView(editText)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name = editText.getText().toString().trim();

                        if (name.equals("")) {
                            Toast.makeText(ViewLists.this, "Please Insert an List Name", Toast.LENGTH_SHORT).show();
                            editText.setError(getString(R.string.error_field_required));
                        }
                        else
                            goToAddList(name);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void goToAddList(String name) {
        Intent addList = new Intent(this, AddList.class);
        addList.putExtra("ListName", name);
        startActivity(addList);
    }

    private void goToItemList(List list) {
        Intent itemList = new Intent(this, ItemList.class);
        itemList.putExtra("ListName", list);
        startActivity(itemList);
    }
}
