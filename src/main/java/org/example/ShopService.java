package org.example;

import java.util.List;
import java.util.Map;
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

    public double getOrderTotalPrice(String orderId) {
        Order order = orderMap.getSingleOrder(orderId);
        if (!Objects.isNull(order)) {
            return order.getTotalPrice();
        } else {
            System.out.println("Order Not Found");
            return 0;
        }
    }

    // INTERFACE METHODS
    @Override
    public void addOrder(Order order) {
        Map<Integer, Product> orderedProductsMap = order.productsWithQuantity();

        for (Map.Entry<Integer, Product> productEntry : orderedProductsMap.entrySet()) {
            Product product = productEntry.getValue();
            boolean productExists = productRepo.productExists(product.id());
            if (!productExists) {
                System.out.println("Product with id " + product.id() + " does not exist");
                System.out.println("Order has not been added");
                return;
            }
        }

        System.out.println("\nOrder has been added:");
        for (Map.Entry<Integer, Product> productEntry : order.productsWithQuantity().entrySet()) {
            Product product = productEntry.getValue();
            Integer quantity = productEntry.getKey();
            System.out.println(quantity + "x " + product.name());
        }
        System.out.println("\nTotal price is: " + order.getTotalPrice());

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
