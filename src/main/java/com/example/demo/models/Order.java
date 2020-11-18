package com.example.demo.models;

import com.example.demo.models.Stellage;

public class Order {
    private double x;
    private double y;
    private double z;

    private String type;
    public Stellage stellage;

    public Order(double x, double y, double z, Stellage stellage, String type){
        this.x = x;
        this.y = y;
        this.z = z;
        this.stellage = stellage;
        this.type = type;

    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getZ(){
        return this.z;
    }

    public String getType(){
        return this.type;
    }
}