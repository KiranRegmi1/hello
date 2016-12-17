package com.example.a.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageUpload extends AppCompatActivity {

    GalleryPhoto galleryPhoto;
    CameraPhoto cameraPhoto;

    final int CAMERA_REQUEST = 1323;
    final int GALLERY_REQUEST = 3434;
    String selectedPhoto;
    ImageView ivCamera, ivGallery, ivUpload, ivImage;


    public void tvSkip(View view) {
       // Log.i("Data show","slkfsdk");

        Toast.makeText(ImageUpload.this, "inside success",Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferences2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String id1 = sharedPreferences2.getString("id", "");
        int id = Integer.parseInt(id1);
        String city = sharedPreferences2.getString("city","");
        String street = sharedPreferences2.getString("street","");
        String area = sharedPreferences2.getString("area","");
        String house_no = sharedPreferences2.getString("house_no","");
        String contact2 = sharedPreferences2.getString("contact","");
        String price2 = sharedPreferences2.getString("price","");
        String type = sharedPreferences2.getString("type","");
        int contact = Integer.parseInt(contact2);
        int price = Integer.parseInt(price2);

        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {

                        Intent intent = new Intent(ImageUpload.this, HomePage.class);

                        startActivity(intent);
                        Toast.makeText(ImageUpload.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageUpload.this);
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

        AddInfoRequest addInfoRequest = new AddInfoRequest(id, city, street, area, house_no, price, contact, type,responseListner);

        RequestQueue queue = Volley.newRequestQueue(ImageUpload.this);
        queue.add(addInfoRequest);
    }


    public void ivUpload(View view){
        Toast.makeText(ImageUpload.this, "inside ivUpload", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences2 = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String id1 = sharedPreferences2.getString("id", "");
        int id = Integer.parseInt(id1);
        String city = sharedPreferences2.getString("city","");
        String street = sharedPreferences2.getString("street","");
        String area = sharedPreferences2.getString("area","");
        String house_no = sharedPreferences2.getString("house_no","");
        String contact2 = sharedPreferences2.getString("contact","");
        String price2 = sharedPreferences2.getString("price","");
        String type = sharedPreferences2.getString("type","");
        int contact = Integer.parseInt(contact2);
        int price = Integer.parseInt(price2);

        String encodedImage = "";

        if(selectedPhoto == null){
            Toast.makeText(getApplicationContext(), "No Image Selected",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Bitmap bitmap= ImageLoader.init().from(selectedPhoto).requestSize(1024,1024).getBitmap();
            encodedImage = ImageBase64.encode(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {

                        Intent intent = new Intent(ImageUpload.this, HomePage.class);

                        startActivity(intent);
                        Toast.makeText(ImageUpload.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageUpload.this);
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

        AddInfoRequest addInfoRequest = new AddInfoRequest(id, city, street, area, house_no, price, contact, type, encodedImage, responseListner);

        RequestQueue queue = Volley.newRequestQueue(ImageUpload.this);
        queue.add(addInfoRequest);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());

        ivCamera=(ImageView)findViewById(R.id.ivCamera);
        ivGallery=(ImageView)findViewById(R.id.ivGallery);
        ivUpload=(ImageView)findViewById(R.id.ivUpload);
        ivImage=(ImageView)findViewById(R.id.ivImage);


        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong while taking photos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                String photoPath = cameraPhoto.getPhotoPath();
                selectedPhoto = photoPath;
                Bitmap bitmap = null;
                try {
                    bitmap= ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
                    ivImage.setImageBitmap(getRotatedBitmap(bitmap, 90));
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong while loading photos",
                            Toast.LENGTH_SHORT).show();
                }
            }

            else if(requestCode == GALLERY_REQUEST){

                galleryPhoto.setPhotoUri(data.getData());
                String photoPath=galleryPhoto.getPath();
                selectedPhoto = photoPath;

                try {
                    Bitmap bitmap= ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
                    ivImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong while choosing photos",
                            Toast.LENGTH_SHORT).show();
                }

            }

        }
    }

    private Bitmap getRotatedBitmap(Bitmap source, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap bitmap1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        return bitmap1;
    }

}
