package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Stellage;

public abstract class Strategy {
    public abstract void execute(Stellage order, Robot robot);
}