package com.example.a.testproject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by A on 6/19/2016.
 */
public class Config extends StringRequest{

    public static final String DATA_URL = "http://bbkkirraj.netne.net/bibek/getData.php";
    public static final String DATA_URL1 = "http://bbkkirraj.netne.net/bibek/yourInfo.php";
    public static final String Delete_URL = "http://bbkkirraj.netne.net/bibek/deleteInfo.php";

//    public static final String DATA_URL = "http://10.0.3.2/local_volley2/getData.php";
//    public static final String DATA_URL1 = "http://10.0.3.2/local_volley2/yourInfo.php";
//    public static final String Delete_URL = "http://10.0.3.2/local_volley2/deleteInfo.php";

    public static final String KEY_id = "info_id";

    public static final String KEY_CITY = "City";
    public static final String KEY_STREET = "Street";
    public static final String KEY_AREA = "Area";
    public static final String KEY_HOUSE_NUMBER = "House_number";
    public static final String KEY_PRICE = "Price";
    public static final String KEY_CONTACT = "Contact";
    public static final String KEY_TYPE = "Type";
    public static final String JSON_ARRAY = "result";
    public static final String Path = "path";

    private Map<String, String> params;

    public  Config(String info_id, Response.Listener<String> Listener){
        super(Method.POST,Delete_URL, Listener, null);

        params = new HashMap<>();
        int info_id1 = Integer.parseInt(info_id);
        params.put("info_id", info_id1+"");

    }
    public Config(String city, String street, Response.Listener<String> Listener) {
        super(Request.Method.POST,DATA_URL, Listener,null);
        params=new HashMap<>();
        params.put("City",city);
        params.put("Street",street);
    }

    public Config(int id, Response.Listener<String> Listner){
        super(Method.POST, DATA_URL1, Listner, null);
        params = new HashMap<>();
        params.put("fid", id+"");
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
