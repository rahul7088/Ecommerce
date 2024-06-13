package com.ecommerce.serviceImpl;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;
import com.ecommerce.repositary.OrderRepositary;
import com.ecommerce.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepositary orderRepositary;
    private ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepositary orderRepositary,ModelMapper modelMapper){
        this.orderRepositary = orderRepositary;
        this.modelMapper = modelMapper;
    }
    @Override
    public OrderDto addOrder(Order order, User user) {

        order.setCustomer(user);
        order.setCreateBy(user.getName());
        order.setCreateDt(new Date());
        order.setCreateBy(user.getName());
        order.setUpdateDt(new Date());
        order.setTotalPrice(order.getQuantity()*order.getProduct().getPrice());
        Order savedOrder = this.orderRepositary.save(order);
        OrderDto orderDto = modelMapper.map(savedOrder,OrderDto.class);
        return orderDto;
    }
}
