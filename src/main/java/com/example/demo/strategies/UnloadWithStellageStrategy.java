package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class UnloadWithStellageStrategy extends Strategy {

    public UnloadWithStellageStrategy(){

    }

    @Override
    public void execute(Stellage order, Robot robot){
        System.out.println("Unloading...");
    }
}