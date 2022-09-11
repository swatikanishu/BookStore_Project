package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.OrderDto;
import com.example.bookstore_project.model.Order;

import java.util.List;

public interface IOrderService {
    String addOrder(OrderDto orderDto);

    List<Order> findAll();

    Order FindById(Long id);

    String deleteById(Long id);



    String updateOrderData(Long id, OrderDto orderDto);
}
