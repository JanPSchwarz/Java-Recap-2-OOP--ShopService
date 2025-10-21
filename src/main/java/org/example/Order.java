package org.example;

public record Order(Product product, String orderId, int quantity) {
}
