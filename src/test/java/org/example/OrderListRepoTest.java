package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OrderListRepoTest {
    OrderListRepo orderListRepo;

    @BeforeEach
    void setUp() {
        // ADD PRODUCT TO ORDER AND ORDER TO A LIST
        Product toothbrush = new Product("1", "Toothbrush", 1.99);
        Order exampleOrder = new Order(Map.of(toothbrush, 1), "1");
        List<Order> orderList = new ArrayList<>();
        orderList.add(exampleOrder);

        // INSTANCE ORDER-LIST-REPO
        orderListRepo = new OrderListRepo(orderList);
    }

    @Test
    void findOrder_shouldReturnTrue_whenCalledWithExistingOrderID() {
        // GIVEN
        Product toothbrush = new Product("1", "Toothbrush", 1.99);
        Order exampleOrder = new Order(Map.of(toothbrush, 1), "1");

        String orderId = "1";

        // WHEN
        Order order = orderListRepo.findOrder(orderId);
        // THEN
        assertThat(order).isEqualTo(exampleOrder);
    }

    @Test
    void findOrder_shouldReturnNull_whenCalledNonExistingOrderID() {
        // GIVEN
        String orderId = "99";

        // WHEN
        Order order = orderListRepo.findOrder(orderId);
        // THEN
        assertThat(order).isNull();
    }

    @Test
    void addOrder_shouldReturnTrue_whenAddedOrderExists() {
        // GIVEN
        Product shampoo = new Product("2", "Shampoo", 1.99);
        Order exampleOrder = new Order(Map.of(shampoo, 1), "2");

        // WHEN
        orderListRepo.addOrder(exampleOrder);
        List<Order> orders = orderListRepo.getOrders();

        // THEN
        assertThat(orders).contains(exampleOrder);
    }

    @Test
    void deleteOrder() {
        // GIVEN
        List<Order> orders = orderListRepo.getOrders();
        // WHEN
        orderListRepo.deleteOrder("1");

        // THEN
        assertThat(orders).isEmpty();
    }

    @Test
    void getAllOrders() {
        // GIVEN
        Product shampoo = new Product("2", "shampoo", 2.99);
        Order exampleOrder = new Order(Map.of(shampoo, 1), "2");
        orderListRepo.addOrder(exampleOrder);

        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(orderListRepo.getSingleOrder("1"));
        expectedOrders.add(exampleOrder);

        // WHEN
        List<Order> actualOrders = orderListRepo.getAllOrders();

        // THEN
        assertThat(actualOrders).isEqualTo(expectedOrders);
    }
}