package com.example.pragraplex.service;

import com.example.pragraplex.entity.Order;
import java.util.List;
import java.util.Optional;
public interface OrderService {
    Order createOrder(int custId, Order order);

    List<Order> findAll();

    Optional<Order> findOneById(Integer id);
    void deleteById(int orderId);
    List<Order> createMultipleOrders(int custId, List<Order> order);
}
