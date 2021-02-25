package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import jdk.internal.event.Event;

import com.example.demo.models.Observer.EventManager;
import com.example.demo.models.Observer.EventListener;
import com.example.demo.models.Order.Request;
import com.example.demo.models.Order.Deliver;
import com.example.demo.models.Order.Order;
import com.example.demo.models.Dijkstra.Node;
import com.example.demo.models.Dijkstra.Edge;
import com.example.demo.models.Dijkstra.Dijkstra;
import com.example.demo.strategies.*;

public class Robot extends Object3D implements Updatable, EventListener {

    private boolean returning = false;

    private Node graph;
    private Dijkstra dijkstra;
    private ArrayList<Node> nodes;
    private Strategy strategy;
    private ArrayList<Order> orders;
    public EventManager events;

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param z
     */
    public Robot(double x, double y, double z) {
        super(x, y, z);
        this.orders = new ArrayList<Order>();

        this.setSpeed(0.1);
        this.setUUID(UUID.randomUUID());
        this.events = new EventManager("loaded", "unloaded");

        this.graph = new Node(5, 2, 5, "S");
        this.dijkstra = new Dijkstra();
        this.nodes = this.dijkstra.spawnNodes();
        this.graph.addDestination(new Edge(3, this.graph, this.nodes.get((3))));
        this.setStrategy(new GetRequest());
    }

    /**
     * Update the robot
     * 
     * @return boolean
     */
    @Override
    public boolean update() {
        this.move();
        return true;
    }

    /**
     * Gets the type
     * 
     * @return string
     */
    @Override
    public String getType() {
        return Robot.class.getSimpleName().toLowerCase();
    }

    /**
     * Het the number of deliverables
     * 
     * @return int
     */
    public int getDeliverables(){
        int amount = 0;
        for(Order order : this.orders){
            if(order instanceof Deliver){
                amount = amount + 1;
            }
        }
        return amount;
    }

    /**
     * Get the number of requests
     * 
     * @return int
     */
    public int getRequests(){
        int amount = 0;
        for(Order order : this.orders){
            if(order instanceof Request){
                amount = amount + 1;
            }
        }
        return amount;
    }

    /**
     * Get the first order
     * 
     * @return Order
     */
    public Order getFirstOrder(){
        return this.orders.get(0);
    }

    /**
     * Moves the robot and any stellages it is carrying
     */
    public void move(){
        if(this.getDeliverables() > 0){
            if(this.returning == false){
                Point point = new Point(this.getX(), this.getY(), this.getZ());
                Point newPoint = this.strategy.execute(this.getFirstOrder(), point);
                this.setX(newPoint.getX());
                this.setZ(newPoint.getZ());

                if (newPoint.getFlag()) {
                    this.setStrategy(new ReturnRequest());
                    this.returning = true;
                }
            } else {
                Point point = new Point(this.getX(), this.getY(), this.getZ());
                Point newPoint = this.strategy.execute(this.getFirstOrder(), point);
                this.setX(newPoint.getX());
                this.setZ(newPoint.getZ());

                if (newPoint.getFlag()) {
                    this.events.notify("unloaded", "unloaded");
                    this.getFirstOrder().stellage.events.notify("unloaded", this.getFirstOrder().stellage.getUUID());
                    this.getFirstOrder().stellage.status = true;
                    this.removeOrder(this.getFirstOrder());
                    this.setStrategy(new GetRequest());
                    this.returning = false;
                }
            }            
        }

        if(this.getRequests() > 0){
            if(this.getDeliverables() == 0){
                if(this.returning == false){
                    Point point = new Point(this.getX(), this.getY(), this.getZ());
                    Point newPoint = this.strategy.execute(this.getFirstOrder(), point);
                    this.setX(newPoint.getX());
                    this.setZ(newPoint.getZ());

                    if (newPoint.getFlag()) {
                        this.setStrategy(new ReturnRequest());
                        this.returning = true;
                    }
                } else {
                    Point point = new Point(this.getX(), this.getY(), this.getZ());
                    Point newPoint = this.strategy.execute(this.getFirstOrder(), point);
                    this.setX(newPoint.getX());
                    this.setZ(newPoint.getZ());

                    if (newPoint.getFlag()) {
                        this.events.notify("loaded", "loaded");
                        this.getFirstOrder().stellage.events.notify("loaded", this.getFirstOrder().stellage.getUUID());
                        this.getFirstOrder().stellage.status = true;
                        this.removeOrder(this.getFirstOrder());
                        this.setStrategy(new GetRequest());
                        this.returning = false;
                    }
                }
            }
        } 
    }

    /**
     * Remove an order from the orderlist
     * 
     * @param index
     */
    public void removeOrder(int index) {
        this.orders.remove(index);
    }

    /**
     * Remove an order from the orderlist
     * 
     * @param order
     */
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    /**
     * Add an order to the orderlist and compute the shortest path
     * 
     * @param order
     */
    public void addOrder(Order order) {
        this.orders.add(order);

        this.dijkstra.computerShortestPath(this.graph);

        Node no = new Node();

        for (Node node : this.nodes) {
            if (node.getName().equals(order.stellage.getName())) {
                no = node;
                List<Node> nodes = this.dijkstra.getShortestPathTo(node);
                for (Node n : nodes) {
                    order.addNode(n);
                }
            }
        }

        System.out.printf("[ROBOT] Moving stellage %s via path %s\n", order.stellage.getName(), this.dijkstra.getShortestPathTo(no));
    }

    /**
     *  Update the robot
     */
    @Override
    public void update(String event, String message) {
   
    }

    /**
     * Set strategy
     * 
     * @param strategy
     */
    private void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}