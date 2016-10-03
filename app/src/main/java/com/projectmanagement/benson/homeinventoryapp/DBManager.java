package com.projectmanagement.benson.homeinventoryapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benson on 9/30/2016.
 */

class DBManager {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    DBManager() {
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

    DatabaseReference getUserItemDB(){
        return mDatabase.child("user-items").child(getUserID());
    }

    FirebaseUser getUser() {
        return user;
    }

    String getUserEmail(){
        return user.getEmail();
    }

    private String getUserID(){
        return user.getUid();
    }

    void addItemToDB(Item item) {
        DatabaseReference newItem = mDatabase.child("user-items").child(getUserID()).push();

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
    }

    void updateItemToDB(Item item){
        String key = mDatabase.child("user-items").push().getKey();

        Map<String, Object> itemValues = item.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/items/" + key, itemValues);
        childUpdates.put("/user-items/" + getUserID() + "/" + key, itemValues);

        mDatabase.updateChildren(childUpdates);
    }
}
