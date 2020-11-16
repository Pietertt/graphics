package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class LoadWithStellageStrategy extends Strategy {

    public LoadWithStellageStrategy(){

    }

    @Override
    public void execute(Stellage order, Robot robot){
        if(((15 - 0.01 <= robot.getX()) && (robot.getX() <= 15 + 0.01))){
            if((0 - 0.01 <= robot.getZ()) && (robot.getZ() <= 0 + 0.01)){
                robot.events.notify("deliver", robot.orders.get(0).getUUID());
                robot.orders.get(0).events.notify("loaded", robot.orders.get(0).getUUID());
                robot.orders.get(0).status = false;
                robot.orders.remove(0);  
                robot.setStrategy(new LoadWithoutStellageStrategy());
            } else {
                if(robot.getZ() > 0){
                    robot.setZ(robot.getZ() - robot.getSpeed());
                    robot.orders.get(0).setZ(robot.getZ());
                } else {
                    robot.setZ(robot.getZ() + robot.getSpeed());
                    robot.orders.get(0).setZ(robot.getZ()); 
                }
            }
        } else {
            if(robot.getX() > 15){
                robot.setX(robot.getX() - robot.getSpeed());
                robot.orders.get(0).setX(robot.getX());
            } else {
                robot.setX(robot.getX() + robot.getSpeed());
                robot.orders.get(0).setX(robot.getX());
            }
        }
    }
}