package com.projectmanagement.benson.homeinventoryapp;

/**
 * Created by Benson on 9/30/2016.
 */

/**
 * Using the Controller Design Pattern...Not finished yet...
 */
class ItemController {

    ItemController() {

    }

    void addItem(Item item) {
        DBManager db = new DBManager();
        db.addItemToDB(item);
    }
}
