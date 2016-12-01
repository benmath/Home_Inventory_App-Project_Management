package com.projectmanagement.benson.homeinventoryapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.R;

import java.io.Serializable;

public class ViewItem extends AppCompatActivity implements Serializable {

    private TextView tv_view_itemBrand, tv_view_model, tv_view_serialNum, tv_view_type, tv_view_locDesc, tv_view_datePurch,
            tv_view_price, tv_view_expireDate, tv_view_notes, tv_view_loc, tv_view_cond;
    private Item item;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        item = (Item) getIntent().getSerializableExtra("Item"); // retrieve item
        getToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEditItem();
            }
        }); // edit item button

        connectFields();
        setInfo();
    }

    /**
     * Retrieve the Toolbar information
     */
    private void getToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(item.getItemName());
        setSupportActionBar(toolbar);
    }

    /**
     * Navigates the user to the Edit Item Activity
     */
    private void goToEditItem() {
        Intent editItem = new Intent(ViewItem.this, EditItem.class);
        editItem.putExtra("EditItem", item);
        startActivityForResult(editItem, 1);
    }

    /**
     * Updates the View Item Activity when the user saves and edited Item.
     *
     * @param requestCode   the request value when returning
     * @param resultCode    the result code if it was successful
     * @param data          the data being sent back
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
            if (resultCode == RESULT_OK) {
                item = (Item) data.getSerializableExtra("ItemUpdate");
                getToolbar();
                toolbar.setTitle(item.getItemName());
                setInfo();
            }
    }

    /**
     * Connects the fields from the XML layout resource
     */
    private void connectFields() {
        tv_view_itemBrand = (TextView) findViewById(R.id.tv_view_itemBrand);
        tv_view_model = (TextView) findViewById(R.id.tv_view_model);
        tv_view_serialNum = (TextView) findViewById(R.id.tv_view_serialNum);
        tv_view_type = (TextView) findViewById(R.id.tv_view_type);
        tv_view_loc = (TextView) findViewById(R.id.tv_view_loc);
        tv_view_locDesc = (TextView) findViewById(R.id.tv_view_locDesc);
        tv_view_datePurch = (TextView) findViewById(R.id.tv_view_datePurch);
        tv_view_price = (TextView) findViewById(R.id.tv_view_price);
        tv_view_expireDate = (TextView) findViewById(R.id.tv_view_expDate);
        tv_view_cond = (TextView) findViewById(R.id.tv_view_cond);
        tv_view_notes = (TextView) findViewById(R.id.tv_view_notes);
    }

    /**
     * Prints the retrieved Item information onto the TextView fields
     */
    private void setInfo() {
        tv_view_itemBrand.setText(item.getBrand());
        tv_view_model.setText(item.getModel());
        tv_view_serialNum.setText(item.getSerialNumber());
        tv_view_type.setText(item.getType());
        tv_view_loc.setText(item.getLoc());
        tv_view_locDesc.setText(item.getLocDescription());
        tv_view_datePurch.setText(item.getDatePurch());
        tv_view_price.setText("$" + String.valueOf(item.getPrice()));
        tv_view_expireDate.setText(item.getExpireDate());
        tv_view_cond.setText(item.getCondition());
        tv_view_notes.setText(item.getNotes());
    }
}
