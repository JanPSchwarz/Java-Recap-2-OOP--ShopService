package org.example;

import java.util.Map;

public record Order(Map<Product, Integer> productsWithQuantity, String orderId) {
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> product : productsWithQuantity.entrySet()) {
            double price = product.getKey().price() * product.getValue();
            totalPrice += price;
        }
        return totalPrice;
    }
}
