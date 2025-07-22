package com.example.practice.spring;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        List<Product> products = Arrays.asList(
                new Product("Notebook", 10),
                new Product("Pencil", 30),
                new Product("Charger", 50),
                new Product("Monitor", 5)
        );

        productService.getProductFinalPrice(products);
    }
}
