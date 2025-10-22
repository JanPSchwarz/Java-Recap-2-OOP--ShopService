package org.example;

import java.util.Map;
import java.util.Objects;

public class ShopService {
    private OrderListRepo orderList = new OrderListRepo();
    private OrderMapRepo orderMap = new OrderMapRepo();

    private ProductRepo productRepo = new ProductRepo();


    // CONSTRUCTORS
    public ShopService(OrderMapRepo orderMap) {
        this.orderMap = orderMap;
    }

    public ShopService(OrderListRepo orderList) {
        this.orderList = orderList;
    }

    public ShopService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ShopService(OrderListRepo orderList, ProductRepo productRepo) {
        this.orderList = orderList;
        this.productRepo = productRepo;
    }

    public ShopService(OrderMapRepo orderMap, ProductRepo productRepo) {
        this.orderMap = orderMap;
        this.productRepo = productRepo;
    }

    public ShopService(OrderListRepo orderList, OrderMapRepo orderMap, ProductRepo productRepo) {
        this.orderList = orderList;
        this.orderMap = orderMap;
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

    public void addOrder(Order order) {
        Map<Product, Integer> orderedProductsMap = order.productsWithQuantity();

        for (Map.Entry<Product, Integer> productEntry : orderedProductsMap.entrySet()) {
            Product product = productEntry.getKey();
            boolean productExists = productRepo.productExists(product.id());
            if (!productExists) {
                System.out.println("\nProduct with id " + product.id() + " does not exist");
                System.out.println("Order has not been added");
                return; // EARLY RETURN IF ONE PRODUCT DOES NOT EXIST
            }
        }

        // ADDING ACTUAL ORDER
        orderMap.addOrder(order);
        orderList.addOrder(order);

        System.out.println("\nOrder has been added:");
        for (Map.Entry<Product, Integer> productEntry : order.productsWithQuantity().entrySet()) {
            Product product = productEntry.getKey();
            Integer quantity = productEntry.getValue();
            System.out.println(quantity + "x " + product.name());
        }
        System.out.println("\nTotal price is: " + order.getTotalPrice());

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
