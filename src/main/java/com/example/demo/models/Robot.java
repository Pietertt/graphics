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

import jdk.internal.event.Event;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public class Robot extends Object3D implements Updatable, EventListener {
    private UUID uuid;

    private double x;
    private double y = 0;
    private double z;
    private boolean filling = false;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private double orderedX = 0;
    private double orderedY = 0;
    private double orderedZ = 0;

    private boolean returning = false;

    private Node graph;
    private Dijkstra dijkstra;
    private ArrayList<Node> nodes;


    public ArrayList<Order> orders;
    //public ArrayList<Stellage> wishing;

    public EventManager events;

    public Robot(double x, double z) {

        

        this.orders = new ArrayList<Order>();
        //this.wishing = new ArrayList<Stellage>();

        this.x = x;
        this.z = z;
        this.speed = 0.1;
        this.uuid = UUID.randomUUID();
        this.events = new EventManager("loaded", "unloaded");

        this.graph = new Node(5, 2, 5, "S");
        this.nodes = new ArrayList<Node>();

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

        this.nodes.add(A);
        this.nodes.add(B);
        this.nodes.add(C);
        this.nodes.add(D);
        this.nodes.add(E);
        this.nodes.add(F);
        this.nodes.add(G);
        this.nodes.add(H);
        this.nodes.add(I);
        this.nodes.add(J);
        this.nodes.add(K);
        this.nodes.add(L);
        this.nodes.add(M);

        this.graph.addDestination(new Edge(3, this.graph, D));

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


        this.dijkstra = new Dijkstra();
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
    public String getUUID() {
        return this.uuid.toString();
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

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }

    public double getSpeed(){
        return this.speed;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setZ(double z){
        this.z = z;
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
        if(this.getDeliverables() > 0){
            
            if(this.returning == false){
                if(((this.getFirstOrder().getX() - 0.01 <= this.getX()) && (this.getX() <= this.getFirstOrder().getX() + 0.01))){
                    if(((this.getFirstOrder().getZ() - 0.01 <= this.getZ()) && (this.getZ() <= this.getFirstOrder().getZ() + 0.01))){
                        //robot.setStrategy(new UnloadWithoutStellageStrategy());
                        this.returning = true;
                        System.out.println("Delivered stellage to place");
                    } else {
                        if(this.getFirstOrder().getZ() > this.getZ()){
                            this.setZ(this.getZ() + this.getSpeed());
                            this.getFirstOrder().stellage.setZ(this.getZ());
                        } else {
                            this.setZ(this.getZ() - this.getSpeed());
                            this.getFirstOrder().stellage.setZ(this.getZ());
                        }
                    }
                } else {
                    this.getFirstOrder().stellage.status = true;
                    if(this.getFirstOrder().getX() > this.getX()){
                        this.setX(this.getX() + this.getSpeed());
                        this.getFirstOrder().stellage.setX(this.getX());
                        this.getFirstOrder().stellage.setZ(this.getZ());
                    } else {
                        this.setX(this.getX() - this.getSpeed());
                        this.getFirstOrder().stellage.setX(this.getX());
                        this.getFirstOrder().stellage.setZ(this.getZ());
                    }
                }
            } else {
                if(((15 - 0.01 <= this.getX()) && (this.getX() <= 15 + 0.01))){
                    if((0 - 0.01 <= this.getZ()) && (this.getZ() <= 0 + 0.01)){
                        this.events.notify("unloaded", this.getFirstOrder().stellage.getUUID());
                        this.getFirstOrder().stellage.events.notify("unloaded", this.getFirstOrder().stellage.getUUID());
                        this.getFirstOrder().stellage.status = true;
                        this.removeOrder(this.getFirstOrder());
                        this.returning = false;
                        System.out.println("Returned to truck");
                        return 1;
                    } else {
                        if(this.getZ() > 0){
                            this.setZ(this.getZ() - this.getSpeed());
                        } else {
                            this.setZ(this.getZ() + this.getSpeed());
                        }
                    }
                } else {
                    if(this.getX() > 15){
                        this.setX(this.getX() - this.getSpeed());
                    } else {
                        this.setX(this.getX() + this.getSpeed());
                    }
                }
            }            
        }

        if(this.getRequests() > 0){
            if(this.getDeliverables() == 0){
                if(this.returning == false){

                    if(this.getFirstOrder().containsNodes()){
                        if(((this.getFirstOrder().getFirstNode().getX() - 0.01 <= this.getX()) && (this.getX() <= this.getFirstOrder().getFirstNode().getX() + 0.01))){
                            if(((this.getFirstOrder().getFirstNode().getZ() - 0.01 <= this.getZ()) && (this.getZ() <= this.getFirstOrder().getFirstNode().getZ() + 0.01))){
                                System.out.printf("Arrived at node %s\n", this.getFirstOrder().getFirstNode().getName());
                                this.getFirstOrder().removeVisitedNode();

                                if(!this.getFirstOrder().containsNodes()){
                                    this.returning = true;
                                }
                            } else {
                                if(this.getFirstOrder().getFirstNode().getZ() > this.getZ()){
                                    this.setZ(this.getZ() + this.getSpeed());
                                } else {
                                    this.setZ(this.getZ() - this.getSpeed());
                                }
                            }
                        } else {
                            this.getFirstOrder().stellage.status = true;
                            if(this.getFirstOrder().getFirstNode().getX() > this.getX()){
                                this.setX(this.getX() + this.getSpeed());
                            } else {
                                this.setX(this.getX() - this.getSpeed());
                            }
                        }
                        // System.out.printf("%s %s %s %s\n", this.getFirstOrder().getFirstNode().getName(), this.getFirstOrder().getFirstNode().getX(), this.getFirstOrder().getFirstNode().getY(), this.getFirstOrder().getFirstNode().getZ());
                    } 

                } else {
                    if(this.getFirstOrder().containsVisitedNodes()){
                        if(((this.getFirstOrder().getLastNode().getX() - 0.01 <= this.getX()) && (this.getX() <= this.getFirstOrder().getLastNode().getX() + 0.01))){
                            if(((this.getFirstOrder().getLastNode().getZ() - 0.01 <= this.getZ()) && (this.getZ() <= this.getFirstOrder().getLastNode().getZ() + 0.01))){
                                System.out.printf("Arrived at node %s\n", this.getFirstOrder().getLastNode().getName());
                                this.getFirstOrder().removeUnvisitedNove();

                                if(!this.getFirstOrder().containsVisitedNodes()){
                                    System.out.println("Back at the truck");
                                }
                            } else {
                                if(this.getZ() > this.getFirstOrder().getLastNode().getZ()){
                                    this.setZ(this.getZ() - this.getSpeed());
                                    this.getFirstOrder().stellage.setX(this.getX());
                                    this.getFirstOrder().stellage.setZ(this.getZ());
                                } else {
                                    this.setZ(this.getZ() + this.getSpeed());
                                    this.getFirstOrder().stellage.setX(this.getX());
                                    this.getFirstOrder().stellage.setZ(this.getZ());
                                }
                            }
                        } else {
                            if(this.getX() > this.getFirstOrder().getLastNode().getX()){
                                this.setX(this.getX() - this.getSpeed());
                                this.getFirstOrder().stellage.setX(this.getX());
                                this.getFirstOrder().stellage.setZ(this.getZ());
                            } else {
                                this.setX(this.getX() + this.getSpeed());
                                this.getFirstOrder().stellage.setX(this.getX());
                                this.getFirstOrder().stellage.setZ(this.getZ());
                            }
                        }
                    }

                    // if(((15 - 0.01 <= this.getX()) && (this.getX() <= 15 + 0.01))){
                    //     if((0 - 0.01 <= this.getZ()) && (this.getZ() <= 0 + 0.01)){
                    //         this.events.notify("loaded", this.getFirstOrder().stellage.getUUID());
                    //         this.getFirstOrder().stellage.events.notify("loaded", this.getFirstOrder().stellage.getUUID());
                    //         this.getFirstOrder().stellage.status = false;
                    //         this.removeOrder(this.getFirstOrder());
                    //         this.returning = false;
                    //         System.out.println("Delivered stellage to truck");
                    //         // if(robot.gotAnyWishOrders()){
                    //         //     robot.setStrategy(new UnloadWithStellageStrategy());
                    //         // } else {
                    //         //     if(robot.gotAnyOrders()){
                    //         //         robot.setStrategy(new LoadWithoutStellageStrategy());
                    //         //     }
                    //         // }
                    //         //System.out.println("[ROBOT] Returning empty to truck");
                    //     } else {
                    //         if(this.getZ() > 0){
                    //             this.setZ(this.getZ() - this.getSpeed());
                    //             this.getFirstOrder().stellage.setX(this.getX());
                    //             this.getFirstOrder().stellage.setZ(this.getZ());
                    //         } else {
                    //             this.setZ(this.getZ() + this.getSpeed());
                    //             this.getFirstOrder().stellage.setX(this.getX());
                    //             this.getFirstOrder().stellage.setZ(this.getZ());
                    //         }
                    //     }
                    // } else {
                    //     if(this.getX() > 15){
                    //         this.setX(this.getX() - this.getSpeed());
                    //         this.getFirstOrder().stellage.setX(this.getX());
                    //         this.getFirstOrder().stellage.setZ(this.getZ());
                    //     } else {
                    //         this.setX(this.getX() + this.getSpeed());
                    //         this.getFirstOrder().stellage.setX(this.getX());
                    //         this.getFirstOrder().stellage.setZ(this.getZ());
                    //     }
                    // }
                }
            }
        } 
        
        return 0;
        
    }

    public void remove(){
        this.status = false;
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

        for(Node node : this.nodes){
            if(node.getName().equals(order.stellage.getName())){
                List<Node> nodes = this.dijkstra.getShortestPathTo(node);
                for(Node n : nodes){
                    order.addNode(n);
                }
            }
        }

        System.out.printf("[ROBOT] Moving stellage %s to coordinates (%s, %s, %s)\n", order.stellage.getName(), order.getX(), order.getY(), order.getZ());
    }

    public void update(String event, String message){
        // if(event == "full"){
        //     System.out.println("[ROBOT] Waiting for new truck");
        //     this.setStrategy(new UnloadWithStellageStrategy());
        // } 
    }
}