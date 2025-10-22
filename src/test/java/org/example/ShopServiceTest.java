package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ShopServiceTest {

    ShopService testShop;

    @BeforeEach
    void setUp() {

        // CREATE PRODUCTS
        Product toothbrush = new Product("1", "Toothbrush", new BigDecimal("2.99"));
        Product shampoo = new Product("2", "Shampoo", new BigDecimal("4.99"));

        // BUNDLE TO MAP
        Map<Product, Integer> products = new HashMap<>();
        products.put(toothbrush, 1);
        products.put(shampoo, 1);

        Order exampleOrder = new Order(products, "1");
        Map<String, Order> orderMap = new HashMap<>();
        orderMap.put("1", exampleOrder);

        // CREATE ORDER-MAP-REPO WITH CONSTRUCTOR-PARAMS MAP
        OrderMapRepo orderMapRepo = new OrderMapRepo(orderMap);

        // CREATE SHOP WITH ORDER-MAP-REPO
        testShop = new ShopService(orderMapRepo);
    }

    @Test
    void getOrderTotalPrice_shouldReturn_7_98() {
        // GIVEN
        BigDecimal totalPrice = testShop.getOrderTotalPrice("1");

        assertThat(totalPrice).isEqualTo(new BigDecimal("7.98"));
    }

    @Test
    void addOrder_shouldReturnTrue_whenOrderAddedAndExistsInMap() {
        // GIVEN
        Product soap = new Product("1", "Soap", new BigDecimal("0.99"));
        Order newOrder = new Order(Map.of(soap, 1), "2");

        // WHEN
        testShop.getProductRepo().addProduct(soap);
        testShop.addOrder(newOrder);

        Map<String, Order> orderMap = testShop.getOrderMap().getOrders();

        assertThat(orderMap).containsEntry("2", newOrder);
    }
}