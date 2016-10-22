package com.projectmanagement.benson.homeinventoryapp;

/**
 * Created by Benson on 9/30/2016.
 */

import com.projectmanagement.benson.homeinventoryapp.Models.Item;

/**
 * Using the Controller Design Pattern...Not finished yet...
 */
public class ItemController {

    private DBManager db;

    public ItemController(){
        db = new DBManager();
    }
    /*
        Adds the item to the DB
     */
    public void addItem(Item item) {
        db.addItemToDB(item);
    }

    public void updateItem(Item item) {
        db.updateItemToDB(item);
    }

    public void deleteItem(Item item) {
        db.deleteItem(item.getKey());
    }
}
