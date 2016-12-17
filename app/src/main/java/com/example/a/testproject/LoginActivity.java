package com.example.a.testproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);

    }
    public void tvRegister(View view){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
    public void btnLogin(View view){
        if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {

            Toast.makeText(getApplicationContext(), "Enter Both Username and Password", Toast.LENGTH_LONG).show();
        } else {
            final String username = etUsername.getText().toString();
            final String password = etPassword.getText().toString();

            Response.Listener<String> responseListner = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {

                            int id = jsonObject.getInt("id");

                            String id1 = Integer.toString(id);

                            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("id",id1);

                            editor.apply();

                            Intent intent = new Intent(LoginActivity.this, HomePage.class);

                            LoginActivity.this.startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Welcome to Rental Help System",Toast.LENGTH_LONG).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

            LoginRequest loginRequest = new LoginRequest(username, password, responseListner);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
        }
    }

}
