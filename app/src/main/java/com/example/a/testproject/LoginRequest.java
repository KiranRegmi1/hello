package com.example.a.testproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by A on 6/19/2016.
 */
public class LoginRequest extends StringRequest {

     private static final String Login_URL="http://bbkkirraj.netne.net/bibek/login.php";
//   private static final String Login_URL="http://10.0.3.2/local_volley2/login.php";
    //private static final String Login_URL="http://bbkchdhry.comxa.com/volley/login.php";

    private Map<String,String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Method.POST, Login_URL, listener, null);

        params=new HashMap<>();
        params.put("Username",username);
        params.put("Password",password);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
