package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.model.CustomerOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderItemRepository extends JpaRepository<CustomerOrderItem, Long> {
}
