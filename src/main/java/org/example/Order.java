package org.example;

import java.math.BigDecimal;
import java.util.Map;

public record Order(Map<Product, Integer> productsWithQuantity, String orderId) {
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Map.Entry<Product, Integer> product : productsWithQuantity.entrySet()) {
            // BigDecimal price = product.getKey().price() * product.getValue();
            BigDecimal price = product.getKey().price().multiply(new BigDecimal(product.getValue()));
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }
}
