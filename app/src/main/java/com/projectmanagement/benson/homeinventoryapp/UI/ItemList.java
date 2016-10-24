package com.projectmanagement.benson.homeinventoryapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projectmanagement.benson.homeinventoryapp.Adapters.ItemListAdapter;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.R;

public class ItemList extends AppCompatActivity {

    private ItemController control; // used for Controller functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        control = new ItemController();
        displayItems();
    }

    /**
     * Changes the Toolbar Text to name.
     *
     * @param name  a String for the new Toolbar text.
     */
    private void changeToolbarText(String name) {
        name = name + " Items";
        getSupportActionBar().setTitle(name);
    }

    /**
     * Displays the Items from a subcategory
     */
    private void displayItems() {
        String subcategories = getIntent().getStringExtra("Subcategory");
        String categories = getIntent().getStringExtra("Category");

        changeToolbarText(subcategories);

        ListAdapter list = new ItemListAdapter(control.findSubcategoryItems(categories, subcategories),
                this, R.layout.item_list_row);

        System.out.println("list count: " + list.getCount());

        ListView itemListView = (ListView) findViewById(R.id.item_list_view);

        try {
            itemListView.setAdapter(list);

            itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Item item = (Item) parent.getItemAtPosition(position);
                    goToViewItem(item);
                }
            });
        } catch (NullPointerException e) {
            System.err.print(e.getMessage());
            Toast.makeText(this, "No Items Found :(", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Once the user selects an item, the user will be directed to the View Item Activity
     *
     * @param item  the Item that was selected
     */
    private void goToViewItem(Item item) {
        Intent viewItem = new Intent(ItemList.this, ViewItem.class);
        viewItem.putExtra("Item", item);
        startActivity(viewItem);
    }
}
