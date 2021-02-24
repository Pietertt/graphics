package com.example.demo.models.Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Node implements Comparable<Node> {
    private String name;
    private List<Edge> adjacenciesList;
    private boolean visited;
    private Node predecessor;
    private double distance = Double.MAX_VALUE;

    private double x;
    private double y;
    private double z;

    /**
     * Constructor
     * 
     * @param x
     * @param y
     * @param z
     * @param name
     */
    public Node(double x, double y, double z, String name){
        this.name = name;
        this.adjacenciesList = new ArrayList<>();

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructor
     */
    public Node(){
        
    }

    /**
     * Get X
     * 
     * @return double
     */
    public double getX(){
        return this.x;
    }

    /**
     * Get Z
     * 
     * @return double
     */
    public double getZ(){
        return this.z;
    }

    /**
     * Get Y
     * 
     * @return double
     */
    public double getY(){
        return this.y;
    }

    /**
     * Add a destination
     * 
     * @param edge
     */
    public void addDestination(Edge edge){
        this.adjacenciesList.add(edge);
    }

    /**
     * Gets the name
     * 
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the adjacencies list
     * 
     * @return List<Edge>
     */
    public List<Edge> getAdjacenciesList(){
        return this.adjacenciesList;
    }

    /**
     * Gets visited
     * 
     * @return boolean
     */
    public boolean isVisited(){
        return this.visited;
    }

    /**
     * Sets visited
     * 
     * @param visited
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    /**
     * Set the predecessor
     * 
     * @param predecessor
     */
    public void setPredecessor(Node predecessor){
        this.predecessor = predecessor;
    }

    /**
     * Gets the predecessor
     * 
     * @return Node
     */
    public Node getPredecessor(){
        return this.predecessor;
    }

    /**
     * Gets the distance
     * 
     * @return double
     */
    public double getDistance(){
        return this.distance;
    }

    /**
     * Sets the distance
     * 
     * @param distance
     */
    public void setDistance(double distance){
        this.distance = distance;
    }

    /**
     * Returns the name of a node
     * 
     * @return string
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Compares two nodes
     * 
     * @return int
     */
    @Override
    public int compareTo(Node node){
        return Double.compare(this.distance, node.getDistance());
    }
}
