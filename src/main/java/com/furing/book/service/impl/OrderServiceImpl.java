package com.furing.book.service.impl;

import com.furing.book.mapper.OrderMapper;
import com.furing.book.pojo.Order;
import com.furing.book.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author furing
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOwnOrders(Integer userId) {
        return orderMapper.getOwnOrders(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public Order getOrder(Integer oid) {
        return orderMapper.getOrder(oid);
    }

    @Override
    public void updateOrder(Integer orderId, String status) {
        orderMapper.updateOrder(orderId, status);
    }

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }
}
