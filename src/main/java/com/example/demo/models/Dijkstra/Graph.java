package com.example.demo.models.Dijkstra;

import java.util.ArrayList;

import com.example.demo.models.Dijkstra.Node;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<Node>();

    public Graph(){
        
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }
}