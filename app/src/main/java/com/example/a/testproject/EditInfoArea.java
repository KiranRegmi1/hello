package com.example.a.testproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EditInfoArea extends AppCompatActivity {

    AutoCompleteTextView City, Street, Area;
    EditText House_no, Price, Contact;
    String info_id;
    int info_id1;


    public void btnUpdate(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String id1 = sharedPreferences.getString("id","");


        int id = Integer.parseInt(id1);


//        Toast.makeText(EditInfoArea.this, info_id, Toast.LENGTH_SHORT).show();
        info_id1 = Integer.parseInt(info_id);

        final EditText acTvCity = (EditText) findViewById(R.id.acTvCity);
        final EditText acTvStreet = (EditText) findViewById(R.id.acTvStreet);
        final EditText acTvArea = (EditText) findViewById(R.id.acTvArea);
        final EditText etHouseNo = (EditText) findViewById(R.id.etHouse_No);
        final EditText etPrice = (EditText) findViewById(R.id.etPrice);
        final EditText etContact2 = (EditText) findViewById(R.id.etContact);
        final Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);

        if (acTvCity.getText().toString().isEmpty()) {
            acTvCity.requestFocus();
            acTvCity.setError("* Required");

        }
        else if (acTvStreet.getText().toString().isEmpty()) {
            acTvStreet.requestFocus();
            acTvStreet.setError("* Required");

        }
        else if (acTvArea.getText().toString().isEmpty()) {
            acTvArea.requestFocus();
            acTvArea.setError("* Required");

        }

        else if (etPrice.getText().toString().isEmpty()) {
            etPrice.requestFocus();
            etPrice.setError("* Required");

        }
        else if (etContact2.getText().toString().isEmpty()) {
            etContact2.requestFocus();
            etContact2.setError("* Required");
        }

        else {

            final String city = acTvCity.getText().toString();
            final String street = acTvStreet.getText().toString();
            final String area = acTvArea.getText().toString();
            final String house_no = etHouseNo.getText().toString();
            final int price = Integer.parseInt(etPrice.getText().toString());
            final int contact = Integer.parseInt(etContact2.getText().toString());
            final String type = spinnerType.getSelectedItem().toString();


            spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(), spinnerType.getSelectedItem().toString(),
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Response.Listener<String> responseListner = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);

                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {

                            Intent intent = new Intent(EditInfoArea.this, HomePage.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Data Updated Successfullly",Toast.LENGTH_LONG).show();

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditInfoArea.this);
                            builder.setMessage("Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

            AddInfoRequest addInfoRequest = new AddInfoRequest(info_id1, city, street, area, house_no, price, contact, type, id, responseListner);

            RequestQueue queue = Volley.newRequestQueue(EditInfoArea.this);
            queue.add(addInfoRequest);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_area);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("editInfo", Context.MODE_PRIVATE);

        String city = sharedPreferences.getString("city","");
        String street = sharedPreferences.getString("street","");
        String area = sharedPreferences.getString("area","");
        String house_no = sharedPreferences.getString("house_no","");
        String price = sharedPreferences.getString("price","");
        String contact = sharedPreferences.getString("contact","");
        info_id = sharedPreferences.getString("info_id","");


        City=(AutoCompleteTextView) findViewById(R.id.acTvCity);
        Street=(AutoCompleteTextView) findViewById(R.id.acTvStreet);
        Area=(AutoCompleteTextView) findViewById(R.id.acTvArea);
        House_no=(EditText) findViewById(R.id.etHouse_No);
        Price=(EditText) findViewById(R.id.etPrice);
        Contact=(EditText) findViewById(R.id.etContact);

        City.setText(city);
        Street.setText(street);
        Area.setText(area);
        House_no.setText(house_no);
        Price.setText(price);
        Contact.setText(contact);

    }

}
