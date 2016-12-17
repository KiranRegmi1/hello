package com.example.a.testproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by A on 6/19/2016.
 */
public class SignUpRequest extends StringRequest {

       private static final String REGISTER_REQUEST_URL="http://bbkkirraj.netne.net/bibek/register.php";
//    private static final String REGISTER_REQUEST_URL="http://10.0.3.2/local_volley2/register.php";
//    private static final String REGISTER_REQUEST_URL="http://bbkchdhry.comxa.com/volley/register.php";

    private Map<String,String> params;

    public SignUpRequest(String fname, String mname, String lname, String username, String password, String contact,
                         String gender, String address, String email, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);

        params=new HashMap<>();
        params.put("First_Name",fname);
        params.put("Middle_Name",mname);
        params.put("Last_Name",lname);
        params.put("Username",username);
        params.put("Password",password);
        params.put("Contact",contact);
        params.put("Gender",gender);
        params.put("Address", address);
        params.put("Email", email);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
