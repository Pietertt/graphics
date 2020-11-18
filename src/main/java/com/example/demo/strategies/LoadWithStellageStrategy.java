package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class LoadWithStellageStrategy extends Strategy {

    public LoadWithStellageStrategy(){

    }

    @Override
    public void execute(Order order, Robot robot){
        if(((15 - 0.01 <= robot.getX()) && (robot.getX() <= 15 + 0.01))){
            if((0 - 0.01 <= robot.getZ()) && (robot.getZ() <= 0 + 0.01)){
                robot.events.notify("deliver", order.stellage.getUUID());
                order.stellage.events.notify("loaded", order.stellage.getUUID());
                //robot.orders.get(0).status = false;
                robot.removeOrder(order);
                robot.setStrategy(new LoadWithoutStellageStrategy());
                System.out.println("[ROBOT] Delivered stellage to truck");
            } else {
                if(robot.getZ() > 0){
                    robot.setZ(robot.getZ() - robot.getSpeed());
                    order.stellage.setZ(robot.getZ());
                } else {
                    robot.setZ(robot.getZ() + robot.getSpeed());
                    order.stellage.setZ(robot.getZ());
                }
            }
        } else {
            if(robot.getX() > 15){
                robot.setX(robot.getX() - robot.getSpeed());
                order.stellage.setX(robot.getX());
            } else {
                robot.setX(robot.getX() + robot.getSpeed());
                order.stellage.setX(robot.getX());
            }
        }
    }
}