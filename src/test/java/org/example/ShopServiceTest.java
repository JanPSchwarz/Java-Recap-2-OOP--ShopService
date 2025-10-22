package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ShopServiceTest {

    ShopService testShop;

    @BeforeEach
    void setUp() {

        // CREATE PRODUCTS
        Product toothbrush = new Product("1", "Toothbrush", 2.99);
        Product shampoo = new Product("2", "Shampoo", 4.99);

        // BUNDLE TO MAP
        Map<Product, Integer> products = new HashMap<>();
        products.put(toothbrush, 1);
        products.put(shampoo, 1);

        Order exampleOrder = new Order(products, "1");
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        orderMapRepo.addOrder(exampleOrder);

        // CREATE SHOP WITH ORDER-MAP
        testShop = new ShopService(orderMapRepo);
    }

    @Test
    void getOrderTotalPrice_shouldReturn_7_98() {
        // GIVEN
        double totalPrice = testShop.getOrderTotalPrice("1");

        assertThat(totalPrice).isEqualTo(7.98);
    }

    @Test
    void addOrder_shouldReturnTrue_whenOrderAddedAndExistsInMap() {
        // GIVEN
        Product soap = new Product("1", "Soap", 0.99);
        Order newOrder = new Order(Map.of(soap, 1), "2");

        // WHEN
        testShop.getProductRepo().addProduct(soap);
        testShop.addOrder(newOrder);

        Map<String, Order> orderMap = testShop.getOrderMap().getOrders();

        assertThat(orderMap).containsEntry("2", newOrder);
    }
}