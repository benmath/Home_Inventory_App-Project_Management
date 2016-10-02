package com.projectmanagement.benson.homeinventoryapp;

import android.app.ListActivity;
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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Item> itemList;
    private ArrayList<String> itemKeys;
    private FirebaseListAdapter itemListAdapter;
    private ListAdapter list;
    private ListView itemListView;
    private ChildEventListener itemListener;
    private ValueEventListener listen;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        itemKeys = new ArrayList<>();

        initializeDB();
        extraStuff();
        //attachAdapter();
        //newAdapter();
    }

    void attachAdapter(){
        itemListView  = (ListView) findViewById(R.id.itemListView);
        //itemListAdapter = new ItemListAdapter(MainActivity.this, itemList);
        itemListView.setAdapter(itemListAdapter);

        System.out.println("List Size: " +  itemList.size());

        /*
        itemListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Item item = (Item) parent.getItemAtPosition(position);

                    }
                }
        );
        */
    }

    void newAdapter() {
        System.out.println("List Size: " +  itemList.size());

    }

    void initializeDB() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user-items").child(user.getUid());

    }

    private void populateData() {
        /*
        final ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, R.layout.item_list_row, R.id.tv_itemListName, itemList);
        itemListView = (ListView) findViewById(R.id.itemListView);
        itemListView.setAdapter(adapter);
        */

        itemListView  = (ListView) findViewById(R.id.itemListView);
        //itemListAdapter = new ItemListAdapter(MainActivity.this, itemList);
        itemListView.setAdapter(itemListAdapter);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item item = dataSnapshot.getValue(Item.class);

                itemList.add(item);
                itemKeys.add(dataSnapshot.getKey());
                itemListAdapter.notifyDataSetChanged();
                System.out.println("\nadded item: " + item.getItemName() + "\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Item item = dataSnapshot.getValue(Item.class);

                int index = itemKeys.indexOf(dataSnapshot.getKey());
                if (index > -1) {
                    itemList.set(index, item);
                    System.out.println("\nedited item: " + item.getItemName() + "\n");
                } else
                    System.err.println("\n\nUNKNOWN KEY!!!!!!!!\n\n");
                itemListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                int index = itemKeys.indexOf(dataSnapshot.getKey());
                if (index > -1) {
                    itemList.remove(index);
                    itemKeys.remove(index);
                    System.out.println("\nremoved item: " + dataSnapshot.getKey() + "\n");
                } else
                    System.err.println("\n\nUNKNOWN KEY!!!!!!!!\n\n");
                itemListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("\n\nERROR!!!!!!!!\n\n" + databaseError.toString());
            }
        };
        mDatabase.addChildEventListener(childEventListener);
    }

    void newPopulateData() {
        /*FirebaseListAdapter<Item> adapter = new FirebaseListAdapter<Item>(this, Item.class, R.layout.item_list_row, mDatabase) {
            @Override
            protected void populateView(View v, Item model, int position) {
                TextView tv_itemListName = (TextView) findViewById(R.id.tv_itemListName);
                tv_itemListName.setText(model.getItemName());
            }
        };
        */

        list = new ItemListAdapter(mDatabase, this, R.layout.item_list_row, "hi");
        itemListView  = (ListView) findViewById(R.id.itemListView);
        itemListView.setAdapter(list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        newPopulateData();
        /*
        listen = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Item item = new Item;
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                item.setMap(map);
                itemList.add(item);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }*/
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (itemListener != null) {
            mDatabase.removeEventListener(itemListener);
        }
    }

    private void extraStuff() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mainMenu);
        setSupportActionBar(toolbar);

        /*  Floating Action Bar */
        FloatingActionButton fabAddItem = (FloatingActionButton) findViewById(R.id.fab_addItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                 */
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
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.logout_option){
            FirebaseAuth.getInstance().signOut();
            login();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void login() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
