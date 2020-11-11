package com.example.demo.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.lang.Math;

class Truck extends Object3D implements Updatable {
    private UUID uuid;

    private double x;
    private double y = 0;
    private double z;

    private boolean forward = true;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private ArrayList<Robot> availableRobots;

    public Truck(int x, int z) {
        this.x = x;
        this.z = z;
        this.speed = 0.2;
        this.uuid = UUID.randomUUID();
        this.inventory = new ArrayList<Integer>();
        this.availableRobots = new ArrayList<Robot>();

        System.out.println("Spawned truck");
    }

    @Override
    public boolean update() {

        if(this.forward){
            if(this.z + this.speed < 0.0){
                this.z += this.speed;
            } else {
                for(int i = 0; i < new Random().nextInt(3) + 1; i++){
                    this.inventory.add(new Random().nextInt(10));
                }
                
                for(Robot robot : this.availableRobots){
                    robot.addOrder(new Stellage(30, 0, 0));
                    robot.addOrder(new Stellage(0, 0, 0));
                    robot.addOrder(new Stellage(0, 0, 30));
                    robot.addOrder(new Stellage(0, 0, 0));
                }

                this.forward = false;
            }
        } else {
            // The truck doesn't move until the robots have unload it
            if(this.inventory.size() == 0){
                if(this.z - this.speed > -50){
                    this.z -= this.speed;
                } else {
                    this.status = false;
                }
            }
        }  
        
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

    // @Override
    // public void moveTo(double x, double y, double z){

    // }

    public void addRobot(Robot robot){
        this.availableRobots.add(robot);
    }
}