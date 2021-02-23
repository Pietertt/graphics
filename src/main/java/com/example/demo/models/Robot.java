package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.example.demo.models.Observer.EventManager;
import com.example.demo.models.Observer.EventListener;

import com.example.demo.models.Order.Request;
import com.example.demo.models.Order.Deliver;
import com.example.demo.models.Order.Order;

import com.example.demo.models.Dijkstra.Node;
import com.example.demo.models.Dijkstra.Edge;
import com.example.demo.models.Dijkstra.Dijkstra;

import com.example.demo.strategies.Strategy;
import com.example.demo.strategies.DeliverStrategy;

import jdk.internal.event.Event;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public class Robot extends Object3D implements Updatable, EventListener {

    private boolean filling = false;

    private double orderedX = 0;
    private double orderedY = 0;
    private double orderedZ = 0;

    private boolean returning = false;

    private Node graph;
    private Dijkstra dijkstra;
    private ArrayList<Node> nodes;

    private Strategy strategy;

    public ArrayList<Order> orders;

    public EventManager events;

    public Robot(double x, double y, double z) {
        super(x, y, z);
        this.orders = new ArrayList<Order>();

        this.setSpeed(0.1);
        this.setUUID(UUID.randomUUID());
        this.events = new EventManager("loaded", "unloaded");

        this.graph = new Node(5, 2, 5, "S");
        this.dijkstra = new Dijkstra();
        this.nodes = this.dijkstra.spawnNodes();
        this.graph.addDestination(new Edge(3, this.graph, this.nodes.get((3))));
        this.setStrategy(new DeliverStrategy());
    }

    /*
     * Deze update methode wordt door de World aangeroepen wanneer de
     * World zelf geupdate wordt. Dit betekent dat elk object, ook deze
     * robot, in de 3D wereld steeds een beetje tijd krijgt om een update
     * uit te voeren. In de updatemethode hieronder schrijf je dus de code
     * die de robot steeds uitvoert (bijvoorbeeld positieveranderingen). Wanneer
     * de methode true teruggeeft (zoals in het voorbeeld), betekent dit dat
     * er inderdaad iets veranderd is en dat deze nieuwe informatie naar de views
     * moet worden gestuurd. Wordt false teruggegeven, dan betekent dit dat er niks
     * is veranderd, en de informatie hoeft dus niet naar de views te worden gestuurd.
     * (Omdat de informatie niet veranderd is, is deze dus ook nog steeds hetzelfde als
     * in de view)
     */
    @Override
    public boolean update() {
        this.move();
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
        return Robot.class.getSimpleName().toLowerCase();
    }

    public int getDeliverables(){
        int amount = 0;
        for(Order order : this.orders){
            if(order instanceof Deliver){
                amount = amount + 1;
            }
        }
        return amount;
    }

    public int getRequests(){
        int amount = 0;
        for(Order order : this.orders){
            if(order instanceof Request){
                amount = amount + 1;
            }
        }
        return amount;
    }

    public void fillTruck(){
        this.filling = true;
    }

    private void takeNextStellage(){
        this.filling = false;
    }

    public boolean isFillingTruck(){
        return this.filling;
    }

    public Order getFirstOrder(){
        return this.orders.get(0);
    }

    public int move(){
        if(this.getDeliverables() > 0){ // heenweg
            
            if(this.returning == false){ // heenweg
                this.setStrategy(new DeliverStrategy());
                Point p = this.strategy.execute(this.getFirstOrder(), new Point(this.getX(), this.getY(), this.getZ()));
                this.setX(p.getX());
                this.setZ(p.getZ());
            } else {
                this.setStrategy(new RequestStrategy());
                Point p = this.strategy.execute(this.getFirstOrder(), new Point(this.getX(), this.getY(), this.getZ()));
                this.setX(p.getX());
                this.setZ(p.getZ());
            }            
        }

        if(this.getRequests() > 0){
            if(this.getDeliverables() == 0){
                if(this.returning == false){
                    this.setStrategy(new DeliverStrategy());
                    Point p = this.strategy.execute(this.getFirstOrder(), new Point(this.getX(), this.getY(), this.getZ()));
                    this.setX(p.getX());
                    this.setZ(p.getZ());
                } else {
                    this.setStrategy(new RequestStrategy());
                    Point p = this.strategy.execute(this.getFirstOrder(), new Point(this.getX(), this.getY(), this.getZ()));
                    this.setX(p.getX());
                    this.setZ(p.getZ());
                }
            }
        } 
        
        return 0;
        
    }

    public void removeOrder(int index){
        this.orders.remove(index);
    }

    public void removeOrder(Order order){
        this.orders.remove(order);
    }

    public void addOrder(Order order){
        this.orders.add(order);

        this.dijkstra.computerShortestPath(this.graph);

        /*
            Hier declareer ik alvast een nieuwe node die later gevuld wordt. Het is nodig om deze alvast
            te declareren omdat dit object moet bestaan als ik deze later wil printen naar de console.
        */
        Node no = new Node();

        for(Node node : this.nodes){
            if(node.getName().equals(order.stellage.getName())){
                no = node;
                List<Node> nodes = this.dijkstra.getShortestPathTo(node);
                for(Node n : nodes){
                    order.addNode(n);
                }
            }
        }

        System.out.printf("[ROBOT] Moving stellage %s via path %s\n", order.stellage.getName(), this.dijkstra.getShortestPathTo(no));
    }

    public void update(String event, String message){
        // if(event == "full"){
        //     System.out.println("[ROBOT] Waiting for new truck");
        //     this.setStrategy(new UnloadWithStellageStrategy());
        // } 
    }

    private void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}