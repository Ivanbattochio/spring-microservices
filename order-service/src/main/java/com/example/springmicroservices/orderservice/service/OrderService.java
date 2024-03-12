package com.example.springmicroservices.orderservice.service;


import com.example.springmicroservices.orderservice.dto.InventoryResponse;
import com.example.springmicroservices.orderservice.dto.OrderDTO;
import com.example.springmicroservices.orderservice.dto.OrderRequest;
import com.example.springmicroservices.orderservice.model.Order;
import com.example.springmicroservices.orderservice.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final IOrderRepository orderRepository;

    private final WebClient webClientaaa;

    public OrderDTO placeOrder(OrderRequest orderRequest) {


        List<String> skuCodes = orderRequest.getOrderSkuCodes();

        InventoryResponse[] inventoryResponses = webClientaaa.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean b = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (Boolean.FALSE.equals(b)) {
            throw new IllegalArgumentException("a product is not in stock");
        }

        Order order = orderRequest.toEntity();
        orderRepository.save(order);
        log.info("Order is saved, id: {}", order.getId());
        return OrderDTO.from(order);
    }
}
