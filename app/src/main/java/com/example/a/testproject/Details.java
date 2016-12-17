package com.example.a.testproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class Details extends AppCompatActivity {


    Bitmap bitmap;
    ProgressDialog pDialog;

    ImageView imageView;

    public void Home(View view){

        startActivity(new Intent(Details.this,HomePage.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        String city=intent.getStringExtra("City");
        String street=intent.getStringExtra("Street");
        String area=intent.getStringExtra("Area");
        String house_no=intent.getStringExtra("House_number");
        int price=intent.getIntExtra("Price",-1);
        int contact=intent.getIntExtra("Contact",-1);
        String type=intent.getStringExtra("Type");
        String path = intent.getStringExtra("path");

        new LoadImage().execute("http://10.0.3.2/local_volley2/"+path);

        TextView City=(TextView) findViewById(R.id.detail_City);
        TextView Street=(TextView) findViewById(R.id.detail_Street);
        TextView Area=(TextView) findViewById(R.id.detail_Area);
        TextView House_no=(TextView) findViewById(R.id.detail_House_number);
        TextView Price=(TextView) findViewById(R.id.detail_price);
        TextView Contact=(TextView) findViewById(R.id.detail_Contact);
        TextView Type=(TextView) findViewById(R.id.detail_Type);
        imageView = (ImageView)findViewById(R.id.ivImageDetails);

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
            pDialog = new ProgressDialog(Details.this);
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
                Toast.makeText(Details.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
