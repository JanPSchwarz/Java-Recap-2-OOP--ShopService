package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapRepoTest {
    OrderMapRepo orderMapRepo;

    @BeforeEach
    void setUp() {
        Product toothBrush = new Product("1", "Tooth Brush", 1.99);
        Order exampleOrder = new Order(Map.of(toothBrush, 1), "1");

        Map<String, Order> ordersMap = new HashMap<>();
        ordersMap.put(exampleOrder.orderId(), exampleOrder);

        orderMapRepo = new OrderMapRepo(ordersMap);
    }

    @Test
    void addOrder_returnsTrue_whenOrderAdded() {
        // GIVEN
        Product shampoo = new Product("1", "Shampoo", 1.99);
        Order exampleOrder = new Order(Map.of(shampoo, 1), "2");

        // WHEN
        orderMapRepo.addOrder(exampleOrder);

        // THEN
        assertThat(orderMapRepo.getOrders()).containsEntry("2", exampleOrder);
    }

    @Test
    void deleteOrder() {
        // WHEN
        orderMapRepo.deleteOrder("1");

        // THEN
        Map<String, Order> ordersMap = orderMapRepo.getOrders();

        assertThat(ordersMap).isEmpty();
    }

    @Test
    void getSingleOrder_shouldReturnOrder_whenCalledWithExistingOrder() {
        // GIVEN
        Product toothBrush = new Product("1", "Tooth Brush", 1.99);
        Order exampleOrder = new Order(Map.of(toothBrush, 1), "1");
        String orderId = "1";

        // WHEN
        Order expectedOrder = orderMapRepo.getSingleOrder(orderId);

        // THEN
        assertThat(expectedOrder).isEqualTo(exampleOrder);
    }

    @Test
    void getSingleOrder_shouldReturnNull_whenCalledWithNonExistingOrder() {
        // GIVEN
        String orderId = "99";

        // WHEN
        Order expectedOrder = orderMapRepo.getSingleOrder(orderId);

        // THEN
        assertThat(expectedOrder).isNull();
    }

    @Test
    void getAllOrders() {
        // GIVEN
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(orderMapRepo.getSingleOrder("1"));


        // WHEN
        List<Order> orders = orderMapRepo.getAllOrders();

        // THEN
        assertThat(orders).isEqualTo(expectedOrders);
    }
}