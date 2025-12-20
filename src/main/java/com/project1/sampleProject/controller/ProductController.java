package com.project1.sampleProject.controller;

import com.project1.sampleProject.model.Product;
import com.project1.sampleProject.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    final private ProductService productService;


    @RequestMapping("/")
    public String greet() {
        return "Hello World";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.gelAllProducts();
    }
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
}