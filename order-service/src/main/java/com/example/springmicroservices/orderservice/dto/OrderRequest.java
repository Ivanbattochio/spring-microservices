package com.example.springmicroservices.orderservice.dto;

import com.example.springmicroservices.orderservice.model.Order;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty(message = "There must be at least one order line")
    private List<OrderLineItemsDTO> orderLineItemsList;

    public Order toEntity() {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(this.getOrderLineItemsList().stream().map(OrderLineItemsDTO::toEntity).toList());

        return order;
    }

    public List<String> getOrderSkuCodes() {
        return this.getOrderLineItemsList().stream().map(OrderLineItemsDTO::getSkuCode).toList();
    }
}
