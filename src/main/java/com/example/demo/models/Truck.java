package com.example.demo.models;

import java.util.Random;
import java.util.UUID;
import java.lang.Math;

class Truck implements Object3D, Updatable {
    private UUID uuid;

    private double x;
    private double y = 0;
    private double z;

    private boolean forward = true;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Truck(int x, int z) {
        this.x = x;
        this.z = z;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public boolean update() {

        double z = this.z;
        
        double speed = 0.1;

        if(forward){
            if((z + speed <= 0.0) && (z + speed >= -20)){
                this.z += speed;
            } else {
                forward = false;
            }
        } else {
            if((z - speed >= 0.0) && (z - speed <= -20)){
                this.z -= speed;
            } else {
                forward = true;
            }
        }



        // if((z + randomZ <= 30.0) && (z + randomZ >= 0.0)){
        //     this.z += randomZ;
        // }
        
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Truck.class.getSimpleName().toLowerCase();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }
}