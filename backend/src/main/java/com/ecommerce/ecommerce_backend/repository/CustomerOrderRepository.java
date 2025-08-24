package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
