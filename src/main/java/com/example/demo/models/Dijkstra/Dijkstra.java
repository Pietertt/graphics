package com.example.demo.models.Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Dijkstra {

    public void computerShortestPath(Node source){
        source.setDistance(0);

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(source);
        source.setVisited(true);

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(Edge edge : node.getAdjacenciesList()){
                Node v = edge.endNode;
                if(!v.isVisited()){
                    double newDistance = node.getDistance() + edge.weight;
                    if(newDistance < v.getDistance()){
                        queue.remove(v);
                        v.setDistance(newDistance);
                        v.setPredecessor(node);
                        queue.add(v);
                    }
                }
            }
            node.setVisited(true);
        }
    }

    public List<Node> getShortestPathTo(Node n){
        List<Node> path = new ArrayList<>();

        for(Node node = n; node != null; node = node.getPredecessor()){
            path.add(node);
        }

        Collections.reverse(path);

        return path;
    }

}
