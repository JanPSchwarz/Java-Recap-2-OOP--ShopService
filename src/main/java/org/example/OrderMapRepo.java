package org.example;

import java.util.*;

public class OrderMapRepo implements OrderRepoInterface {
    private Map<String, Order> orders = new HashMap<>();

    // CONSTRUCTORS
    public OrderMapRepo(Map<String, Order> orders) {
        this.orders = orders;
    }

    public OrderMapRepo() {
    }

    // METHODS
    @Override
    public void addOrder(Order order) {
        orders.put(order.orderId(), order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orders.remove(orderId);
    }

    @Override
    public Order getSingleOrder(String orderId) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void printAllOrders() {
        System.out.println("All Orders in Map:");
        for (Map.Entry<String, Order> order : orders.entrySet()) {
            Map<Product, Integer> actualOrder = order.getValue().productsWithQuantity();
            for (Map.Entry<Product, Integer> productWithQuantity : actualOrder.entrySet()) {
                System.out.println(productWithQuantity.getValue() + "x " + productWithQuantity.getKey().name());
            }

        }
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
