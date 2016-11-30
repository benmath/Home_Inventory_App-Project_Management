package com.projectmanagement.benson.homeinventoryapp.Models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Benson on 11/13/2016.
 */

public class List implements Serializable {
    private String listName;
    private ArrayList<String> listKeys;
    private String keys;

    public List() {
        listKeys = new ArrayList<>();
    }

    public ArrayList<String> getListKeys() {
        if (keys != null)
            convertStringToList();
        return listKeys;
    }

    public void setListKeys(ArrayList<String> listKeys) {
        this.listKeys = listKeys;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void addKey(String key) {
        listKeys.add(key);
    }

    public void removeKey(String key) {        listKeys.remove(listKeys.indexOf(key));
    }

    public String getKeys() {
        if (listKeys != null)
            convertListToString();
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
        if (keys != null)
            convertStringToList();
    }



    public void convertListToString() {
        StringBuilder build = new StringBuilder();
        for (String s : listKeys) {
            build.append(s);
            build.append(",");
        }
        keys = build.toString();
    }

    public void convertStringToList() {
        listKeys = new ArrayList<>(Arrays.asList(keys.split(",")));
    }


    // Used in conjunction with the Firebase Database
    @Exclude
    public Map<String, String> toMap() {
        HashMap<String, String> item = new HashMap<>();

        item.put("listName", listName);
        item.put("keys", getKeys());

        return item;
    }

    public void setMap(Map<String, String> map) {
        listName = map.get("listName");
        setKeys(map.get("keys"));
    }
}
