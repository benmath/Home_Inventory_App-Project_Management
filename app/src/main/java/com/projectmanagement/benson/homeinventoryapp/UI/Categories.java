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

public class Categories extends AppCompatActivity {

    private ArrayList<String> categories;   // a dynamic list of categories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categories = new ItemController().getCategories(); // gets the list from the Item Controller

        setAdapter();
    }

    /**
     * Sets the ListAdapter to display the categories and handles the user's selection.
     */
    private void setAdapter() {
        ListAdapter adapter = new CategoryAdapter(this, categories);
        ListView categoryListView = (ListView) findViewById(R.id.CategoryListView);
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoryName = String.valueOf(parent.getItemAtPosition(position));
                goToSubcategory(categoryName);
            }
        });
        categoryListView.setAdapter(adapter);
    }

    /**
     * Directs the user to the Subcategory Activity.
     *
     * @param category  A String that represents the Category that was selected.
     */
    private void goToSubcategory(String category) {
        Intent subcategory = new Intent(this, Subcategories.class);
        subcategory.putExtra("Category", category);
        startActivity(subcategory);
    }
}
