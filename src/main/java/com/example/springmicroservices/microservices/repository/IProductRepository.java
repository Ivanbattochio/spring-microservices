package com.example.springmicroservices.microservices.repository;

import com.example.springmicroservices.microservices.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<Product, String> {
}
