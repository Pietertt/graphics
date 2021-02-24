package com.example.demo.models;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Observer.*;
import com.example.demo.models.Dijkstra.Edge;

public class Stellage extends Object3D implements Updatable, Comparable<Stellage> {

    private String name;

    private double initX;
    private double initY;
    private double initZ;

    private boolean visited;
    private Stellage predecessor;
    private double distance = Double.MAX_VALUE;

    public EventManager events;
    private List<Edge> adjacenciesList;

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param z
     * @param name
     */
    public Stellage(double x, double y, double z, String name) {
        super(x, y, z);
        this.name = name;

        this.initX = x;
        this.initY = y;
        this.initZ = z;

        this.setUUID(UUID.randomUUID());
        this.events = new EventManager("loaded", "unloaded");
        this.adjacenciesList = new ArrayList<>();
    }

    /**
     * Add an destination
     * 
     * @param edge
     */
    public void addDestination(Edge edge){
        this.adjacenciesList.add(edge);
    }

    /**
     * Returns the name of this stellag
     * 
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * Return the adjacencies list
     * 
     * @return List<Edge>
     */
    public List<Edge> getAdjacenciesList(){
        return this.adjacenciesList;
    }

    /**
     * Returns visited or not
     * 
     * @return boolean
     */
    public boolean isVisited(){
        return this.visited;
    }

    /**
     * Mark this stellage as visited
     * 
     * @param visited
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    /**
     * Set the current stellage
     * 
     * @param predecessor
     */
    public void setPredecessor(Stellage predecessor){
        this.predecessor = predecessor;
    }

    /**
     * Return the previous stellage
     * 
     * @return Stellage
     */
    public Stellage getPredecessor(){
        return this.predecessor;
    }

    /**
     * Get the distance
     * 
     * @return double
     */
    public double getDistance(){
        return this.distance;
    }

    /**
     * Set the distance
     * 
     * @param distance
     */
    public void setDistance(double distance){
        this.distance = distance;
    }

    /**
     * Update
     * 
     * @return booleaon
     */
    @Override
    public boolean update() {
        return true;
    }

    /**
     * Get the type
     * 
     * @return String
     */
    @Override
    public String getType() {
        return Stellage.class.getSimpleName().toLowerCase();
    }

    /**
     * Return the name of the stellage
     * 
     * @return String
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Compare the distance
     * 
     * @return int
     */
    @Override
    public int compareTo(Stellage node){
        return Double.compare(this.distance, node.getDistance());
    }

    /**
     * Get initX
     * 
     * @return double
     */
    public double getInitX() {
        return this.initX;
    }

    /**
     * Set initX
     * 
     * @param x
     */
    public void setInitX(double x) {
        this.initX = x;
    }

    /**
     * Get initY
     * 
     * @return double
     */
    public double getInitY() {
        return this.initY;
    }

    /**
     * Set initY
     * 
     * @param y
     */
    public void setInitY(double y) {
        this.initX = y;
    }

    /**
     * Get initZ
     * 
     * @return double
     */
    public double getInitZ() {
        return this.initZ;
    }

    /**
     * Set initZ
     * 
     * @param z
     */
    public void setInitZ(double z) {
        this.initX = z;
    }
}
