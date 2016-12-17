package com.example.a.testproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by A on 6/19/2016.
 */
public class AddInfoRequest extends StringRequest {

     private static final String AddInfo_URL="http://bbkkirraj.netne.net/bibek/add_info.php";
    private static final String AddInfo_URL2="http://bbkkirraj.netne.net/bibek/noImageUpload.php";
    private static final String EditInfo_URL="http://bbkkirraj.netne.net/bibek/edit_info.php";
    //private static final String AddInfo_URL2="http://10.0.3.2/local_volley2/noImageUpload.php";
    //private static final String EditInfo_URL="http://10.0.3.2/local_volley2/edit_info.php";
   //private static final String AddInfo_URL="http://bbkchdhry.comxa.com/volley/add_info.php";


    private Map<String,String> params;

    public AddInfoRequest(int info_id, String city, String street, String area, String house_no,
                          int price, int contact, String type,int id, Response.Listener<String> listner){

        super(Method.POST, EditInfo_URL, listner, null);

        params=new HashMap<>();
        params.put("info_id",info_id+"");
        params.put("id",id+"");
        params.put("City",city);
        params.put("Street",street);
        params.put("Area",area);
        params.put("House_number",house_no);
        params.put("Price",price+"");
        params.put("Contact",contact+"");
        params.put("Type",type);
    }

    public AddInfoRequest(int id, String city, String street, String area, String house_no, int price,
                          int contact, String type, Response.Listener<String> listner){

        super(Method.POST, AddInfo_URL2, listner, null);

        params=new HashMap<>();
        params.put("id",id+"");
        params.put("City",city);
        params.put("Street",street);
        params.put("Area",area);
        params.put("House_number",house_no);
        params.put("Price",price+"");
        params.put("Contact",contact+"");
        params.put("Type",type);
    }

    public AddInfoRequest(int id, String city, String street, String area, String house_no,
                          int price, int contact, String type, String encodedImage, Response.Listener<String> listner){

        super(Method.POST, AddInfo_URL, listner, null);

        params=new HashMap<>();
        params.put("image",encodedImage);
        params.put("id",id+"");
        params.put("City",city);
        params.put("Street",street);
        params.put("Area",area);
        params.put("House_number",house_no);
        params.put("Price",price+"");
        params.put("Contact",contact+"");
        params.put("Type",type);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
