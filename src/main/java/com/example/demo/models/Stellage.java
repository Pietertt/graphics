package com.example.demo.models;

import java.util.UUID;
import java.util.ArrayList;

public class Stellage extends Object3D implements Updatable {

    private UUID uuid;

    private double x;
    private double y = 0;
    private double z;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Stellage(int x, int z) {
        this.x = x;
        this.z = z;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public boolean update() {
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
        return Stellage.class.getSimpleName().toLowerCase();
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
