package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class UnloadWithStellageStrategy extends Strategy {

    public UnloadWithStellageStrategy(){

    }

    @Override
    public void execute(Order order, Robot robot){
        if(((order.getX() - 0.01 <= robot.getX()) && (robot.getX() <= order.getX() + 0.01))){
            if(((order.getZ() - 0.01 <= robot.getZ()) && (robot.getZ() <= order.getZ() + 0.01))){
                robot.setStrategy(new UnloadWithoutStellageStrategy());
            } else {
                if(order.getZ() > robot.getZ()){
                    robot.setZ(robot.getZ() + robot.getSpeed());
                    order.stellage.setZ(robot.getZ());
                } else {
                    robot.setZ(robot.getZ() - robot.getSpeed());
                    order.stellage.setZ(robot.getZ());
                }
            }
        } else {
            if(order.getX() > robot.getX()){
                robot.setX(robot.getX() + robot.getSpeed());
                order.stellage.setX(robot.getX());
                order.stellage.setZ(robot.getZ());
            } else {
                robot.setX(robot.getX() - robot.getSpeed());
                order.stellage.setX(robot.getX());
                order.stellage.setZ(robot.getZ());
            }
        }  
    }
}