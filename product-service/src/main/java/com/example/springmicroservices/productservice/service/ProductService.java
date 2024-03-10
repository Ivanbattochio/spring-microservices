package com.example.springmicroservices.productservice.service;

import com.example.springmicroservices.productservice.dto.ProductCreateRequest;
import com.example.springmicroservices.productservice.dto.ProductDTO;
import com.example.springmicroservices.productservice.model.Product;
import com.example.springmicroservices.productservice.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final IProductRepository productRepository;

    public ProductDTO createProduct(ProductCreateRequest productCreateRequest) {
        Product product = productRepository.save(productCreateRequest.toEntity());
        log.info("Product is saved, id: {}", product.getId());
        return ProductDTO.from(product);
    }

    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(ProductDTO::from).toList();
    }

}
