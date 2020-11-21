package com.example.demo.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import com.example.demo.models.Observer.EventManager;
import com.example.demo.models.Observer.EventListener;

import com.example.demo.models.Order.Request;
import com.example.demo.models.Order.Deliver;
import com.example.demo.models.Order.Order;

import com.example.demo.models.Dijkstra.Node;
import com.example.demo.models.Dijkstra.Graph;
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

        Node S = new Node("S");

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node G = new Node("G");
        Node H = new Node("H");
        Node I = new Node("I");
        Node J = new Node("J");
        Node K = new Node("K");
        Node L = new Node("L");
        Node M = new Node("M");

        S.addDestination(new Edge(3, S, A));

        A.addDestination(new Edge(3, A, B));

        B.addDestination(new Edge(3, B, C));
        B.addDestination(new Edge(6, B, G));

        C.addDestination(new Edge(3, C, D));

        D.addDestination(new Edge(3, D, E));

        E.addDestination(new Edge(9, E, J));

        G.addDestination(new Edge(3, G, I));

        I.addDestination(new Edge(3, I, H));

        H.addDestination(new Edge(3, H, F));
        H.addDestination(new Edge(3, H, K));

        J.addDestination(new Edge(3, J, K));

        K.addDestination(new Edge(3, K, L));

        L.addDestination(new Edge(3, L, M));

        Dijkstra dijkstra = new Dijkstra();

        dijkstra.c(S);

        System.out.println(dijkstra.getShortestPathTo(J));

        // Graph graph = new Graph();

        // graph.addNode(A);
        // graph.addNode(B);
        // graph.addNode(C);
        // graph.addNode(D);
        // graph.addNode(E);
        // graph.addNode(F);
        // graph.addNode(G);
        // graph.addNode(H);
        // graph.addNode(I);
        // graph.addNode(J);
        // graph.addNode(K);
        // graph.addNode(L);
        // graph.addNode(M);

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
































    // /*
    //     Check whenever the robot has reached its destination. The destination can be either 
    //     his spawn point or a stellage.
    // */
    // private boolean robotHasArrivedX(){
    //     if(!this.isFillingTruck()){
    //         if(!this.hasNoOrders()){
    //             Stellage stellage = this.orders.get(0);
    //             if((stellage.getX() - 0.01 <= this.x) && (this.x <= stellage.getX() + 0.01)){
    //                 if((stellage.getZ() - 0.01 <= this.z) && (this.z <= stellage.getZ() + 0.01)){
    //                     this.fillTruck();
    //                     //this.events.notify("load", "test"); 
    //                 }
    //                 return true;
    //             }
    //             return false;
    //         }
    //         return false;
    //     } else {
    //         if((15 - 0.01 <= this.x) && (this.x <= 15 + 0.01)){
    //             if((0 - 0.01 <= this.z) && (this.z <= 0 + 0.01)){
    //                 if(!this.hasNoOrders()){
    //                     this.events.notify("deliver", this.orders.get(0).getUUID());
    //                     this.orders.get(0).events.notify("loaded", this.orders.get(0).getUUID());
    //                     this.orders.remove(0);
    //                     this.takeNextStellage();
    //                 } else {
    //                     //this.events.notify("deliver", this.getUUID());
    //                 }
    //             }
    //             return true;
    //         }
    //         return false;
    //     }
    // }

    // private boolean robotHasArrivedZ(){
        
    // }

    // private void moveX(){
    //     if(!this.isFillingTruck()){
    //         if(!this.hasNoOrders()){
    //             Stellage stellage = this.orders.get(0);
    //             if(stellage.getX() > this.x){
    //                 this.x += this.speed;
    //             } else {
    //                 this.x -= this.speed;
    //             }
    //         }
    //     } else {
    //         Stellage stellage = this.orders.get(0);
    //         if(this.x > 15){
    //             this.x -= this.speed;
    //             this.orders.get(0).setX(this.x);
    //             this.orders.get(0).setZ(this.z); 
    //         } else {
    //             this.x += this.speed;
    //             this.orders.get(0).setX(this.x);
    //             this.orders.get(0).setZ(this.z); 
    //         }
    //     }
    // }

    // private void moveZ(){
    //     if(!this.isFillingTruck()){
    //         if(!this.hasNoOrders()){
    //             Stellage stellage = this.orders.get(0);
    //             if(stellage.getZ() > this.z){
    //                 this.z += this.speed;
    //             } else {
    //                 this.z -= this.speed;
    //             }
    //         }
    //     } else {
    //         if(this.z > 0){
    //             this.z -= this.speed;
    //             this.orders.get(0).setX(this.x);
    //             this.orders.get(0).setZ(this.z);
    //         } else {
    //             this.z += this.speed;
    //             this.orders.get(0).setX(this.x);
    //             this.orders.get(0).setZ(this.z);
    //         }
    //     }
    // }

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
                    if(((this.getFirstOrder().getX() - 0.01 <= this.getX()) && (this.getX() <= this.getFirstOrder().getX() + 0.01))){
                        if(((this.getFirstOrder().getZ() - 0.01 <= this.getZ()) && (this.getZ() <= this.getFirstOrder().getZ() + 0.01))){
                            this.returning = true;
                            System.out.println("Arrived at desired stellage");
                        } else {
                            if(this.getFirstOrder().getZ() > this.getZ()){
                                this.setZ(this.getZ() + this.getSpeed());
                                // this.getFirstOrder().stellage.setX(this.getX());
                                // this.getFirstOrder().stellage.setZ(this.getZ());
                            } else {
                                this.setZ(this.getZ() - this.getSpeed());
                                // this.getFirstOrder().stellage.setX(this.getX());
                                // this.getFirstOrder().stellage.setZ(this.getZ());
                            }
                        }
                    } else {
                        if(this.getFirstOrder().getX() > this.getX()){
                            this.setX(this.getX() + this.getSpeed());
                            // this.getFirstOrder().stellage.setX(this.getX());
                            // this.getFirstOrder().stellage.setZ(this.getZ());
                        } else {
                            this.setX(this.getX() - this.getSpeed());
                            // this.getFirstOrder().stellage.setX(this.getX());
                            // this.getFirstOrder().stellage.setZ(this.getZ());
                        }
                    } 
                } else {
                    if(((15 - 0.01 <= this.getX()) && (this.getX() <= 15 + 0.01))){
                        if((0 - 0.01 <= this.getZ()) && (this.getZ() <= 0 + 0.01)){
                            this.events.notify("loaded", this.getFirstOrder().stellage.getUUID());
                            this.getFirstOrder().stellage.events.notify("loaded", this.getFirstOrder().stellage.getUUID());
                            this.getFirstOrder().stellage.status = false;
                            this.removeOrder(this.getFirstOrder());
                            this.returning = false;
                            System.out.println("Delivered stellage to truck");
                            // if(robot.gotAnyWishOrders()){
                            //     robot.setStrategy(new UnloadWithStellageStrategy());
                            // } else {
                            //     if(robot.gotAnyOrders()){
                            //         robot.setStrategy(new LoadWithoutStellageStrategy());
                            //     }
                            // }
                            //System.out.println("[ROBOT] Returning empty to truck");
                        } else {
                            if(this.getZ() > 0){
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
                        if(this.getX() > 15){
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
        System.out.printf("[ROBOT] Moving stellage to coordinates (%s, %s, %s)\n", order.getX(), order.getY(), order.getZ());
    }

    public void update(String event, String message){
        // if(event == "full"){
        //     System.out.println("[ROBOT] Waiting for new truck");
        //     this.setStrategy(new UnloadWithStellageStrategy());
        // } 
    }

    // public void addWishing(Stellage stellage){
    //     this.wishing.add(stellage);
    //     System.out.printf("[ROBOT] Moving to stellage %s on coordinates (%s %s %s)\n", stellage.getUUID(), stellage.getRotationX(), stellage.getY(), stellage.getZ());
    // }
}