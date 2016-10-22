package com.projectmanagement.benson.homeinventoryapp.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benson on 9/30/2016.
 */

/*
 *	Class: Item
 *	Description: Model for a basic Item object
 */
public class Item implements Serializable{

    private String itemName;
    private String brand;
    private String model;
    private String serialNumber;
    private String type;
    private String loc;
    private String locDescription;
    private String datePurch;
    private double price;
    private String expireDate;
    private String condition;
    private String notes;
    private String key;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDatePurch() {
        return datePurch;
    }

    public void setDatePurch(String date) {
        datePurch = date;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String date) {
        expireDate = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLocDescription() {
        return locDescription;
    }

    public void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    // Used in conjunction with the Firebase Database
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> item = new HashMap<>();

        item.put("itemName", itemName);
        item.put("brand", brand);
        item.put("model", model);
        item.put("serialNumber", serialNumber);
        item.put("type", type);
        item.put("loc", loc);
        item.put("locDescription", locDescription);
        item.put("datePurch", datePurch);
        item.put("price", price);
        item.put("expireDate", expireDate);
        item.put("condition", condition);
        item.put("notes", notes);
        item.put("key", key);

        return item;
    }

    public void setMap(Map<String, String> map) {
        itemName = map.get("itemName");
        brand = map.get("brand");
        model = map.get("model");
        serialNumber = map.get("serialNumber");
        type = map.get("type");
        loc = map.get("loc");
        locDescription = map.get("locDescription");
        datePurch = map.get("datePruch");
        if(!map.get("price").equals(""))
            price = Double.parseDouble(map.get("price").trim());
        else
            price = 0;
        expireDate = map.get("expireDate");
        condition = map.get("condition");
        notes = map.get("notes");
        key = map.get("key");
    }

    @Override
    public String toString(){
        return  "Name " + itemName + "\n" +
                "Brand " + brand + "\n" +
                "Model " + model + "\n" +
                "Serial Number " + serialNumber + "\n" +
                "Type " + type + "\n" +
                "Location " + loc + "\n" +
                "Location Description " + locDescription + "\n" +
                "Date Purchased " + datePurch + "\n" +
                "Price " + price + "\n" +
                "Expiration Date " + expireDate + "\n" +
                "Condition " + condition + "\n" +
                "Notes " + notes + "\n" +
                "Key " + key + "\n";
    }
}
