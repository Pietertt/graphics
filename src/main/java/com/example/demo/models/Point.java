package com.example.demo.models;


public class Point {

    private double x;
    private double y;
    private double z;
    private boolean returning;

    private boolean flag = false;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {

    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setZ(double z){
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setReturning(boolean returning) {
        this.returning = returning;
    }

    public boolean getReturning() {
        return this.returning;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }
}