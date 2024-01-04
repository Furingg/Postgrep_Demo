package com.furing.book.service;

import com.furing.book.pojo.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOwnOrders(Integer userId);

    List<Order> getAllOrders();

    Order getOrder(Integer oid);

    void updateOrder(Integer orderId, String status);

    void insertOrder(Order order);
}
