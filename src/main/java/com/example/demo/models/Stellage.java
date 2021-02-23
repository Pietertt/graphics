package com.example.demo.models;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Observer.*;
import com.example.demo.models.Dijkstra.Edge;

public class Stellage extends Object3D implements Updatable, Comparable<Stellage> {

    private String name;

    public double initX;
    public double initY;
    public double initZ;

    private boolean visited;
    private Stellage predecessor;
    private double distance = Double.MAX_VALUE;

    public EventManager events;
    private List<Edge> adjacenciesList;

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
    public String toString(){
        return this.name;
    }

    @Override
    public int compareTo(Stellage node){
        return Double.compare(this.distance, node.getDistance());
    }
}
