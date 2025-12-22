package com.project1.sampleProject.service;

import com.project1.sampleProject.model.Product;
import com.project1.sampleProject.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;


    public List<Product> gelAllProducts() {
       return productRepo.findAll();
    }
    @Autowired
    public ProductService(ProductRepo productRepo){
        this.productRepo = productRepo;

    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }
}
