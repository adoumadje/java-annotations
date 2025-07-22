package com.example.practice.spring;

import com.example.practice.spring.annotations.Component;

import java.util.List;

@Component
public class ProductRepository {
    public List<Product> getProductPrice(List<Product> products) {
        for(Product product: products) {
            double price = 100 * Math.random();
            System.out.printf("Product <%s> initial price is: %.2f$%n", product.getName(),  price);
            product.setPrice(price);
        }
        return products;
    }
}
