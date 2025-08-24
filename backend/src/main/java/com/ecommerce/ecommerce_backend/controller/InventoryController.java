package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.Inventory;
import com.ecommerce.ecommerce_backend.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping("/{productId}")
    public Inventory createInventory(@PathVariable Long productId, @RequestParam Integer quantity) {
        return inventoryService.createInventory(productId, quantity);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @RequestParam Integer quantity) {
        return inventoryService.updateInventory(id, quantity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

}
