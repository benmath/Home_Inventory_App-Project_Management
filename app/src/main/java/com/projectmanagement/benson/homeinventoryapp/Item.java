package com.projectmanagement.benson.homeinventoryapp;

import com.google.firebase.database.Exclude;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benson on 9/30/2016.
 */

class Item {

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

    public Item() {

    }

    public String getBrand() {
        return brand;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCondition() {
        return condition;
    }

    void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDatePurch() {
        return datePurch;
    }

    void setDatePurch(String date) {
        datePurch = date;
    }

    public String getExpireDate() {
        return expireDate;
    }

    void setExpireDate(String date) {
        expireDate = date;
    }

    public String getItemName() {
        return itemName;
    }

    void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLoc() {
        return loc;
    }

    void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLocDescription() {
        return locDescription;
    }

    void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public String getModel() {
        return model;
    }

    void setModel(String model) {
        this.model = model;
    }

    public String getNotes() {
        return notes;
    }

    void setNotes(String notes) {
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

    void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }


    /*
    private String stringToSqlDate(String date) {
        //assuming that the dates are stored with a '/' as a delimiter

        String[] s = new String[3];
        String[] result = date.split("/");
        System.arraycopy(result, 0, s, 0, result.length);

        return s[2] + "-" + s[0] + "-" +s[1]; //yyyy-mm-dd
    }
    */


    @Exclude
    Map<String, Object> toMap() {
        HashMap<String, Object> item = new HashMap<>();

        item.put("itemName", itemName);
        item.put("brand", brand);
        item.put("serialNumber", serialNumber);
        item.put("type", type);
        item.put("loc", loc);
        item.put("locDescription", locDescription);
        item.put("datePurch", datePurch);
        item.put("price", price);
        item.put("expireDate", expireDate);
        item.put("condition", condition);
        item.put("notes", notes);

        return item;
    }

    void setMap(Map<String, String> map) {
        itemName = map.get("itemName");
        brand = map.get("brand");
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
    }
}
