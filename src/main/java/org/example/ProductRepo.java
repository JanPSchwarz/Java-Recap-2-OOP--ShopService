package org.example;

import java.util.*;

public class ProductRepo {

    private Map<String, Product> products = new HashMap<>();


    // CONSTRUCTORS
    public ProductRepo(Map<String, Product> products) {
        this.products = products;
    }

    public ProductRepo() {

    }

    // METHODS
    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    public Product getSingleProduct(String id) {
        return products.get(id);
    }

    public boolean productExists(String id) {
        return products.containsKey(id);
    }

    public void deleteProduct(String id) {
        products.remove(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // GETTERS AND SETTERS


    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    // OVERRIDE METHODS


    @Override
    public String toString() {
        return "ProductRepo{" +
                "products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }
}
