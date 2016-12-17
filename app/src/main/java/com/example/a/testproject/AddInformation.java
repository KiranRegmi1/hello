package com.example.a.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddInformation extends AppCompatActivity {

    String[] city = {"Kathmandu", "Pokhara", "Birgunj", "Biratnagar", "Baglung",
            "Bhaktapur", "Nuwakot", "Lalitput", "Butwal", "Dharan", "Narayanghat",
            "Galkot", "Kusma", "Hetauda"};

    String[] street = {
            "Buspark", "Baneswor", "Lazimpat", "Narayanthan", "Chakrapath", "Kalanki",
            "Balkhu", "Maitighar", "Maharajgunj", "Talimkendra", "Naxal"};

    String[] area = {"Khursanitar", "Laxmi Marg", "DhungeDhara", "KaloTanki", "Manamaiju", "Kalopul"
            , "Thamel", "Nayabazar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AutoCompleteTextView acTvCity = (AutoCompleteTextView) findViewById(R.id.acTvCityadd);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.select_dialog_item, city);

        acTvCity.setThreshold(1);
        acTvCity.setAdapter(adapter1);


        AutoCompleteTextView acTvStreet = (AutoCompleteTextView) findViewById(R.id.acTvStreetadd);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.select_dialog_item, street);

        acTvStreet.setThreshold(1);
        acTvStreet.setAdapter(adapter2);


        AutoCompleteTextView acTvArea = (AutoCompleteTextView) findViewById(R.id.acTvAreaadd);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.select_dialog_item, area);

        acTvArea.setThreshold(1);
        acTvArea.setAdapter(adapter3);


    }


    public void btnSubmit(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String id1 = sharedPreferences.getString("id", "");


        final EditText acTvCity = (EditText) findViewById(R.id.acTvCityadd);
        final EditText acTvStreet = (EditText) findViewById(R.id.acTvStreetadd);
        final EditText acTvArea = (EditText) findViewById(R.id.acTvAreaadd);
        final EditText etPrice = (EditText) findViewById(R.id.etPriceadd);
        final EditText etContact2 = (EditText) findViewById(R.id.etContactadd);
        final Spinner spinnerType = (Spinner) findViewById(R.id.spinnerTypeadd);
        final EditText etHouseNo = (EditText) findViewById(R.id.etHouse_Noadd);

        if (acTvCity.getText().toString().isEmpty()) {
            acTvCity.requestFocus();
            acTvCity.setError("* Required");

        } else if (acTvStreet.getText().toString().isEmpty()) {
            acTvStreet.requestFocus();
            acTvStreet.setError("* Required");

        } else if (acTvArea.getText().toString().isEmpty()) {
            acTvArea.requestFocus();
            acTvArea.setError("* Required");

        } else if (etPrice.getText().toString().isEmpty()) {
            etPrice.requestFocus();
            etPrice.setError("* Required");

        } else if (etContact2.getText().toString().isEmpty()) {
            etContact2.requestFocus();
            etContact2.setError("* Required");
        } else {


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

//        int id1 = sharedPreferences.getInt("id", Integer.parseInt(""));

            //            int id = Integer.parseInt(id1);


            Intent intent = new Intent(AddInformation.this,ImageUpload.class);

            SharedPreferences sharedPreferences2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences2.edit();
//                            editor.putInt("id",id);

            String contact2 = Integer.toString(contact);
            String price2 = Integer.toString(price);

            editor.putString("id",id1);
            editor.putString("city",city);
            editor.putString("street",street);
            editor.putString("area",area);
            editor.putString("house_no",house_no);
            editor.putString("contact",contact2);
            editor.putString("price",price2);
            editor.putString("type",type);


            editor.apply();

            startActivity(intent);
        }
    }
}

