package com.example.demo.models.Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Dijkstra {

    /**
     * Compute the shortest path by comparing various distances
     * 
     * @param source
     */
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

    /**
     * Returns the shortest path
     * 
     * @param n
     * @return List<Node>
     */
    public List<Node> getShortestPathTo(Node n){
        List<Node> path = new ArrayList<>();

        for(Node node = n; node != null; node = node.getPredecessor()){
            path.add(node);
        }

        Collections.reverse(path);

        return path;
    }

    /**
     * Creates the network of nodes a robot can use
     * 
     * @return Arraylist<Node>
     */
    public ArrayList<Node> spawnNodes() {
        ArrayList nodes = new ArrayList();

        Node A = new Node(26, 2, 9, "A");
        Node B = new Node(22, 2, 9, "B");
        Node C = new Node(10, 2, 9, "C");
        Node D = new Node(5, 2, 9, "D");
        Node E = new Node(26, 2, 17, "E");
        Node F = new Node(21, 2, 24, "F");
        Node G = new Node(10, 2, 24, "G");
        Node H = new Node(21, 2, 31, "H");
        Node I = new Node(10, 2, 31, "I");
        Node J = new Node(26, 2, 38, "J");
        Node K = new Node(21, 2, 38, "K");
        Node L = new Node(10, 2, 38, "L");
        Node M = new Node(5, 2, 38, "M");

        A.addDestination(new Edge(3, A, E));
        B.addDestination(new Edge(3, B, A));
        C.addDestination(new Edge(6, C, B));
        C.addDestination(new Edge(6, C, G));
        D.addDestination(new Edge(3, D, C));
        D.addDestination(new Edge(21, D, M));
        E.addDestination(new Edge(9, E, J));
        F.addDestination(new Edge(6, F, G));
        G.addDestination(new Edge(3, G, I));
        H.addDestination(new Edge(3, H, F));
        I.addDestination(new Edge(3, I, L));
        J.addDestination(new Edge(3, J, K));
        K.addDestination(new Edge(6, K, L));
        K.addDestination(new Edge(3, K, H));
        L.addDestination(new Edge(3, L, M));

        nodes.add(A);
        nodes.add(B);
        nodes.add(C);
        nodes.add(D);
        nodes.add(E);
        nodes.add(F);
        nodes.add(G);
        nodes.add(H);
        nodes.add(I);
        nodes.add(J);
        nodes.add(K);
        nodes.add(L);
        nodes.add(M);

        return nodes;
    }

}
