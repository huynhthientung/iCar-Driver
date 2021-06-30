package com.example.icar.model;

public class Driver {
    public String uid;
    public String full_name;
    public String email;
    public String phone;
    public String address;
    public String birthday;
    public boolean gender;
    public String license_type;

    public Driver(String uid, String full_name, String email, String phone, String address, String birthday, boolean gender, String license_type) {
        this.uid = uid;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.license_type = license_type;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "uid='" + uid + '\'' +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", license_type=" + license_type +
                '}';
    }

    public Driver() {
    }
}
