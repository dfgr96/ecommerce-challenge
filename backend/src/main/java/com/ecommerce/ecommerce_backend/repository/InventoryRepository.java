package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.model.Inventory;
import com.ecommerce.ecommerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct(Product product);
}
