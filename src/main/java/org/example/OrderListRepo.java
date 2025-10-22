package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderListRepo implements OrderRepoInterface {
    private List<Order> orders = new ArrayList<>();

    // CONSTRUCTORS
    public OrderListRepo(List<Order> orders) {
        this.orders = orders;
    }

    public OrderListRepo() {
    }

    // OWN METHODS
    Order findOrder(String orderId) {
        for (Order order : orders) {
            if (order.orderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    // INTERFACE METHODS
    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orders.remove(findOrder(orderId));
    }

    @Override
    public Order getSingleOrder(String orderId) {
        return findOrder(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public void printAllOrders() {
        System.out.println("\nAll Orders in OrderListRepo:");
        for (Order order : orders) {
            Map<Integer, Product> actualOrders = order.productsWithQuantity();
            for (Map.Entry<Integer, Product> entry : actualOrders.entrySet()) {
                int quantity = entry.getKey();
                String name = entry.getValue().name();
                System.out.println(quantity + "x " + name);
            }
        }
    }

    // GETTERS AND SETTERS
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // OVERRIDE DEFAULT METHODS
    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orders);
    }
}
