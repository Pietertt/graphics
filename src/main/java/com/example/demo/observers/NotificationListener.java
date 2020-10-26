package com.example.demo.observers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.demo.models.Object3D;

import java.util.ArrayList;
import com.example.demo.models.World;

public class NotificationListener extends EventListener {
    //private ArrayList<Object3D> worldObjects;
    private World world;

    public NotificationListener(World world) {
        this.world = world;
    }

    public void action(Object3D object) {
        // for(Object3D o : this.world.worldObjects){
        //     if(o.getUUID() == object.getUUID()){
        //         this.world.worldObjects.remove(object);
        //         System.out.println("Deleted!");
        //     }
        // }

        System.out.println("Deleted");
        
        //this.world.worldObjects.remove(object);
    }
}