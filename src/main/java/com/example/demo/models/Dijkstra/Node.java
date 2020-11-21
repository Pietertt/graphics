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

    public Node(double x, double y, double z, String name){
        this.name = name;
        this.adjacenciesList = new ArrayList<>();

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return this.x;
    }

    public double getZ(){
        return this.z;
    }

    public double getY(){
        return this.y;
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

    public void setPredecessor(Node predecessor){
        this.predecessor = predecessor;
    }

    public Node getPredecessor(){
        return this.predecessor;
    }

    public double getDistance(){
        return this.distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int compareTo(Node node){
        return Double.compare(this.distance, node.getDistance());
    }




    // private String name;
    // private List<Edge> adjacenciesList;
    // private boolean visited;
	// private Vertex predecessor;
    // private double distance = Double.MAX_VALUE;
    
    // public Vertex(String name) {
	// 	this.name = name;
	// 	this.adjacenciesList = new ArrayList<>();
	// }
 
	// public void addNeighbour(Edge edge) {
	// 	this.adjacenciesList.add(edge);
	// }
 
	// public String getName() {
	// 	return name;
	// }
 
	// public void setName(String name) {
	// 	this.name = name;
	// }
 
	// public List<Edge> getAdjacenciesList() {
	// 	return adjacenciesList;
	// }
 
	// public void setAdjacenciesList(List<Edge> adjacenciesList) {
	// 	this.adjacenciesList = adjacenciesList;
	// }
 
	// public boolean isVisited() {
	// 	return visited;
	// }
 
	// public void setVisited(boolean visited) {
	// 	this.visited = visited;
	// }
 
	// public Vertex getPredecessor() {
	// 	return predecessor;
	// }
 
	// public void setPredecessor(Vertex predecessor) {
	// 	this.predecessor = predecessor;
	// }
 
	// public double getDistance() {
	// 	return distance;
	// }
 
	// public void setDistance(double distance) {
	// 	this.distance = distance;
	// }
 
	// @Override
	// public String toString() {
	// 	return this.name;
	// }
 
	// @Override
	// public int compareTo(Vertex otherVertex) {
	// 	return Double.compare(this.distance, otherVertex.getDistance());
	// }
}
