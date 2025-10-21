package org.example;

import java.util.List;
import java.util.Objects;

public class ShopService implements OrderRepoInterface {
    private OrderListRepo orderList = new OrderListRepo();
    private OrderMapRepo orderMap = new OrderMapRepo();
    private ProductRepo productRepo = new ProductRepo();

    public ShopService(OrderMapRepo orderMap) {
        this.orderMap = orderMap;
    }

    public ShopService(OrderListRepo orderList) {
        this.orderList = orderList;
    }

    public ShopService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ShopService() {
    }

    // METHODS

    @Override
    public void addOrder(Order order) {
        Product orderedProduct = order.product();
        if (productRepo.productExists(orderedProduct.id())) {
            orderMap.addOrder(order);
            orderList.addOrder(order);
            System.out.println("\nShop-Service: Order added");
        } else {
            System.out.println("Product not found");
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        orderMap.deleteOrder(orderId);
        orderList.deleteOrder(orderId);
        System.out.println("\nShop-Service: Order deleted");
    }

    @Override
    public Order getSingleOrder(String orderId) {
        return orderMap.getSingleOrder(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList.getAllOrders();
    }

    // GETTERS AND SETTERS
    public OrderListRepo getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderListRepo orderList) {
        this.orderList = orderList;
    }

    public OrderMapRepo getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(OrderMapRepo orderMap) {
        this.orderMap = orderMap;
    }

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopService that = (ShopService) o;
        return Objects.equals(orderList, that.orderList) && Objects.equals(orderMap, that.orderMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderList, orderMap);
    }

    @Override
    public String toString() {
        return "ShopService{" +
                "orderList=" + orderList +
                ", orderMap=" + orderMap +
                '}';
    }
}
