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
    
    private boolean full = true;
    private boolean forward = true;

    private Map<String, Integer> inventory = new HashMap<>();
    private ArrayList<Order> orderList;
    private ArrayList<Robot> availableRobots;
    private ArrayList<Stellage> availableStellages;
    private ArrayList<Stellage> unavailableStellages;

    private EventManager events;

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param z
     */
    public Truck(double x, double y, double z) {
        super(x, y, z);
        this.setSpeed(0.2);
        this.setUUID(UUID.randomUUID());

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

    /**
     * Remove the truck
     */
    public void finalize() throws Throwable {
        System.out.println("[TRUCK] Removing");
    }

    /**
     * Move the truck for- or backwards and spread the orders over the robots when the truck is at the warehouse
     */
    @Override
    public boolean update() {
        if (this.forward) {
            if (this.z + this.speed < 0.0) {
                this.z += this.speed;
            } else {
                this.generateOrders();
                for (Robot robot : this.availableRobots) {
                    robot.events.subscribe("loaded", this);
                    robot.events.subscribe("unloaded", this);
                    this.events.subscribe("full", robot);
                }

                int currentRobot = 0;
                for (int i = 0; i < this.orderList.size(); i++) {
                    if (currentRobot == (this.availableRobots.size())) {
                        currentRobot = 0;
                    }
                    this.availableRobots.get(currentRobot).addOrder(this.orderList.get(i));
                    currentRobot++;
                }
                this.forward = false;
            }
        } else {
            if (this.canLeave()) {
                if (this.z - this.speed > -50) {
                    this.z -= this.speed;
                } else {
                    this.remove();
                }
            }
        }  
        return true;
    }

    /**
     * Gets the type
     */
    @Override
    public String getType() {
        return Truck.class.getSimpleName().toLowerCase();
    }

    /**
     * Update
     */
    public void update(String event, String message){
        this.inventory.put(message, this.inventory.get(message) + 1);
    }

    /**
     * Add a robot
     * 
     * @param robot
     */
    public void addRobot(Robot robot){
        this.availableRobots.add(robot);
    }

    /**
     * Add an available stellage
     * 
     * @param stellage
     */
    public void addAvailableStellage(Stellage stellage){
        this.availableStellages.add(stellage);
    }

    /**
     * Add an unavailable stellage
     * 
     * @param stellage
     */
    public void addUnavailableStellages(Stellage stellage){
        this.unavailableStellages.add(stellage);
    }

    /**
     * Adds an order to the orderlist
     * 
     * @param order
     */
    public void addOrder(Order order){
        this.orderList.add(order); 
    }

    /**
     * Generate orders
     */
    public void generateOrders(){
        Random random = new Random();

        ArrayList<Integer> orderedUnavailable = new ArrayList<Integer>();
        ArrayList<Integer> orderedAvailable = new ArrayList<Integer>();

        if (this.unavailableStellages.size() > 0) {
            for (int i = 0; i < random.nextInt(this.unavailableStellages.size()); i++) {
                while (true) {
                    boolean appearance = false;
                    int r = random.nextInt(this.unavailableStellages.size());
                    for (int ordered : orderedUnavailable){
                        if (ordered == r) {
                            appearance = true;
                        }
                    }

                    if (!appearance) {
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

        if (this.availableStellages.size() > 0) {
            for (int i = 0; i < random.nextInt(this.availableStellages.size()); i++) {
                while (true) {
                    boolean appearance = false;
                    int r = random.nextInt(this.availableStellages.size());
                    for (int ordered : orderedAvailable) {
                        if (ordered == r) {
                            appearance = true;
                        }
                    }

                    if (!appearance) {
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

    /**
     * Gets loaded
     * 
     * @return int
     */
    private int getLoaded(){
        return this.inventory.get("loaded");
    }

    /**
     * Gets requests
     * 
     * @return int
     */
    private int getRequest(){
        return this.inventory.get("request");
    }

    /**
     * Gets unloaded
     * 
     * @return int
     */
    private int getUnloaded(){
        return this.inventory.get("unloaded");
    }

    /**
     * Gets deliverables
     * 
     * @return int
     */
    private int getDelivered(){
        return this.inventory.get("delivering");
    }

    /**
     * Checks whether the robot can leave
     * 
     * @return boolean
     */
    private boolean canLeave(){
        if(this.getRequest() == this.getLoaded()){
            if(this.getDelivered() - this.getUnloaded() == 0){
                return true;
            }
        }
        return false;
    }
}
