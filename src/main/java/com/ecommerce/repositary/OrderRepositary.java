package com.ecommerce.repositary;

import com.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositary extends JpaRepository<Order,Long> {
}
