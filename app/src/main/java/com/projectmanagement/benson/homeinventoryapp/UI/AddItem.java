package com.projectmanagement.benson.homeinventoryapp.UI;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projectmanagement.benson.homeinventoryapp.DateDialog;
import com.projectmanagement.benson.homeinventoryapp.ItemController;
import com.projectmanagement.benson.homeinventoryapp.Models.Item;
import com.projectmanagement.benson.homeinventoryapp.R;

public class AddItem extends AppCompatActivity {

    private EditText et_itemName, et_brand, et_model, et_serialNum, et_type, et_locDesc, et_datePurch,
            et_price, et_expireDate, et_notes;
    private Spinner loc_spin, condition_spin;
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

    // connects the fields and assigns listeners
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
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        Button btn_save = (Button) findViewById(R.id.btn_save);

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

                if (!item.getItemName().equals("")) {
                    save();
                    finish();
                } else {
                    Toast.makeText(AddItem.this, "Please Insert an Item Name", Toast.LENGTH_SHORT).show();
                    et_itemName.setError(getString(R.string.error_field_required));
                    View focusView = et_itemName;
                    focusView.requestFocus();
                }
            }
        });

        assert condition_spin != null;
        assert loc_spin != null;
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


    private void getInfo() {
        item.setItemName(et_itemName.getText().toString().trim());
        item.setBrand(et_brand.getText().toString().trim());
        item.setModel(et_model.getText().toString().trim());
        item.setSerialNumber(et_serialNum.getText().toString().trim());
        item.setType(et_type.getText().toString().trim());
        item.setLocDescription(et_locDesc.getText().toString());
        item.setDatePurch(et_datePurch.getText().toString().trim());
        if (!et_price.getText().toString().trim().equals(""))
            item.setPrice(Double.parseDouble(et_price.getText().toString().trim()));
        else
            item.setPrice(0);
        item.setExpireDate(et_expireDate.getText().toString().trim());
        item.setNotes(et_notes.getText().toString());
    }

    /*
        Save to the Firebase database
     */
    private void save() {
        itemController.addItem(item);
    }
}
