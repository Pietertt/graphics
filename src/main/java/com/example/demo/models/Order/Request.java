package com.example.demo.models.Order;

import com.example.demo.models.Stellage;

public class Request extends Order {

    public Request(double x, double y, double z, Stellage stellage){
        super(x, y, z, stellage);
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
}