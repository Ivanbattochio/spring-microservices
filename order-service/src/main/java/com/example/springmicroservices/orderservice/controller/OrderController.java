package com.example.springmicroservices.orderservice.controller;


import com.example.springmicroservices.orderservice.dto.OrderDTO;
import com.example.springmicroservices.orderservice.dto.OrderRequest;
import com.example.springmicroservices.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        OrderDTO orderDTO = orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

}
