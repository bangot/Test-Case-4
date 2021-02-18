package com.example.demo.service.product;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    Product finById(long id);
    void save(Product product);
    void delete(long id);
}
