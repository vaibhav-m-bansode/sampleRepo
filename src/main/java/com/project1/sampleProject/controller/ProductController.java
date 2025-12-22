package com.project1.sampleProject.controller;

import com.project1.sampleProject.model.Product;
import com.project1.sampleProject.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    final private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.gelAllProducts();
    }


    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> GetProductById(@PathVariable int id){
        Product product = productService.getProductById(id);

        if(product != null) {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}