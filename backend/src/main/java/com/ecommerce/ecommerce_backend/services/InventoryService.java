package com.ecommerce.ecommerce_backend.services;

import com.ecommerce.ecommerce_backend.model.Inventory;
import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.repository.InventoryRepository;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    public Inventory createInventory(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Inventory inventory = Inventory.builder()
                .product(product)
                .quantityAvailable(quantity)
                .build();
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Integer quantity) {
        Inventory inventory = getInventoryById(id);
        inventory.setQuantityAvailable(quantity);
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

}
