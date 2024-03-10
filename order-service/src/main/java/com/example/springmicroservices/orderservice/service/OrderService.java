package com.example.springmicroservices.orderservice.service;


import com.example.springmicroservices.orderservice.dto.OrderDTO;
import com.example.springmicroservices.orderservice.dto.OrderRequest;
import com.example.springmicroservices.orderservice.model.Order;
import com.example.springmicroservices.orderservice.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final IOrderRepository orderRepository;

    public OrderDTO placeOrder(OrderRequest orderRequest) {
        Order order = orderRepository.save(orderRequest.toEntity());
        log.info("Order is saved, id: {}", order.getId());
        return OrderDTO.from(order);
    }
}
