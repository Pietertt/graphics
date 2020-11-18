package com.example.demo.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.lang.Math;
import java.util.Iterator;

import com.example.demo.models.Observer.EventManager;
import com.example.demo.models.Observer.EventListener;
import com.example.demo.models.Order;

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

    private ArrayList<Stellage> inventory;
    private ArrayList<Order> orderList;
    private ArrayList<Robot> availableRobots;
    private ArrayList<Stellage> availableStellages;

    private EventManager events;

    public Truck(int x, int z) {
        this.x = x;
        this.z = z;
        this.speed = 0.2;
        this.uuid = UUID.randomUUID();
        this.availableStellages = new ArrayList<Stellage>();
        this.availableRobots = new ArrayList<Robot>();

        this.orderList = new ArrayList<Order>();


        this.inventory = new ArrayList<Stellage>();
        this.events = new EventManager("full");

        System.out.println("[TRUCK] Moving to warehouse");
    }

    @Override
    public boolean update() {

        if(this.forward){
            if(this.z + this.speed < 0.0){
                this.z += this.speed;
            } else {
                for(Robot robot : this.availableRobots){
                    robot.events.subscribe("deliver", this);
                    this.events.subscribe("full", robot);
                }

                int wishings = 0;
                int orders = 0;
                for(Order order : this.orderList){
                    if(order.getType() == "wish"){
                        wishings++;
                    } else {
                        orders++;
                    }
                }

                System.out.printf("[TRUCK] I got %s packages\n", wishings);
                System.out.printf("[TRUCK] I want %s packages\n", orders);

                int currentRobot = 0;

                // for(int i = 0; i < this.wishingList.size(); i++){
                //     if(currentRobot == (this.availableRobots.size())){
                //         currentRobot = 0;
                //     }
                //     this.availableRobots.get(currentRobot).addWishing(this.wishingList.get(i));
                //     currentRobot++;
                // }

                currentRobot = 0;

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
                    this.remove();
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
                    this.events.notify("full", "");
                    this.events.unsubscribe("full", this.availableRobots.get(i));
                    this.availableRobots.get(i).events.unsubscribe(event, this);
                }
            }
        } 
    }

    public void addRobot(Robot robot){
        this.availableRobots.add(robot);
    }

    public void addAvailableStellage(Stellage stellage){
        this.availableStellages.add(stellage);
    }

    public void addOrder(Order order){
        this.orderList.add(order); 
    }

    public void remove(){
        this.status = false;
    }
}