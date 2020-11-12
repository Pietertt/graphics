package com.example.demo.models.Observer;

import com.example.demo.models.Truck;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EventManager {
    Map<String, ArrayList<Truck>> listeners = new HashMap<>();

    public EventManager(String... operations){
        for(String operation : operations){
            this.listeners.put(operation, new ArrayList<Truck>());
        }
    }

    public void subscribe(String event, Truck listener){
        ArrayList<Truck> objects = this.listeners.get(event);
        objects.add(listener);
    }
    
    public void notify(String event) {
        ArrayList<Truck> objects = this.listeners.get(event);
        for (Truck listener : objects) {
            listener.update(event);
        }
    }
}