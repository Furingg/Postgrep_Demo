package com.furing.book.controller;

import com.furing.book.pojo.Order;
import com.furing.book.service.BookService;
import com.furing.book.service.OrderService;
import com.furing.book.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author furing
 */
@Controller
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private BookService bookService;

    @Resource
    private UserService userService;

    public void getOwnOrder(Integer userId) {
        List<Order> orders = orderService.getOwnOrders(userId);
        Scanner sc = new Scanner(System.in);
        if (orders.isEmpty()) {
            System.out.println("对不起，您当前没有订单");
            return;
        }
        System.out.println("<-------------订单列表如下-------------->");
        for (Order o : orders) {
            System.out.println("<---订单id: " + o.getOrderId() + " --->");
            System.out.println("<---订单状态: " + o.getStatus() + " --->");
            System.out.println("<---交易金额: " + o.getMoney() + " --->");
            System.out.println("<---交易数量: " + o.getAmount() + " --->");
            System.out.println("<---图书名字: " + bookService.getBook(o.getBookId()).getName() + "--->");
        }
        try {
            System.out.println("<------------------------------------>\n");
            System.out.println("<---------------是否退单---------------->");
            System.out.println("<------ 1. 是             2. 不是 ------>");
            int button = sc.nextInt();
            if (button == 1) {
                System.out.println("<---请输入你要退单的id（未发货才能退单）: ");
                int oid = sc.nextInt();
                Order order = orderService.getOrder(oid);
                if (order == null) {
                    System.out.println("<---订单不存在: ");
                } else if (userId.equals(order.getOrderId())) {
                    System.out.println("<---不是您的订单: ");
                } else if (!"未发货".equals(order.getStatus())) {
                    System.out.println("<---订单无法退单: ");
                } else {
                    orderService.updateOrder(order.getOrderId(), "已退款");
                    userService.updateUser(userId, order.getMoney());
                    bookService.updateBook(order.getBookId(), order.getAmount());
                    System.out.println("<---------------退单成功---------------->");
                }
            }
        }catch (InputMismatchException e){
            System.out.println("输入异常，请重新输入");
            getOwnOrder(userId);
        }
    }

    public void getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        Scanner sc = new Scanner(System.in);
        if (orders.isEmpty()) {
            System.out.println("对不起，当前没有订单");
            return;
        }
        System.out.println("<-------------订单列表如下-------------->");
        for (Order o : orders) {
            System.out.println("<---订单id: " + o.getOrderId() + " --->");
            System.out.println("<---订单状态: " + o.getStatus() + " --->");
            System.out.println("<---交易金额: " + o.getMoney() + " --->");
            System.out.println("<---交易数量: " + o.getAmount() + " --->");
            System.out.println("<---图书名字: " + bookService.getBook(o.getBookId()).getName() + "--->");
            System.out.println("<---购买者id: " + o.getUserId() + "--->");
        }
        System.out.println("<------------------------------------>\n");
    }
}
