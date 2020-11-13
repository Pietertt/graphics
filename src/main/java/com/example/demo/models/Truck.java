package com.example.demo.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.lang.Math;
import java.util.Iterator;

import com.example.demo.models.Observer.EventListener;

public class Truck extends Object3D implements Updatable, EventListener {
    private UUID uuid;

    private double x;
    private double y = 0;
    private double z;

    private boolean full = true;
    private boolean forward = true;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private ArrayList<Stellage> availableStellages;
    private ArrayList<Stellage> inventory;
    private ArrayList<Stellage> orderList;

    private ArrayList<Robot> availableRobots;

    public Truck(int x, int z) {
        this.x = x;
        this.z = z;
        this.speed = 0.2;
        this.uuid = UUID.randomUUID();
        this.availableStellages = new ArrayList<Stellage>();
        this.availableRobots = new ArrayList<Robot>();

        this.orderList = new ArrayList<Stellage>();
        this.inventory = new ArrayList<Stellage>();

        System.out.println("[TRUCK] Moving to warehouse");
    }

    @Override
    public boolean update() {

        if(this.forward){
            if(this.z + this.speed < 0.0){
                this.z += this.speed;
            } else {
                for(int i = 0; i < new Random().nextInt(3) + 1; i++){
                    Stellage stellage = this.availableStellages.get(new Random().nextInt(this.availableStellages.size() - 1));
                    this.orderList.add(stellage);
                }

                for(Robot robot : this.availableRobots){
                    robot.events.subscribe("deliver", this);
                }

                System.out.printf("[TRUCK] I want %s packages\n", this.orderList.size());

                int currentRobot = 0;

                for(int i = 0; i < this.orderList.size(); i++){
                    if(currentRobot == (this.availableRobots.size())){
                        currentRobot = 0;
                    }
                    this.availableRobots.get(currentRobot).addOrder(this.orderList.get(i));
                    currentRobot++;
                }

                this.forward = false;
            }
        } else {
            // The truck doesn't move until the robots have unload it
            if(!this.full){
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

    public void update(String event, String message){
        if(event == "deliver"){
            this.inventory.add(new Stellage(0, 0, 0));
            if(this.inventory.size() == this.orderList.size()){
                System.out.println("[TRUCK] Full! Returning");
                this.full = false;

                for(int i = 0; i < this.availableRobots.size(); i++){
                    this.availableRobots.get(i).events.unsubscribe(event, this);
                }
            }
        } 
    }

    public void addRobot(Robot robot){
        this.availableRobots.add(robot);
    }

    public void addStellage(Stellage stellage){
        this.availableStellages.add(stellage); 
    }
}