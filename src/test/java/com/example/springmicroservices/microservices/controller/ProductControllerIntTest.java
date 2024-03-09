package com.example.springmicroservices.microservices.controller;


import com.example.springmicroservices.microservices.dto.ProductCreateRequest;
import com.example.springmicroservices.microservices.model.Product;
import com.example.springmicroservices.microservices.repository.IProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductControllerIntTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.6");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setup() {
        List<Product> productList = List.of(new Product("1", "name", "description", BigDecimal.TEN), new Product("2", "name2", "description2", BigDecimal.valueOf(2L)));
        productRepository.saveAll(productList);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name("productName")
                .description("productDescription")
                .price(BigDecimal.TEN)
                .build();

        String productRequestString = objectMapper.writeValueAsString(productCreateRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("productName")))
                .andExpect(jsonPath("$.description", is("productDescription")))
                .andExpect(jsonPath("$.price", is(BigDecimal.TEN.intValue())))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void shouldGetProducts() {
        //assert get request to /api/product returns a list of two products (with its own properties) that where inserted in the setup method
    }
}
