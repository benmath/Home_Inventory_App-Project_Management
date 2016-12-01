package com.projectmanagement.benson.homeinventoryapp.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projectmanagement.benson.homeinventoryapp.Adapters.ListItemsAdapter;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.Models.List;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewListItems extends AppCompatActivity implements Serializable{

    private ItemController control;
    private ArrayAdapter adapter;
    private List listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        control = new ItemController();
        listName = (List) getIntent().getSerializableExtra("ListName");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEditList(listName);
            }
        });

        changeToolbarText(listName.getListName());

        if (listName.getListKeys().isEmpty())
            Toast.makeText(this, "No List Items...", Toast.LENGTH_SHORT).show();

        displayListItems(listName);
    }

    private void changeToolbarText(String name) {
        name = name + " Items";
        getSupportActionBar().setTitle(name);
    }

    private void displayListItems(List itemList) {
        ArrayList<Item> items = control.getListItems(itemList);

        adapter = new ListItemsAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.item_list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                goToViewItem(item);
            }
        });
        listView.setAdapter(adapter);
    }

    private void goToViewItem(Item item) {
        Intent viewItem = new Intent(ViewListItems.this, ViewItem.class);
        viewItem.putExtra("Item", item);
        startActivity(viewItem);
    }

    private void goToEditList(List list) {
        Intent editList = new Intent(ViewListItems.this, EditList.class);
        editList.putExtra("List", list);
        // startActivityForResult(editList, 1);     // maybe later ._.
        startActivity(editList);
    }

    private void goToMainScreen() {
        Intent main = new Intent(this, MainActivity.class);
        main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // cant go back
        startActivity(main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
            if (resultCode == RESULT_OK) {
                adapter.notifyDataSetChanged();
            }
    }

    /**
     * Deletes the List from the DB.
     */
    private void deleteList() {
        control.deleteList(listName);
    }

    /**
     * Dialog Box to confirm with the user.
     */
    private void deleteListDialog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deleting List")
                .setMessage("Are you sure you want to delete this list?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteList();
                        goToMainScreen();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    /**
     * Options menu - delete function.
     *
     * @param menu  The menu for the options.
     * @return  true if it could be inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_deleteList) {
            deleteListDialog();     // deletes the item
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
