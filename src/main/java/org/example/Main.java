package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();

        // CREATE NEW PRODUCTS
        Product toothBrush = new Product("1", "Tooth Brush", 2.99);
        Product toiletPaper = new Product("2", "Toilet Paper", 4.99);
        Product beautyShampoo = new Product("1", "Beauty Shampoo", 12.99);

        // ADD PRODUCTS TO PRODUCT-REPO
        productRepo.addProduct(toothBrush);
        productRepo.addProduct(beautyShampoo);
        productRepo.addProduct(toiletPaper);


        // IMPLEMENT SHOP WITH CREATED PRODUCT-REPO
        ShopService drugStore = new ShopService(productRepo);

        Order toothBrushOrder = new Order(Map.of(1, toothBrush), "1");

        drugStore.addOrder(toothBrushOrder);

        drugStore.getAllOrders().forEach(System.out::println);

        Product nonExistingProduct = new Product("5", "Non Existing Product", 12.99);
        Order notPossibleOrder = new Order(Map.of(1, nonExistingProduct), "1");
        drugStore.addOrder(notPossibleOrder);
    }

}