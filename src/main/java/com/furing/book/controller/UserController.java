package com.furing.book.controller;

import com.furing.book.pojo.User;
import com.furing.book.service.BookService;
import com.furing.book.service.OrderService;
import com.furing.book.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author furing
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private OrderController orderController;

    @Resource
    private BookController bookController;

    public void view() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("<-------------欢迎使用网上购书软件------------->");
            System.out.println("<-------------  1. 管理员登录 --------------->");
            System.out.println("<-------------  2. 用户登录  --------------->");
            System.out.println("<-------------  3. 用户注册  --------------->");
            System.out.println("<-------------  4. 退出     --------------->");
            System.out.println("<--请输入您的选择:            --------------->");
            try {
                int optional = sc.nextInt();
                if (optional == 1) {
                    User user = loginViewAdministrator();
                    if (user != null) {
                        System.out.println("<-----------------登录成功----------------->\n");
                        informationViewAdministrator(user);
                    } else {
                        System.out.println("密码错误！");
                    }
                } else if (optional == 2) {
                    User user = loginView();
                    if (user != null) {
                        System.out.println("<-----------------登录成功----------------->\n");
                        informationView(user);
                    } else {
                        System.out.println("该用户不存在！请检查账号密码是否输入正确！");
                    }
                } else if (optional == 3) {
                    System.out.println("注册成功！您的账号为: " + registerView() + " ,请牢记！\n");
                } else if (optional == 4) {
                    break;
                } else {
                    System.out.println("输入错误，请重新输入！");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("输入异常数据！请重新输入！");
                view();
            }
        }
    }

    private void informationViewAdministrator(User user) {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("<--------------欢迎管理员登录系统-------------->");
                System.out.println("<--------  1. 查看图书  2. 增加图书   -------->");
                System.out.println("<--------  3. 查看订单  4. 返回      -------->");
                System.out.println("<--请输入您的选择:               ------------->");

                int optional = sc.nextInt();
                if (optional == 1) {
                    bookController.getAllBooksAdministrator();
                } else if (optional == 2) {
                    bookController.addBooks();
                } else if (optional == 3) {
                    orderController.getAllOrders();
                } else if (optional == 4) {
                    break;
                } else {
                    System.out.println("输入异常，请重新输入");
                }
            }
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("输入异常数据！请重新输入！");
            informationView(user);
        }
    }

    private User loginViewAdministrator() {
        Scanner sc = new Scanner(System.in);
        String password = null;
        try {
            System.out.println("<---------------欢迎登录--------------->");
            System.out.println("<--请输入您的密码:            ----------->");
            password = sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("输入异常数据！请重新输入！");
            loginView();
        }
        return userService.loginAdministrator(password);
    }

    private void informationView(User user) {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("<-------------欢迎使用网上购书软件------------->");
                System.out.println("<--------  1. 查看图书  2. 个人信息   -------->");
                System.out.println("<--------  3. 查看订单  4. 返回      -------->");
                System.out.println("<--请输入您的选择:               ------------->");
                int optional = sc.nextInt();
                if (optional == 1) {
                    bookController.getAllBooks(user.getUserId() );
                } else if (optional == 2) {
                    getOwnInformation(user.getUserId());
                } else if (optional == 3) {
                    orderController.getOwnOrder(user.getUserId());
                } else if (optional == 4) {
                    break;
                } else {
                    System.out.println("输入异常，请重新输入");
                }
            }
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("输入异常数据！请重新输入！");
            informationView(user);
        }
    }

    private void getOwnInformation(Integer userId) {
        User user = userService.getUser(userId);
        System.out.println("<----------您的个人信息如下：---------->");
        System.out.println("<----姓名:     " + user.getUserName());
        System.out.println("<----账号:     " + user.getAccount());
        System.out.println("<----密码:     " + user.getPassword());
        System.out.println("<----余额:     " + user.getBalance());
        System.out.println("<----------------------------------->\n");
    }

    private String registerView() {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        try {
            System.out.println("<---------------欢迎注册信息--------------->");
            System.out.println("<--请输入您的姓名:               ----------->");
            String name = sc.nextLine();
            user.setUserName(name);
            System.out.println("<--请输入您的密码:               ----------->");
            String password = sc.nextLine();
            user.setPassword(password);
            System.out.println("<--充入你的余额:               ----------->");
            Float balance = sc.nextFloat();
            user.setBalance(balance);

        } catch (InputMismatchException e) {
            System.out.println("输入异常数据！请重新输入！");
            registerView();
        }
        String account = userService.register(user);
        System.out.println("<---您的账号是：" + account);
        userService.updateAccount(user.getUserId(), account);
        return account;
    }

    private User loginView() {
        Scanner sc = new Scanner(System.in);
        String account = null;
        String password = null;
        try {
            System.out.println("<---------------欢迎登录--------------->");
            System.out.println("<--请输入您的账号:           ------------>");
            account = sc.nextLine();
            System.out.println("<--请输入您的密码:            ----------->");
            password = sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("输入异常数据！请重新输入！");
            loginView();
        }
        return userService.login(account, password);
    }
}
