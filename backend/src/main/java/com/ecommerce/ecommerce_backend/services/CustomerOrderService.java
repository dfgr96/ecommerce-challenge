package com.ecommerce.ecommerce_backend.services;

import com.ecommerce.ecommerce_backend.model.*;
import com.ecommerce.ecommerce_backend.repository.CustomerOrderRepository;
import com.ecommerce.ecommerce_backend.repository.InventoryRepository;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {

    private final CustomerOrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public CustomerOrder createOrder(Long userId, List<CustomerOrderItem> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(user);
        order.setCreatedAt(LocalDateTime.now());

        double total = items.stream().mapToDouble(item -> {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            Inventory inventory = inventoryRepository.findByProduct(product)
                    .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

            if (inventory.getQuantityAvailable() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }

            inventory.setQuantityAvailable(inventory.getQuantityAvailable() - item.getQuantity());
            inventoryRepository.save(inventory);

            double itemPrice = product.getPrice() * item.getQuantity();

            item.setPrice(itemPrice);
            item.setOrder(order);
            item.setProduct(product);

            return itemPrice;
        }).sum();

        order.setItems(items);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        CustomerOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
