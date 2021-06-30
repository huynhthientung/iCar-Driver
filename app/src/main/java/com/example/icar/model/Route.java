package com.example.icar.model;

public class Route {
    String uid;
    String name;
    String province;

    public Route(String uid, String name, String province){
        this.uid = uid;
        this.name = name;
        this.province = province;
    }
    @Override
    public String toString() {
        return "Route{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", province=" + province +
                '}';
    }

    public Route() {
    }
}
