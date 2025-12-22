package com.project1.sampleProject.service;

import com.project1.sampleProject.model.Product;
import com.project1.sampleProject.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageData(image.getBytes());
        product.setImageType(image.getContentType());
        product.setImageName(image.getOriginalFilename());
        return productRepo.save(product);
    }
}
