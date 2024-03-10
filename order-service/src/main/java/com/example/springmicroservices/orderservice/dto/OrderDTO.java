package com.example.springmicroservices.orderservice.dto;

import com.example.springmicroservices.orderservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private List<OrderLineItemsDTO> orderLineItemsList;

    public static OrderDTO from(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setOrderNumber(entity.getOrderNumber());
        orderDTO.setOrderLineItemsList(entity.getOrderLineItemsList().stream().map(OrderLineItemsDTO::from).toList());

        return orderDTO;
    }
}
