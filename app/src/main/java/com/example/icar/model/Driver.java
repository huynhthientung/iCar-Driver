package com.example.icar.model;

public class Driver {
    public String uid;
    public String full_name;
    public String email;
    public String phone;
    public String address;
    public String birthday;
    public boolean gender;

    public Driver(String uid, String full_name, String email, String phone, String address, String birthday, boolean gender) {
        this.uid = uid;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Driver() {

    }
}
