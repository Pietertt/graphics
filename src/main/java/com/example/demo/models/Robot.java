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
    private boolean stellage = false;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    
    public Robot(int x, int z) {

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

        this.x = x;
        this.z = z;
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
        Random random = new Random();
        
        double nextX = (random.nextDouble() * 2) - 1;
        double nextZ = (random.nextDouble() * 2) - 1;

        double x = this.x;
        double z = this.z;

        if((x + nextX <= 30.0) && (x + nextX >= 0.0)){
            this.x += nextX;
        }

        if((z + nextZ <= 30.0) && (z + nextZ >= 0.0)){
            this.z += nextZ;
        }

        //System.out.printf("%s - %s\n", this.x, this.z);
        
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

    @Override
    public ArrayList<Integer> getInventory(){
        ArrayList<Integer> empty = new ArrayList<Integer>();
        return empty;
    } 
}