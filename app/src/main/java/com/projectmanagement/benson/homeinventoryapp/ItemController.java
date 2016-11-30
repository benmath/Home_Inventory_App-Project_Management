package com.projectmanagement.benson.homeinventoryapp;

/**
 * Created by Benson on 9/30/2016.
 */

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.Models.List;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Using the Controller Design Pattern...Not finished yet...
 */
public class ItemController {

    private DBManager db;

    /*       Strings for the Categories      */
    private final static String LOCATION = "Location";
    private final static String TYPE = "Type";
    private final static String CURRENT_VALUE = "Current Value";

    public ItemController(){
        db = new DBManager();
    }

    /**
     * Adds an Item to the DB
     *
     * @param item  the new Item to be saved.
     */
    public void addItem(Item item) {
        db.addItemToDB(item);
    }

    /**
     * Updates the Item to the DB.
     *
     * @param item  The Item to be updated.
     */
    public void updateItem(Item item) {
        db.updateItemToDB(item);
    }

    /**
     * Deletes the Item from the DB.
     *
     * @param item  The Item to be deleted.
     */
    public void deleteItem(Item item) {
        db.deleteItem(item.getKey());
    }

    /**
     * The DB reference to get the currenlty logged in user's Items.
     *
     * @return A databaseReference that points to the user's Items.
     */
    public DatabaseReference getUserItems() {
        return db.getUserItems();
    }

    /**
     * The already adjusted Categories
     *
     * @return  a list of Categories
     */
    public ArrayList<String> getCategories(){
        ArrayList<String> list = new ArrayList<>();
        list.add(LOCATION);
        list.add(TYPE);
        list.add(CURRENT_VALUE);

        return list;
    }

    /**
     * A predefined location subcategories.
     *
     * @return a list of location subcategories.
     */
    private ArrayList<String> getLocationSubcategories(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Living Room");
        list.add("Family Room");
        list.add("Kitchen");
        list.add("Dining Room");
        list.add("Master Bedroom");
        list.add("Game Room");
        list.add("Bedroom 1");
        list.add("Bathroom 1");
        list.add("Garage");
        list.add("Patio");
        list.add("Front Porch");

        return list;
    }

    /**
     * A predefined Type subcategories.
     *
     * @return a list of Type subcategories.
     */
    private ArrayList<String> getTypeSubcategories() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Appliances");
        list.add("Furniture");
        list.add("Electronics");
        list.add("Accessories");
        list.add("Office Supplies");
        list.add("Movies");
        list.add("Music");
        list.add("Books");
        list.add("Toys and Video Games");
        list.add("Food");

        return list;
    }

    /**
     * A predefined Current Value subcategories.
     *
     * @return a list of Current Value subcategories.
     */
    private ArrayList<String> getCurrentValueSubcategories() {
        ArrayList<String> list = new ArrayList<>();
        list.add("$0 - $50");
        list.add("$50 - $100");
        list.add("$100 - $200");
        list.add("$200 - $500");
        list.add("$500 - $1000");
        list.add("$1000 - $5000");
        list.add("$5000 - $10000");
        list.add("$10000+");

        return list;
    }

    /**
     * Returns the list of Subcategories from a specific Category.
     *
     * @param category  The category to refer to.
     * @return          A list of subcategories according to the Category.
     */
    public ArrayList<String> getSubcategories(String category) {
        ArrayList<String> list;

        switch (category) {
            case LOCATION:
                list = getLocationSubcategories();
                break;
            case TYPE:
                list = getTypeSubcategories();
                break;
            case CURRENT_VALUE:
                list = getCurrentValueSubcategories();
                break;
            default:
                throw new IllegalStateException();
        }

        return list;
    }

    public void addList(List list) {
        db.addListToDB(list);
    }

    public void updateList(List list) {
        db.updateListToDB(list);
    }

    public void deleteList(List list) {
        db.deleteList(list.getListName());
    }

    /**
     * Queries the DB for the Subcategory Items.
     *
     * @param category      the Category serched for.
     * @param subcategory   the Subcategory serached for.
     * @return              the Query of those limits.
     */
    public Query findSubcategoryItems(String category, String subcategory) {
        System.out.println("Category: " + category + "\tSubcategory: " + subcategory);

        // just to make it compatible with the DB -__-
        switch (category) {
            case "Location":
                category = "loc";
                break;
            case "Type":
                category = "type";
                break;
            case "Current Value":
                category = "price";
                break;
        }

        return getUserItems().orderByChild(category).equalTo(subcategory);
    }

    public Query findListItems(List list) {
        return db.getUserListNames().equalTo(list.getListName());
    }

    public Query findListNames() {
        return db.getUserListNames();
    }

    public ArrayList<Item> getListItems(List list) {
        return db.getListItems(list);
    }
}

