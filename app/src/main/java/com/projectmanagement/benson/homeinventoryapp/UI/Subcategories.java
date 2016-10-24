package com.projectmanagement.benson.homeinventoryapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.projectmanagement.benson.homeinventoryapp.Adapters.CategoryAdapter;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.util.ArrayList;

public class Subcategories extends AppCompatActivity {

    private ArrayList<String> subcategories;    // dynamic list of subcategories
    private String category;                    // one of the Categories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories);

        getSubcategories();
        changeToolbarText(category);

        displaySubcategories();
    }

    /**
     * Changes the Toolbar text to an assigned test.
     *
     * @param text  the text of the new Toolbar text.
     */
    private void changeToolbarText(String text) {
        getSupportActionBar().setTitle(text);
    }

    /**
     * Gets the subcategory from the Category Activity
     */
    private void getSubcategories() {
        category = getIntent().getExtras().getString("Category");
        subcategories = new ItemController().getSubcategories(category);
    }

    /**
     * Displays the subcategories by attaching it to a ListAdapter.
     */
    private void displaySubcategories() {
        ListAdapter adapter = new CategoryAdapter(this, subcategories);
        ListView subcategoryListView = (ListView) findViewById(R.id.subcategories_list_view);
        subcategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String subcategoryName = String.valueOf(parent.getItemAtPosition(position));
                goToItemList(subcategoryName);
            }
        });
        subcategoryListView.setAdapter(adapter);
    }

    /**
     * Navigates the user to the Item List for a specific subcategory.
     *
     * @param subcategory   a String that represents the subcategory of the user's selection.
     */
    private void goToItemList(String subcategory) {
        Intent itemList = new Intent(Subcategories.this, ItemList.class);
        itemList.putExtra("Subcategory", subcategory);
        itemList.putExtra("Category", category);
        startActivity(itemList);
    }
}
