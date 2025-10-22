package org.example;

import java.util.Map;

public record Order(Map<Integer, Product> productsWithQuantity, String orderId) {
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Integer, Product> product : productsWithQuantity.entrySet()) {
            double price = product.getValue().price() * product.getKey();
            totalPrice += price;
        }
        return totalPrice;
    }
}
