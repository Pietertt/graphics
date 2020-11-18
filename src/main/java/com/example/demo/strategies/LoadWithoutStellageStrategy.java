package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class LoadWithoutStellageStrategy extends Strategy {

    public LoadWithoutStellageStrategy(){

    }

    @Override
    public void execute(Order order, Robot robot){
    
        /*
            Wanneer de robot in de nabijheid van de desbetreffende stellage komt begint de robot met het vullen
            van de vrachtwagen
        */
        if(((order.getX() - 0.01 <= robot.getX()) && (robot.getX() <= order.getX() + 0.01))){
            if(((order.getZ() - 0.01 <= robot.getZ()) && (robot.getZ() <= order.getZ() + 0.01))){
                robot.setStrategy(new LoadWithStellageStrategy());
                System.out.println("[ROBOT] Arrived at destination, taking stellage to the truck");
            } else {
                if(order.getZ() > robot.getZ()){
                    robot.setZ(robot.getZ() + robot.getSpeed());
                } else {
                    robot.setZ(robot.getZ() - robot.getSpeed());
                }
            }
        } else {
            if(order.getX() > robot.getX()){
                robot.setX(robot.getX() + robot.getSpeed());
            } else {
                robot.setX(robot.getX() - robot.getSpeed());
            }
        }    
    }
}