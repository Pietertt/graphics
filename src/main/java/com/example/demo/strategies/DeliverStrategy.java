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

public class DeliverStrategy extends Strategy {

    public DeliverStrategy() {
        
    }

    @Override
    public Point execute(Order order, Point point) {
        Point newPoint = new Point();
        if (order.containsNodes()) {
            if (((order.getFirstNode().getX() - 0.01 <= point.getX()) && (point.getX() <= order.getFirstNode().getX() + 0.01))) {
                if (((order.getFirstNode().getZ() - 0.01 <= point.getZ()) && (point.getZ() <= order.getFirstNode().getZ() + 0.01))) {
                    order.removeVisitedNode();

                    // if(!this.getFirstOrder().containsNodes()){
                    //     this.returning = true;
                    // }
                } else {
                    if(order.getFirstNode().getZ() > point.getZ()){
                        newPoint.setZ(point.getZ() + 0.1);
                        order.stellage.setX(point.getX());
                        order.stellage.setZ(point.getZ());
                    } else {
                        newPoint.setZ(point.getZ() - 0.1);
                        order.stellage.setX(point.getX());
                        order.stellage.setZ(point.getZ());
                    }
                }
            } else {
                order.stellage.status = true;
                if(order.getFirstNode().getX() > point.getX()){
                    newPoint.setX(point.getX() + 0.1);
                    order.stellage.setX(point.getX());
                    order.stellage.setZ(point.getZ());
                } else {
                    newPoint.setX(point.getX() - 0.1);
                    order.stellage.setX(point.getX());
                    order.stellage.setZ(point.getZ());
                }
            }
        }
        return newPoint;
    }


}