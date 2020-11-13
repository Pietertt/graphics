package com.example.demo.models.Observer;

import com.example.demo.models.Stellage;
import com.example.demo.models.Truck;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {
    public Map<String, ArrayList<Truck>> listeners = new HashMap<>();

    public EventManager(String... operations){
        for(String operation : operations){
            this.listeners.put(operation, new ArrayList<Truck>());
        }
    }

    public void subscribe(String event, Truck listener){
        ArrayList<Truck> objects = this.listeners.get(event);
        objects.add(listener);
    }

    public void unsubscribe(String event, Truck listener){
        ArrayList<Truck> objects = this.listeners.get(event);
        for(int i = 0; i < objects.size(); i++){
            objects.remove(i);
        }
    }
    
    public void notify(String event, String message) {
        ArrayList<Truck> objects = this.listeners.get(event);
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).update(event, message);
        } 
    }
}