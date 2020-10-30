package com.example.demo.observers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.demo.models.Object3D;

import java.util.ArrayList;
import com.example.demo.models.World;

public class DeletionListener extends EventListener {

    public DeletionListener() {
    
    }

    public void action(Object3D object) {
        object.status = false;
    }
}