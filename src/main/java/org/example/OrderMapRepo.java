package org.example;

import java.util.*;

public class OrderMapRepo implements OrderRepoInterface {
    private Map<String, Order> orders = new HashMap<>();

    // CONSTRUCTORS
    public OrderMapRepo(Map<String, Order> orders) {
        this.orders = orders;
    }

    public OrderMapRepo() {}

    // METHODS

    public void addOrder(Order order) {
        orders.put(order.orderId(), order);
    }

    public void deleteOrder(String orderId) {
        orders.remove(orderId);
    }

    public Order getSingleOrder(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }


    // GETTERS AND SETTERS
    public Map<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, Order> orders) {
        this.orders = orders;
    }

    // OVERRIDE METHODS
    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderMapRepo that = (OrderMapRepo) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orders);
    }
}
