package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.CustomerOrder;
import com.ecommerce.ecommerce_backend.model.CustomerOrderItem;
import com.ecommerce.ecommerce_backend.services.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<CustomerOrder> createOrder(@PathVariable Long userId, @RequestBody List<CustomerOrderItem> items) {
        return ResponseEntity.ok(orderService.createOrder(userId, items));
    }

    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
