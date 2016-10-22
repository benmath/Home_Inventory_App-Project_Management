package com.projectmanagement.benson.homeinventoryapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    /*
        Initialize the DB
     */
    public DBManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

    public DatabaseReference getDefaultDB() {
        return mDatabase;
    }

    public DatabaseReference getUserItems(){
        return mDatabase.child("user-items").child(getUserID());
    }

    public FirebaseUser getUser() {
        return user;
    }

    public String getUserEmail(){
        return user.getEmail();
    }

    private String getUserID(){
        return user.getUid();
    }

    /*
        Adds the item to the database
     */
    void addItemToDB(Item item) {
        DatabaseReference newItem = mDatabase.child("user-items").child(getUserID()).push();
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

    /*
        updates the item to the DB
     */
    void updateItemToDB(Item item){
        //String key = mDatabase.child("user-items").child(getUserID()).getKey();
        String key = item.getKey();

        System.out.println("\nKEY: " + key);
        Map<String, Object> itemValues = item.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/user-items/" + getUserID() + "/" + key, itemValues);

        mDatabase.updateChildren(childUpdates);
    }

    void deleteItem(String key) {
        mDatabase.child("user-items").child(getUserID()).child(key).removeValue();
    }

}
