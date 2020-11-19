package com.example.demo.models.Order;

import com.example.demo.models.Stellage;

public abstract class Order {
    protected double x;
    protected double y;
    protected double z;
    public Stellage stellage;

    public Order(double x, double y, double z, Stellage stellage){
        this.x = x;
        this.y = y;
        this.z = z;
        this.stellage = stellage;
    }

    public abstract double getX();
    public abstract double getY();
    public abstract double getZ();
}