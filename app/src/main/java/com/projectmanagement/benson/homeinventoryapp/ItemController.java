package com.projectmanagement.benson.homeinventoryapp;

/**
 * Created by Benson on 9/30/2016.
 */

class ItemController {

    public ItemController() {

    }

    void addItem(Item item) {
        DBManager db = new DBManager();
        db.addItemToDB(item);
    }
}
