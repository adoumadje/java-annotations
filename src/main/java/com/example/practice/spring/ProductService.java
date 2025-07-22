package com.example.practice.spring;

import com.example.practice.spring.annotations.Component;

import java.util.List;

@Component
public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public void getProductFinalPrice(List<Product> products) {
        List<Product> dbProducts = productRepository.getProductPrice(products);
        for(Product product: dbProducts) {
            double price = product.getPrice();
            int discount = product.getDiscount();
            price = price - price * (double) discount / 100;
            System.out.printf("product <%s> price after %d%%  discount is: %.2f$%n",
                    product.getName(), discount, price);
            product.setPrice(price);
        }
    }
}
