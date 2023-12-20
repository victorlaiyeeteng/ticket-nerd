package com.ticketnerd.orderservice.repository;

import com.ticketnerd.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
