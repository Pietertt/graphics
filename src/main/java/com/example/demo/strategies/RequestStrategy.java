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

public class RequestStrategy extends Strategy {

    public RequestStrategy() {

    }

    @Override
    public Point execute(Order order, Point point) {
        Point newPoint = new Point();

        if(order.containsVisitedNodes()){
            if(((order.getLastNode().getX() - 0.01 <= point.getX()) && (point.getX() <= order.getLastNode().getX() + 0.01))){
                if(((order.getLastNode().getZ() - 0.01 <= point.getZ()) && (point.getZ() <= order.getLastNode().getZ() + 0.01))){
                    order.removeUnvisitedNove();

                    if(!order.containsVisitedNodes()){
                    //     this.events.notify("unloaded", "unloaded");
                    //     this.getFirstOrder().stellage.events.notify("unloaded", this.getFirstOrder().stellage.getUUID());
                    //     this.getFirstOrder().stellage.status = true;
                    //     this.removeOrder(this.getFirstOrder());
                    //     this.returning = false;
                    //     System.out.println("Returned to truck");
                    //     return 1;
                    }
                } else {
                    if(point.getZ() > order.getLastNode().getZ()){
                        newPoint.setZ(point.getZ() - 0.1);
                    } else {
                        newPoint.setZ(point.getZ() + 0.1);
                    }
                }
            } else {
                if(point.getX() > order.getLastNode().getX()){
                    newPoint.setX(point.getX() - 0.1);
                } else {
                    newPoint.setX(point.getX() + 0.1);
                }
            }
        }
        return newPoint;
    }


}