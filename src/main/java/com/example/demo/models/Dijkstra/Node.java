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

    public Node(String name){
        this.name = name;
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
