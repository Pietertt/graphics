package com.example.demo.observers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.models.Object3D;

public class EventManager {
    Map<String, ArrayList<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations){
        for(String operation : operations){
            this.listeners.put(operation, new ArrayList<EventListener>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        ArrayList<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        ArrayList<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, Object3D object) {
        ArrayList<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.action(object);
        }
    }
}