package com.example.demo.models.Dijkstra;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Edge {
    public double weight;
    public Node startNode;
    public Node endNode;

    public Edge(double weight, Node start, Node end){
        this.weight = weight;
        this.startNode = start;
        this.endNode = end;
    }
}