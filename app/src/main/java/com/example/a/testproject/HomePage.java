package com.example.a.testproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private AutoCompleteTextView city;
    private AutoCompleteTextView street;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void btnAddInfo(View view){
        startActivity(new Intent(HomePage.this, AddInformation.class));
    }

    public void btnYourInfo(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String id1 = sharedPreferences.getString("id", "");
        int id = Integer.parseInt(id1);

        Intent intent= new Intent(HomePage.this, YourInfoArea.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }

    public void btnSearch(View view){
        city=(AutoCompleteTextView)findViewById(R.id.actCity);
        street=(AutoCompleteTextView)findViewById(R.id.actStreet);
        String city1=city.getText().toString().trim();
        String street1=street.getText().toString().trim();

        if(city1.equals("")&& street1.equals("")){
            Toast.makeText(this,"Please enter City or Street",Toast.LENGTH_LONG).show();
            return;
        }

        else{
            loading= ProgressDialog.show(this,"Please wait...","Fetching....",false,false);
            Intent intent=new Intent(HomePage.this,SearchDisplayArea.class);
            intent.putExtra("city",city1);
            intent.putExtra("street",street1);
            startActivity(intent);
        }
    }

}
