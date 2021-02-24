package com.example.demo.strategies;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import com.example.demo.models.Robot;
import com.example.demo.models.Order.Order;
import com.example.demo.models.Observer.EventListener;
import com.example.demo.models.Point;

public class ReturnRequest extends Strategy {

    public ReturnRequest() {

    }

    @Override
    public Point execute(Order order, Point point) {
        Point newPoint = new Point();
        newPoint.setX(point.getX());
        newPoint.setY(point.getY());
        newPoint.setZ(point.getZ());

        if(order.containsVisitedNodes()){
            if(((order.getLastNode().getX() - 0.01 <= point.getX()) && (point.getX() <= order.getLastNode().getX() + 0.01))){
                if(((order.getLastNode().getZ() - 0.01 <= point.getZ()) && (point.getZ() <= order.getLastNode().getZ() + 0.01))){
                    order.removeUnvisitedNove();

                    if(!order.containsVisitedNodes()){
                        newPoint.test = true;
                        //this.events.notify("loaded", "loaded");
                        // order.stellage.events.notify("loaded", orderstellage.getUUID());
                        // order.stellage.status = false;
                        // this.removeOrder(order);
                        // this.returning = false;
                    }
                } else {
                    if(point.getZ() > order.getLastNode().getZ()){
                        newPoint.setZ(point.getZ() - 0.1);
                        order.stellage.setX(point.getX());
                        order.stellage.setZ(point.getZ());
                    } else {
                        newPoint.setZ(point.getZ() + 0.1);
                        order.stellage.setX(point.getX());
                        order.stellage.setZ(point.getZ());
                    }
                }
            } else {
                if(point.getX() > order.getLastNode().getX()){
                    newPoint.setX(point.getX() - 0.1);
                    order.stellage.setX(point.getX());
                    order.stellage.setZ(point.getZ());
                } else {
                    newPoint.setX(point.getX() + 0.1);
                    order.stellage.setX(point.getX());
                    order.stellage.setZ(point.getZ());
                }
            }
        }

        return newPoint;
    }
}


