package com.example.demo.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Robot extends Object3D implements Updatable {
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

    private ArrayList<Stellage> orders;

    public Robot(double x, double z) {

        Vertex vertexA = new Vertex("A");
		Vertex vertexB = new Vertex("B");
		Vertex vertexC = new Vertex("C");
		Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        Vertex vertexF = new Vertex("F");
        Vertex vertexG = new Vertex("G");
        Vertex vertexH = new Vertex("H");
        Vertex vertexI = new Vertex("I");
        Vertex vertexJ = new Vertex("J");
        Vertex vertexK = new Vertex("K");
        Vertex vertexL = new Vertex("L");
        Vertex vertexM = new Vertex("M");
        Vertex vertexN = new Vertex("N");
		
		vertexA.addNeighbour(new Edge(1,vertexA,vertexD));
        vertexB.addNeighbour(new Edge(1,vertexB,vertexC));
        vertexC.addNeighbour(new Edge(1,vertexC,vertexD));
        vertexD.addNeighbour(new Edge(1,vertexD,vertexE));
        vertexD.addNeighbour(new Edge(1,vertexD,vertexI));
        vertexE.addNeighbour(new Edge(1,vertexE,vertexF));
        vertexG.addNeighbour(new Edge(1,vertexG,vertexH));
        vertexH.addNeighbour(new Edge(1,vertexH,vertexI));
        vertexI.addNeighbour(new Edge(1,vertexI,vertexJ));
        vertexI.addNeighbour(new Edge(1,vertexI,vertexM));
        vertexJ.addNeighbour(new Edge(1,vertexJ,vertexK));
        vertexL.addNeighbour(new Edge(1,vertexL,vertexM));
        vertexM.addNeighbour(new Edge(1,vertexM,vertexN));

        this.orders = new ArrayList<Stellage>();

        this.x = x;
        this.z = z;
        this.speed = 0.1;
        this.uuid = UUID.randomUUID();
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

    private boolean robotHasArrivedX(){
        if(!this.isFillingTruck()){
            if(!this.hasNoOrders()){
                Stellage stellage = this.orders.get(0);
                if((stellage.getX() - 0.01 <= this.x) && (this.x <= stellage.getX() + 0.01)){
                    if((stellage.getZ() - 0.01 <= this.z) && (this.z <= stellage.getZ() + 0.01)){
                        this.fillTruck();
                        this.orders.remove(0);
                    }
                    return true;
                }
                return false;
            }
            return false;
        } else {
            if((15 - 0.01 <= this.x) && (this.x <= 15 + 0.01)){
                if((0 - 0.01 <= this.z) && (this.z <= 0 + 0.01)){
                    this.takeNextStellage();
                }
                return true;
            }
            return false;
        }
    }

    private boolean robotHasArrivedZ(){
        if(!this.isFillingTruck()){
            if(!this.hasNoOrders()){
                Stellage stellage = this.orders.get(0);
                if((stellage.getZ() - 0.01 <= this.z) && (this.z <= stellage.getZ() + 0.01)){
                    if((stellage.getX() - 0.01 <= this.x) && (this.x <= stellage.getX() + 0.01)){
                        this.fillTruck();
                        this.orders.remove(0);
                    }
                    return true;
                }
                return false;
            }
            return false;
        } else {
            if((0 - 0.01 <= this.z) && (this.z <= 0 + 0.01)){
                if((15 - 0.01 <= this.x) && (this.x <= 15 + 0.01)){
                    this.takeNextStellage();
                }
                return true;
            }
            return false;
        }
    }

    private void moveX(){
        if(!this.isFillingTruck()){
            if(!this.hasNoOrders()){
                Stellage stellage = this.orders.get(0);
                if(stellage.getX() > this.x){
                    this.x += this.speed;
                } else {
                    this.x -= this.speed;
                }
            }
        } else {
            if(this.x > 15){
                this.x -= this.speed;
            } else {
                this.x += this.speed;
            }
        }
    }

    private void moveZ(){
        if(!this.isFillingTruck()){
            if(!this.hasNoOrders()){
                Stellage stellage = this.orders.get(0);
                if(stellage.getZ() > this.z){
                    this.z += this.speed;
                } else {
                    this.z -= this.speed;
                }
            }
        } else {
            if(this.z > 0){
                this.z -= this.speed;
            } else {
                this.z += this.speed;
            }
        }
    }

    private boolean hasNoOrders(){
        if(this.orders.size() == 0){
            return true;
        }
        return false;
    }

    private void fillTruck(){
        this.filling = true;
    }

    private void takeNextStellage(){
        this.filling = false;
    }

    private boolean isFillingTruck(){
        return this.filling;
    }

    public void move(){
        if(!this.robotHasArrivedX()){
            this.moveX();
        }

        if(!this.robotHasArrivedZ()){
            this.moveZ();
        }
    }

    public void addOrder(Stellage stellage){
        this.orders.add(stellage);
        System.out.printf("[ROBOT] Moving to stellage %s on coordinates (%s %s %s)\n", stellage.getUUID(), stellage.getRotationX(), stellage.getY(), stellage.getZ());

    }
}