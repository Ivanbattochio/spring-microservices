package com.example.springmicroservices.productservice.repository;

import com.example.springmicroservices.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<Product, String> {
}
