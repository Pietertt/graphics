package com.example.demo.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.lang.Math;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

import com.example.demo.models.Observer.EventManager;
import com.example.demo.models.Observer.EventListener;
import com.example.demo.models.Order.Request;
import com.example.demo.models.Order.Deliver;
import com.example.demo.models.Order.Order;

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

    private Map<String, Integer> inventory = new HashMap<>();
    private ArrayList<Order> orderList;
    private ArrayList<Robot> availableRobots;
    private ArrayList<Stellage> availableStellages;
    private ArrayList<Stellage> unavailableStellages;

    private EventManager events;

    public Truck(int x, int z) {
        this.x = x;
        this.z = z;
        this.speed = 0.2;
        this.uuid = UUID.randomUUID();
        this.availableStellages = new ArrayList<Stellage>();
        this.unavailableStellages = new ArrayList<Stellage>();
        this.availableRobots = new ArrayList<Robot>();
        this.inventory.put("delivering", 0);
        this.inventory.put("request", 0);
        this.inventory.put("loaded", 0);
        this.inventory.put("unloaded", 0);

        this.orderList = new ArrayList<Order>();

        this.events = new EventManager("full");

        System.out.println("[TRUCK] Moving to warehouse");
    }

    @Override
    public boolean update() {

        if(this.forward){
            if(this.z + this.speed < 0.0){
                this.z += this.speed;
            } else {

                this.generateOrders();
                
                for(Robot robot : this.availableRobots){
                    robot.events.subscribe("loaded", this);
                    robot.events.subscribe("unloaded", this);
                    this.events.subscribe("full", robot);
                }

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
            if(this.canLeave()){
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
        this.inventory.put(message, this.inventory.get(message) + 1);
    }

    public void addRobot(Robot robot){
        this.availableRobots.add(robot);
    }

    public void addAvailableStellage(Stellage stellage){
        this.availableStellages.add(stellage);
    }

    public void addUnavailableStellages(Stellage stellage){
        this.unavailableStellages.add(stellage);
    }

    public void addOrder(Order order){
        this.orderList.add(order); 
    }

    public void remove(){
        this.status = false;
    }

    public void generateOrders(){
        Random random = new Random();

        ArrayList<Integer> orderedUnavailable = new ArrayList<Integer>();
        ArrayList<Integer> orderedAvailable = new ArrayList<Integer>();

        if(this.unavailableStellages.size() > 0){
            for(int i = 0; i < random.nextInt(this.unavailableStellages.size()); i++){
                while(true){
                    boolean appearance = false;
                    int r = random.nextInt(this.unavailableStellages.size());
                    for(int ordered : orderedUnavailable){
                        if(ordered == r){
                            appearance = true;
                        }
                    }

                    if(!appearance){
                        orderedUnavailable.add(r);
                        Stellage stellage = this.unavailableStellages.get(r);
                        Deliver order = new Deliver(stellage.initX, stellage.initY, stellage.initZ, stellage);
                        this.inventory.put("delivering", this.inventory.get("delivering") + 1);
                        this.addOrder(order);
                        break;
                    }
                }
            }
        }

        if(this.availableStellages.size() > 0){
            for(int i = 0; i < random.nextInt(this.availableStellages.size()); i++){
                while(true){
                    boolean appearance = false;
                    int r = random.nextInt(this.availableStellages.size());
                    for(int ordered : orderedAvailable){
                        if(ordered == r){
                            appearance = true;
                        }
                    }

                    if(!appearance){
                        orderedAvailable.add(r);
                        Stellage stellage = this.availableStellages.get(r);
                        Request order = new Request(stellage.initX, stellage.initY, stellage.initZ, stellage);
                        this.addOrder(order);
                        this.inventory.put("request", this.inventory.get("request") + 1);
                        break;
                    }
                }
            }
        }
    }

    private int getLoaded(){
        return this.inventory.get("loaded");
    }

    private int getRequest(){
        return this.inventory.get("request");
    }

    private int getUnloaded(){
        return this.inventory.get("unloaded");
    }

    private int getDelivered(){
        return this.inventory.get("delivering");
    }

    private boolean canLeave(){
        if(this.getRequest() == this.getLoaded()){
            if(this.getDelivered() - this.getUnloaded() == 0){
                return true;
            }
        }
        return false;
    }
}
