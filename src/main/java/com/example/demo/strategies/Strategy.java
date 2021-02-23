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

public abstract class Strategy {
    protected Order order;

    public Strategy() {

    }

    public abstract Point execute(Order order, Point point);
}