package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    ProductRepo productRepo;

    @BeforeEach
    void setUp() {
        productRepo = new ProductRepo();
        Product product1 = new Product("1", "Product 1", 1.99);
        productRepo.addProduct(product1);
    }

    @Test
    void addProduct_shouldReturnTrue_whenProductAdded() {
        // GIVEN
        Product product2 = new Product("2", "Product 2", 1.99);

        // WHEN
        productRepo.addProduct(product2);
        Map<String, Product> productsMap = productRepo.getProducts();
        boolean expected = productsMap.containsValue(product2);

        // THEN
        assertTrue(expected);
    }

    @Test
    void getSingleProduct_shouldReturnProduct_whenProductExists() {
        Product expectedProduct = new Product("1", "Product 1", 1.99);
        Product existingProduct = productRepo.getSingleProduct("1");
        assertEquals(existingProduct, expectedProduct);
    }

    @Test
    void getSingleProduct_shouldReturnNull_whenProductDoesNotExist() {
        String nonExistentProductId = "2";
        Product nonExistingProduct = productRepo.getSingleProduct(nonExistentProductId);

        assertNull(nonExistingProduct);
    }

    @Test
    void productExists_shouldReturnTrue_whenProductExists() {
        boolean productExists = productRepo.productExists("1");
        assertTrue(productExists);
    }

    @Test
    void productExists_shouldReturnFalse_whenProductDoesNotExist() {
        boolean productExists = productRepo.productExists("2");
        assertFalse(productExists);
    }

    // WITH ASSERT-J LIBRARY
    @Test
    void deleteProduct_shouldReturnFalse_whenProductDeleted() {
        // GIVEN
        Product deletedProduct = new Product("1", "Product 1", 1.99);
        Map<String, Product> productsMap = productRepo.getProducts();

        // WHEN
        productRepo.deleteProduct("1");

        // THEN
        assertThat(productsMap).doesNotContainEntry("1", deletedProduct);
    }

    @Test
    void getAllProducts_shouldReturnTrue_whenProductMapComplete() {
        // GIVEN
        Map<String, Product> productsMap = productRepo.getProducts();
        Product existingProduct = new Product("1", "Product 1", 1.99);

        // THEN
        assertThat(productsMap)
                .containsKey(existingProduct.id()).containsValue(existingProduct);
    }
}