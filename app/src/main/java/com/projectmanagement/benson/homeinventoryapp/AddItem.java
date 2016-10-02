package com.projectmanagement.benson.homeinventoryapp;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddItem extends AppCompatActivity {

    private EditText et_itemName, et_brand, et_model, et_serialNum, et_type, et_locDesc, et_datePurch,
            et_price, et_expireDate, et_notes;
    private Spinner loc_spin, condition_spin;
    private Button btn_save, btn_cancel;
    private Item item;
    private ItemController itemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        item = new Item();
        itemController = new ItemController();

        connectFields();

    }

    private void connectFields() {
        et_itemName = (EditText) findViewById(R.id.et_itemName);
        et_brand = (EditText) findViewById(R.id.et_brand);
        et_model = (EditText) findViewById(R.id.et_model);
        et_serialNum = (EditText) findViewById(R.id.et_serialNumber);
        et_type = (EditText) findViewById(R.id.et_type);
        loc_spin = (Spinner) findViewById(R.id.loc_spin);
        et_locDesc = (EditText) findViewById(R.id.et_locDes);
        et_datePurch = (EditText) findViewById(R.id.et_datePurch);
        et_price = (EditText) findViewById(R.id.et_price);
        et_expireDate = (EditText) findViewById(R.id.et_expireDate);
        condition_spin = (Spinner) findViewById(R.id.condition_spin);
        et_notes = (EditText) findViewById(R.id.et_notes);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_save = (Button) findViewById(R.id.btn_save);

        et_datePurch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction f = getFragmentManager().beginTransaction();
                    dialog.show(f, "DatePicker");
                }
            }
        });

        et_expireDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction f = getFragmentManager().beginTransaction();
                    dialog.show(f, "DatePicker");
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
                save();
                finish();
            }
        });
    }

    private void getInfo() {
        item.setItemName(et_itemName.getText().toString().trim());
        item.setBrand(et_brand.getText().toString().trim());
        item.setModel(et_model.getText().toString().trim());
        item.setSerialNumber(et_serialNum.getText().toString().trim());
        item.setType(et_type.getText().toString().trim());
        item.setLocDescription(et_locDesc.getText().toString());
        item.setDatePurch(et_datePurch.getText().toString().trim());
        item.setPrice(et_price.getText().toString().trim());
        item.setExpireDate(et_expireDate.getText().toString().trim());
        item.setNotes(et_notes.getText().toString());

        //list of locations found under strings.xml
        loc_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item.setLoc(String.valueOf(parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                item.setLoc(" ");
            }
        });

        condition_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item.setCondition(String.valueOf(parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                item.setCondition(" ");
            }
        });
    }


    private void save() {
        itemController.addItem(item);
    }
}
