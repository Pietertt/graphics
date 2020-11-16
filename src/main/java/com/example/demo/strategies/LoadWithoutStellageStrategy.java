package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class LoadWithoutStellageStrategy extends Strategy {

    public LoadWithoutStellageStrategy(){

    }

    @Override
    public void execute(Stellage stellage, Robot robot){
    
        /*
            Wanneer de robot in de nabijheid van de desbetreffende stellage komt begint de robot met het vullen
            van de vrachtwagen
        */
        if(((stellage.getX() - 0.01 <= robot.getX()) && (robot.getX() <= stellage.getX() + 0.01))){
            if(((stellage.getZ() - 0.01 <= robot.getZ()) && (robot.getZ() <= stellage.getZ() + 0.01))){
                robot.setStrategy(new LoadWithStellageStrategy());
            } else {
                if(stellage.getZ() > robot.getZ()){
                    robot.setZ(robot.getZ() + robot.getSpeed());
                } else {
                    robot.setZ(robot.getZ() - robot.getSpeed());
                }
            }
        } else {
            if(stellage.getX() > robot.getX()){
                robot.setX(robot.getX() + robot.getSpeed());
            } else {
                robot.setX(robot.getX() - robot.getSpeed());
            }
        }    
    }
}