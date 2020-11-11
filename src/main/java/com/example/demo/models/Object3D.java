package com.example.demo.models;

import java.util.ArrayList;

/*
 * Deze interface beschrijft wat een 3D model is. Het is een interface omdat alleen de
 * methoden worden gegeven die een object moet implementeren om een 3D model te kunnen zijn.
 * Merk op dat hier alleen getters in staan, en geen setters. Dit heeft te maken met de
 * uitvoering van het proxy pattern, en het feit dat in deze software eigenlijk bijna geen
 * setters nodig zijn.
 */

public abstract class Object3D {
    public Boolean status = true;
    public double speed;
    public ArrayList<Integer> inventory;
    
    public abstract String getUUID();
    public abstract String getType();

    public abstract double getX();
    public abstract double getY();
    public abstract double getZ();
    
    public abstract double getRotationX();
    public abstract double getRotationY();
    public abstract double getRotationZ();


    public abstract ArrayList<Integer> getInventory();
    public abstract void addOrder(double x, double y, double z);
    public abstract void moveTo(double x, double y, double z);
}