package com.example.springmicroservices.orderservice.dto;

import com.example.springmicroservices.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDTO {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderLineItems toEntity() {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(this.getQuantity());
        orderLineItems.setSkuCode(this.getSkuCode());
        orderLineItems.setPrice(this.getPrice());

        return orderLineItems;
    }

    public static OrderLineItemsDTO from(OrderLineItems entity) {
        OrderLineItemsDTO orderLineItemsDTO = new OrderLineItemsDTO();
        orderLineItemsDTO.setId(entity.getId());
        orderLineItemsDTO.setQuantity(entity.getQuantity());
        orderLineItemsDTO.setSkuCode(entity.getSkuCode());
        orderLineItemsDTO.setPrice(entity.getPrice());

        return orderLineItemsDTO;
    }
}
