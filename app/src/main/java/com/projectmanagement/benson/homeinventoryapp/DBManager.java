package com.projectmanagement.benson.homeinventoryapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benson on 9/30/2016.
 */

public class DBManager {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    /**
     * Initializes the Firebase DB
     */
    public DBManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    /**
     * Retrieves the Firebase Authorization.
     *
     * @return  Firebase Authorization.
     */
    public FirebaseAuth getAuth() {
        return mAuth;
    }

    /**
     * The default DB reference.
     *
     * @return default DB reference.
     */
    public DatabaseReference getDefaultDB() {
        return mDatabase;
    }

    /**
     * The default DB query to get the current user'd Items.
     *
     * @return  The DB Reference for a user's Items.
     */
    public DatabaseReference getUserItems(){
        return mDatabase.child(getUserID()).child("Items");
    }

    /**
     * The currently logged in Firebase User.
     *
     * @return the firebase user.
     */
    public FirebaseUser getUser() {
        return user;
    }

    /**
     * The user's email...maybe for another iteration...
     *
     * @return the current user's email
     */
    public String getUserEmail(){
        return user.getEmail();
    }

    /**
     * The user's unique ID.
     * @return the user's unique Firebase ID.
     */
    private String getUserID(){
        return user.getUid();
    }

    /**
     * Adds an Item to the DB.
     *
     * @param item  The new item to be be saved.
     */
    void addItemToDB(Item item) {
        DatabaseReference newItem = mDatabase.child(getUserID()).child("Items").push();
        item.setKey(newItem.getKey());

        newItem.child("itemName").setValue(item.getItemName());
        newItem.child("brand").setValue(item.getBrand());
        newItem.child("model").setValue(item.getModel());
        newItem.child("serialNumber").setValue(item.getSerialNumber());
        newItem.child("type").setValue(item.getType());
        newItem.child("loc").setValue(item.getLoc());
        newItem.child("locDescription").setValue(item.getLocDescription());
        newItem.child("datePurch").setValue(item.getDatePurch());
        newItem.child("price").setValue(item.getPrice());
        newItem.child("expireDate").setValue(item.getExpireDate());
        newItem.child("condition").setValue(item.getCondition());
        newItem.child("notes").setValue(item.getNotes());
        newItem.child("key").setValue(item.getKey());

    }

    /**
     * Updates the DB with the Item.
     *
     * @param item  an Item that has been updated.
     */
    void updateItemToDB(Item item){
        String key = item.getKey();
        Map<String, Object> itemValues = item.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(getUserID() +"/Items/" + key, itemValues);

        mDatabase.updateChildren(childUpdates);
    }

    /**
     * Deletes a unique Item based on a key.
     *
     * @param key   A Firebase generated key.
     */
    void deleteItem(String key) {
        mDatabase.child(getUserID()).child("Items").child(key).removeValue();
    }

}
