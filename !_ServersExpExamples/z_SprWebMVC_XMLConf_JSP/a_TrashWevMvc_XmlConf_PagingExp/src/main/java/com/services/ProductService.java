package com.services;

import com.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public Iterable<Product> findAll();
}