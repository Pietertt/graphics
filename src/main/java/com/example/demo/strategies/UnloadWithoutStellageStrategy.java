package com.example.demo.strategies;

import com.example.demo.models.Robot;
import com.example.demo.models.Order;
import com.example.demo.models.Stellage;

public class UnloadWithoutStellageStrategy extends Strategy {

    public UnloadWithoutStellageStrategy(){

    }

    @Override
    public void execute(Order order, Robot robot){
        if(((15 - 0.01 <= robot.getX()) && (robot.getX() <= 15 + 0.01))){
            if((0 - 0.01 <= robot.getZ()) && (robot.getZ() <= 0 + 0.01)){
                robot.events.notify("deliver", order.stellage.getUUID());
                order.stellage.events.notify("unloaded", order.stellage.getUUID());
                //robot.orders.get(0).status = false;
                robot.removeOrder(order);
                if(robot.gotAnyWishOrders()){
                    robot.setStrategy(new UnloadWithStellageStrategy());
                } else {
                    if(robot.gotAnyOrders()){
                        robot.setStrategy(new LoadWithoutStellageStrategy());
                    }
                }
                System.out.println("[ROBOT] Returning empty to truck");
            } else {
                if(robot.getZ() > 0){
                    robot.setZ(robot.getZ() - robot.getSpeed());
                } else {
                    robot.setZ(robot.getZ() + robot.getSpeed());
                }
            }
        } else {
            if(robot.getX() > 15){
                robot.setX(robot.getX() - robot.getSpeed());
            } else {
                robot.setX(robot.getX() + robot.getSpeed());
            }
        }
    }
}