package com.example.demo.models.Order;

import java.util.ArrayList;

import com.example.demo.models.Stellage;
import com.example.demo.models.Dijkstra.Node;

public abstract class Order {
    protected double x;
    protected double y;
    protected double z; 
    
    public Stellage stellage;
    private ArrayList<Node> nodes;
    private ArrayList<Node> visitedNodes;

    public Order(double x, double y, double z, Stellage stellage){
        this.x = x;
        this.y = y;
        this.z = z;
        this.stellage = stellage;
        this.nodes = new ArrayList<Node>();
        this.visitedNodes = new ArrayList<Node>();
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    public void getNodes(){
        for(Node node : this.nodes){
            System.out.printf("%s %s %s %s", node.getX(), node.getY(), node.getZ(), node.getName());
        }
        System.out.println();
    }

    public Node getFirstNode(){
        return this.nodes.get(0);
    }

    public Node getLastNode(){
        return this.visitedNodes.get(this.visitedNodes.size() - 1);
    }

    public boolean containsNodes(){
        if(this.nodes.size() > 0){
            return true;
        }
        return false;
    }

    public boolean containsVisitedNodes(){
        if(this.visitedNodes.size() > 0){
            return true;
        }
        return false;
    }

    public void removeVisitedNode(){
        Node node = this.nodes.remove(0);
        this.visitedNodes.add(node);
    }

    public void removeUnvisitedNove(){
        this.visitedNodes.remove(this.visitedNodes.size() - 1);
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getZ(){
        return this.z;
    }
}