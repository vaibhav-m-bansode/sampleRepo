package com.project1.sampleProject.repository;

import com.project1.sampleProject.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
