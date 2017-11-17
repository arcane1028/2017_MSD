package com.jsh.ms_hw10_201302480;

/**
 * Created by JSH on 2017-11-17.
 */

public class PannelItem {

    String name;
    String phone;
    String city;
    int resId;

    public PannelItem(String name, String phone, String city, int resId) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
