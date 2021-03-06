package com.example.a.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YourInfoArea extends AppCompatActivity {

    public RecyclerView recyclerView;
    String city, street, area, house_no, type, path;
    int price, contact, info_id;
    ArrayList<Information> list=new ArrayList<Information>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_info_area);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData1();
    }

    public void Home(View view){

        startActivity(new Intent(YourInfoArea.this, HomePage.class));
    }

    public void getData1() {
        Intent intent =getIntent();
        int id = intent.getIntExtra("id",-1);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);


            }
        };

        Config getData = new Config(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(YourInfoArea.this);
        queue.add(getData);
    }


    private void showJSON(String response) {

        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {

                JSONObject collegeData = result.getJSONObject(i);

                info_id = collegeData.getInt(Config.KEY_id);
                city = collegeData.getString(Config.KEY_CITY);
                street = collegeData.getString(Config.KEY_STREET);
                area = collegeData.getString(Config.KEY_AREA);
                house_no=collegeData.getString(Config.KEY_HOUSE_NUMBER);
                price=collegeData.getInt(Config.KEY_PRICE);
                contact=collegeData.getInt(Config.KEY_CONTACT);
                type=collegeData.getString(Config.KEY_TYPE);
                path=collegeData.getString(Config.Path);

                Information information=new Information(info_id,city,street,area,house_no,price,contact,type,path);
                    list.add(information);

                    InformationAdapter2 informationAdapter = new InformationAdapter2(list,this);
                    recyclerView.setAdapter(informationAdapter);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
