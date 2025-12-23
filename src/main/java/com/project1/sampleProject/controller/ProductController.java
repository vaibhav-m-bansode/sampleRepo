package com.project1.sampleProject.controller;

import com.project1.sampleProject.model.Product;
import com.project1.sampleProject.repository.ProductRepo;
import com.project1.sampleProject.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile image){

        try{
            Product pr1 = productService.addProduct(product,image);
            return new ResponseEntity<>(pr1,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/Products/{id}/image")
    public ResponseEntity<byte[]> fetchImageById(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if (product == null || product.getImageData() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(product.getImageType());
        } catch (Exception e) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM; // fallback
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(product.getImageData());
    }

    @PutMapping("/Products/{id}")
    public ResponseEntity<?> updateProduct(@RequestPart int id,
                                                 @RequestPart Product product,
                                                 @RequestPart MultipartFile image) {
        try {
            Product prd = productService.updateProduct(id, product, image);

            return new ResponseEntity<>(prd, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot find the product to update " + e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/Product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){

        Product prd = productService.getProductById(id);
        if(prd != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);


    }


}