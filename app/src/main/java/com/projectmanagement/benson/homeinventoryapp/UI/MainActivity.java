package com.projectmanagement.benson.homeinventoryapp.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.projectmanagement.benson.homeinventoryapp.Adapters.ItemListAdapter;
import com.projectmanagement.benson.homeinventoryapp.DBManager;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Serializable {

    private ItemController control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new ItemController();
        extraStuff();
    }

    void populateData() {
        //list adapter using the FirebaseListAdapter

        ListAdapter list = new ItemListAdapter(control.getUserItems(), this, R.layout.item_list_row);
        ListView itemListView = (ListView) findViewById(R.id.itemListView);

        itemListView.setAdapter(list);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                goToViewItem(item);
            }
        });
    }

    private void extraStuff() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mainMenu);
        setSupportActionBar(toolbar);

        /*  Floating Action Bar */
        FloatingActionButton fabAddItem = (FloatingActionButton) findViewById(R.id.fab_addItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add an item
                Intent addItem = new Intent(MainActivity.this, AddItem.class);
                startActivity(addItem);
            }
        });

        /*  Side Navigation Drawer */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void login() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
    }

    private void goToCategories() {
        Intent categories = new Intent(MainActivity.this, Categories.class);
        startActivity(categories);
    }

    private void goToViewLists() {
        Intent lists = new Intent(MainActivity.this, ViewLists.class);
        startActivity(lists);
    }

    private void goToViewItem(Item item) {
        Intent viewItem = new Intent(MainActivity.this, ViewItem.class);
        viewItem.putExtra("Item", item);
        startActivity(viewItem);
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_option){  // Logout option
            new DBManager().getAuth().signOut();
            login();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // *** RESERVED FOR FUTURE ITERATION *** //

        if (id == R.id.item_categories)     // for categories
            goToCategories();
        else if (id == R.id.item_lists)     // for lists
            goToViewLists();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
