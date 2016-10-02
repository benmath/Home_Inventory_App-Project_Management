package com.projectmanagement.benson.homeinventoryapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Benson on 9/30/2016.
 */

class DBManager {

    private DatabaseReference mDatabase;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private FirebaseUser user;

    DBManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    String getUserEmail(){
        return user.getEmail();
    }

    String getUserID(){
        return user.getUid();
    }

    /*


    private String itemName;
    private String brand;
    private String model;
    private String serialNumber;
    private String type;
    private String loc;
    private String locDescription;
    private Date datePurch;
    private float price;
    private Date expireDate;
    private String condition;
    private String notes;

    */

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
        String key = mDatabase.child("items").push().getKey();

        Map<String, Object> itemValues = item.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/items/" + key, itemValues);
        childUpdates.put("/user-items/" + getUserID() + "/" + key, itemValues);

        mDatabase.updateChildren(childUpdates);
    }
}
