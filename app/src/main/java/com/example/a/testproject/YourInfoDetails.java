package com.example.a.testproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class YourInfoDetails extends AppCompatActivity {

    String city, street, area, house_no, type;
    int price, contact, info_id;

    Bitmap bitmap;
    ProgressDialog pDialog;
    ImageView imageView;

    public void Home(View view){
        startActivity(new Intent(YourInfoDetails.this,HomePage.class));
    }


    public void btnDelete(View view){

        getData2();

    }

    private void getData2(){

        String info_id1 = Integer.toString(info_id);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {

                        startActivity(new Intent(YourInfoDetails.this, HomePage.class));

                        Toast.makeText(getApplicationContext(),"Data Deleted Successfullly",Toast.LENGTH_LONG).show();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(YourInfoDetails.this);
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

        Config getData = new Config(info_id1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(YourInfoDetails.this);
        queue.add(getData);
    }


    public void btnEdit(View view){

        SharedPreferences sharedPreferences = getSharedPreferences("editInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String contact2 = Integer.toString(contact);
        String price2 = Integer.toString(price);
        String info_id1 = Integer.toString(info_id);


        editor.putString("city",city);
        editor.putString("street",street);
        editor.putString("area",area);
        editor.putString("house_no",house_no);
        editor.putString("price",price2);
        editor.putString("contact",contact2);
        editor.putString("info_id",info_id1);

        editor.apply();

        startActivity(new Intent(YourInfoDetails.this, EditInfoArea.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_info_details);

        Intent intent=getIntent();
        info_id = intent.getIntExtra("info_id",-1);
        city=intent.getStringExtra("City");
        street=intent.getStringExtra("Street");
        area=intent.getStringExtra("Area");
        house_no=intent.getStringExtra("House_number");
        price=intent.getIntExtra("Price",-1);
        contact=intent.getIntExtra("Contact",-1);
        type=intent.getStringExtra("Type");
        String path=intent.getStringExtra("path");

        new LoadImage().execute("http://bbkkirraj.netne.net/bibek/"+path);

        TextView City=(TextView) findViewById(R.id.detail_City);
        TextView Street=(TextView) findViewById(R.id.detail_Street);
        TextView Area=(TextView) findViewById(R.id.detail_Area);
        TextView House_no=(TextView) findViewById(R.id.detail_House_number);
        TextView Price=(TextView) findViewById(R.id.detail_price);
        TextView Contact=(TextView) findViewById(R.id.detail_Contact);
        TextView Type=(TextView) findViewById(R.id.detail_Type);
        imageView = (ImageView)findViewById(R.id.ivYourInfo);

        City.setText("CITY: "+city);
        Street.setText("STREET: "+street);
        Area.setText("AREA: "+area);
        House_no.setText("House number: "+house_no);
        Price.setText("Price: "+price);
        Contact.setText("Contact: "+contact);
        Type.setText("Type: "+type);

    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(YourInfoDetails.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                imageView.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(YourInfoDetails.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
