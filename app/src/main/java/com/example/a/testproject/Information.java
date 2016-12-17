package com.example.a.testproject;

/**
 * Created by A on 6/20/2016.
 */
public class Information {

    public Information(String city, String street, String area, String house_no, int price, int contact, String type, String path){

        this.setInfo_city(city);
        this.setInfo_street(street);
        this.setInfo_area(area);
        this.setInfo_house_no(house_no);
        this.setInfo_price(price);
        this.setInfo_contact(contact);
        this.setInfo_type(type);
        this.setInfo_path(path);

    }

    public Information(int info_id, String city, String street, String area, String house_no, int price,
                       int contact, String type, String path){
        this.setInfo_id(info_id);
        this.setInfo_city(city);
        this.setInfo_street(street);
        this.setInfo_area(area);
        this.setInfo_house_no(house_no);
        this.setInfo_price(price);
        this.setInfo_contact(contact);
        this.setInfo_type(type);
        this.setInfo_path(path);
    }

    String info_city, info_street, info_area, info_house_no, info_type, info_path;
    int info_price, info_contact, info_id;

    public String getInfo_path() {
        return info_path;
    }

    public void setInfo_path(String info_path) {
        this.info_path = info_path;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getInfo_house_no() {
        return info_house_no;
    }

    public void setInfo_house_no(String info_house_no) {
        this.info_house_no = info_house_no;
    }

    public String getInfo_type() {
        return info_type;
    }

    public void setInfo_type(String info_type) {
        this.info_type = info_type;
    }

    public int getInfo_price() {
        return info_price;
    }

    public void setInfo_price(int info_price) {
        this.info_price = info_price;
    }

    public int getInfo_contact() {
        return info_contact;
    }

    public void setInfo_contact(int info_contact) {
        this.info_contact = info_contact;
    }

    public String getInfo_city() {
        return info_city;
    }

    public void setInfo_city(String info_city) {
        this.info_city = info_city;
    }

    public String getInfo_street() {
        return info_street;
    }

    public void setInfo_street(String info_street) {
        this.info_street = info_street;
    }

    public String getInfo_area() {
        return info_area;
    }

    public void setInfo_area(String info_area) {
        this.info_area = info_area;
    }
}
