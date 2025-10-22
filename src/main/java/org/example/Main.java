package org.example;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();

        // CREATE NEW PRODUCTS
        Product toothBrush = new Product("1", "Tooth Brush", new BigDecimal("2.99"));
        Product toiletPaper = new Product("2", "Toilet Paper", new BigDecimal("4.99"));
        Product beautyShampoo = new Product("1", "Beauty Shampoo", new BigDecimal("12.99"));

        // ADD PRODUCTS TO PRODUCT-REPO
        productRepo.addProduct(toothBrush);
        productRepo.addProduct(beautyShampoo);
        productRepo.addProduct(toiletPaper);


        // IMPLEMENT SHOP WITH CREATED PRODUCT-REPO
        ShopService drugStore = new ShopService(productRepo);

        Order toothBrushOrder = new Order(Map.of(toothBrush, 1), "1");

        drugStore.addOrder(toothBrushOrder);

        drugStore.getOrderMap().printAllOrders();
        drugStore.getOrderList().printAllOrders();


        // NON EXISTING PRODUCT RETURNS NULL AND SOUT WITH INFO
        Product nonExistingProduct = new Product("5", "Non Existing Product", new BigDecimal("12.99"));
        Order notPossibleOrder = new Order(Map.of(nonExistingProduct, 1), "1");
        drugStore.addOrder(notPossibleOrder);
    }

}