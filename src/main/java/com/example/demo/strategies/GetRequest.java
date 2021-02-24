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

public class GetRequest extends Strategy {

    public GetRequest() {

    }

    @Override
    public Point execute(Order order, Point point) {
        Point newPoint = new Point();
        newPoint.setX(point.getX());
        newPoint.setY(point.getY());
        newPoint.setZ(point.getZ());

        if (order.containsNodes()) {
            if (((order.getFirstNode().getX() - 0.01 <= point.getX()) && (point.getX() <= order.getFirstNode().getX() + 0.01))) {
                if (((order.getFirstNode().getZ() - 0.01 <= point.getZ()) && (point.getZ() <= order.getFirstNode().getZ() + 0.01))) {
                    order.removeVisitedNode();
                    if (!order.containsNodes()) {
                        newPoint.test = true;
                    }
                } else {
                    if (order.getFirstNode().getZ() > point.getZ()) {
                        newPoint.setZ(point.getZ() + 0.1);
                    } else {
                        newPoint.setZ(point.getZ() - 0.1);
                    }
                }
            } else {
                order.stellage.status = true;
                if (order.getFirstNode().getX() > point.getX()) {
                    newPoint.setX(point.getX() + 0.1);
                } else {
                    newPoint.setX(point.getX() - 0.1);
                }
            }
        } 
        return newPoint;
    }


}