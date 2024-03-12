package com.example.microservices.inventoryservice.controller;

import com.example.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isInStock(@PathVariable("sku-code") String skuCode) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCode));
    }
}
