package com.example.practice.spring;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);

        ProductService productService = applicationContext.getBean(ProductService.class);

        List<Product> products = Arrays.asList(
                new Product("Notebook", 10),
                new Product("Pencil", 30),
                new Product("Charger", 50),
                new Product("Monitor", 5)
        );

        productService.getProductFinalPrice(products);
    }
}
