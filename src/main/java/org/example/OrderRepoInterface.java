package org.example;

import java.util.List;

public interface OrderRepoInterface {
    void addOrder(Order order);

    void deleteOrder(String orderId);

    Order getSingleOrder(String orderId);

    List<Order> getAllOrders();

    void printAllOrders();

}
