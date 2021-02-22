package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Order_Session> orderSessions = new ArrayList<>();

    public List<Order_Session> getOrderSessions() {
        return orderSessions;
    }

    public void setOrderSessions(List<Order_Session> orderSessions) {
        this.orderSessions = orderSessions;
    }



    public Order_Session getOrderSession(Product product){
        for(Order_Session orderSession : orderSessions)
        {
            if(orderSession.getProduct().getId().equals(product.getId())) return orderSession;
        }
        Order_Session itemLine = new Order_Session(product);
        orderSessions.add(itemLine);
        return itemLine;
    }

    public void removeOrderSessionByProduct(Product product){
        for(Order_Session orderSession: orderSessions){
            if(orderSession.getProduct().getId().equals(product.getId())){
                orderSessions.remove(orderSession);
                break;
            }
        }
    }


}
