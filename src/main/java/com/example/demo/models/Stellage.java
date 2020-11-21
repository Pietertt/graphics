package com.example.demo.models;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Observer.*;
import com.example.demo.models.Dijkstra.Edge;

public class Stellage extends Object3D implements Updatable, Comparable<Stellage> {

    private UUID uuid;
    private String name;

    public double initX;
    public double initY;
    public double initZ;

    private double x;
    private double y = 0;
    private double z;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private boolean visited;
    private Stellage predecessor;
    private double distance = Double.MAX_VALUE;

    public EventManager events;
    private List<Edge> adjacenciesList;

    public Stellage(double x, double y, double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;

        this.initX = x;
        this.initY = y;
        this.initZ = z;

        this.uuid = UUID.randomUUID();
        this.events = new EventManager("loaded", "unloaded");
        this.adjacenciesList = new ArrayList<>();
    }

    public void addDestination(Edge edge){
        this.adjacenciesList.add(edge);
    }

    public String getName(){
        return this.name;
    }

    public List<Edge> getAdjacenciesList(){
        return this.adjacenciesList;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public void setPredecessor(Stellage predecessor){
        this.predecessor = predecessor;
    }

    public Stellage getPredecessor(){
        return this.predecessor;
    }

    public double getDistance(){
        return this.distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        /*
         * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde terug
         * te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie nodig
         * is op de client, en die verstuurd moet kunnen worden naar de browser. In de
         * javascript code wordt dit dan weer verder afgehandeld.
         */
        return Stellage.class.getSimpleName().toLowerCase();
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

    public void setX(double x){
        this.x = x;
    }

    public void setZ(double z){
        this.z = z;
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

    public void remove(){
        this.status = false;
        System.out.printf("My status is %s\n", this.status);
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int compareTo(Stellage node){
        return Double.compare(this.distance, node.getDistance());
    }
}
