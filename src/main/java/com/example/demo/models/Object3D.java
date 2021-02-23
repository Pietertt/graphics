package com.example.demo.models;

import java.util.ArrayList;
import java.util.UUID;

/*
 * Deze interface beschrijft wat een 3D model is. Het is een interface omdat alleen de
 * methoden worden gegeven die een object moet implementeren om een 3D model te kunnen zijn.
 * Merk op dat hier alleen getters in staan, en geen setters. Dit heeft te maken met de
 * uitvoering van het proxy pattern, en het feit dat in deze software eigenlijk bijna geen
 * setters nodig zijn.
 */

public abstract class Object3D {
    public Boolean status = true;
    public Boolean deleted = false;
    public double speed;

    public double x;
    public double y;
    public double z;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private UUID uuid;
    
    public abstract String getType();

    public Object3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        return this.uuid.toString();
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

    public void setRotationX(double rotation) {
        this.rotationX = rotation;
    }

    public void setRotationY(double rotation) {
        this.rotationY = rotation;
    }

    public void setRotationZ(double rotation) {
        this.rotationZ = rotation;
    }

    public double getRotationX() {
        return this.rotationX;
    }

    public double getRotationY() {
        return this.rotationY;
    }

    public double getRotationZ() {
        return this.rotationZ;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void remove(){
        this.status = false;
    }
}