package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;

public abstract class Strategy {
    public abstract void execute(Order order, Robot robot);
}