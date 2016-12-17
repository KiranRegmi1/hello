package com.example.a.testproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    static String gender = "";
    public void rgGender(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.gender_Female:
                if (checked)
                    gender = "Female";
                break;
            case R.id.gender_Male:
                if (checked)
                    gender = "Male";
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void btnRegister(View view){
        final EditText F_name =(EditText)findViewById(R.id.etFirst_Name);
        final EditText M_name=(EditText)findViewById(R.id.etMiddle_Name);
        final EditText L_name=(EditText)findViewById(R.id.etLast_Name);
        final EditText Username=(EditText)findViewById(R.id.etUsername);
        final EditText Password=(EditText)findViewById(R.id.etPassword);
        final EditText Contact=(EditText)findViewById(R.id.etContact);
        final EditText Address=(EditText)findViewById(R.id.etAddress);
        final EditText Email=(EditText)findViewById(R.id.etEmail);


        final String fname=F_name.getText().toString();
        final String mname=M_name.getText().toString();
        final String lname=L_name.getText().toString();
        final String username=Username.getText().toString();
        final String password=Password.getText().toString();
        final String contact=Contact.getText().toString();
        final String address= Address.getText().toString();
        final String email=Email.getText().toString();

        boolean a=fname.isEmpty();
        boolean b=fname.matches("[a-zA-Z]+");
        boolean c=lname.isEmpty();
        boolean d=lname.matches("[a-zA-Z]+");
        boolean e=username.matches("[a-zA-Z]+([0-9])*");
        boolean f=password.length()<6;
        boolean g=contact.isEmpty();
        boolean h=email.isEmpty();
        boolean i=email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        if (a) {
            F_name.requestFocus();
            F_name.setError("* Required");
        } else if (!b) {
            F_name.requestFocus();
            F_name.setError("Enter Only Alphabetical Characters");
        } else if (c) {
            L_name.requestFocus();
            L_name.setError("* Required");
        } else if (!d) {
            L_name.requestFocus();
            L_name.setError("Enter Only Alphabetical Characters");
        } else if (!e) {
            Username.requestFocus();
            Username.setError("Invalid Username");
        } else if (f) {
            Password.requestFocus();
            Password.setError("Enter atleast 6 characters");
        } else if (g) {
            Contact.requestFocus();
            Contact.setError("* Required");
        } else if (h) {
            Email.requestFocus();
            Email.setError("* Required");
        } else if (!i) {
            Email.requestFocus();
            Email.setError("Invalid Email id");
        }

        else {
            Response.Listener<String> responseListner = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        Boolean success = jsonObject.getBoolean("success");

                        if (success) {
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            Toast.makeText(getApplicationContext(), "Registration Successful, " +
                                    "Please Login", Toast.LENGTH_LONG).show();

                        } else if (!success) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                            builder.setMessage("Username Already Exists!!!")
                                    .setNegativeButton("Retry", null)
                                    .create().show();


                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                            builder.setMessage("Registration Failed")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };


            SignUpRequest registerRequest = new SignUpRequest(fname, mname, lname, username, password, contact, gender, address, email, responseListner);
            RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
            queue.add(registerRequest);

        }

    }

}
