package com.ecommerce.service;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;

public interface OrderService {
    OrderDto addOrder(Order order, User user);
}
