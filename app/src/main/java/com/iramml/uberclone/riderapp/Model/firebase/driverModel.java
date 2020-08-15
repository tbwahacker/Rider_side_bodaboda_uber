package com.iramml.uberclone.riderapp.Model.firebase;


import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

//@IgnoreExtraProperties
public class driverModel {
    private String phone,name;
    private Double lat,lng;


    public driverModel() {
    }

    public driverModel(String phone, String name, Double lat, Double lng) {
        this.phone = phone;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    private double total;


//    @Exclude
//    public Map<String, Object> toMap(){
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("name", drivername);
//        result.put("phone", phonenumber);
//        return result;
//    }


}
