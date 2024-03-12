package com.example.microservices.inventoryservice.repository;

import com.example.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);
}
