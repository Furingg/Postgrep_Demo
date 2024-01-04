package com.furing.book.mapper;

import com.furing.book.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author furing
 */
@Mapper
public interface OrderMapper {
    
    List<Order> getAllOrders();

    List<Order> getOwnOrders(Integer userId);

    Order getOrder(Integer oid);

    void updateOrder(@Param("orderId") Integer orderId, @Param("status") String status);

    void insertOrder(Order order);
}
