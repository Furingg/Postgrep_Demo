package com.furing.book.controller;

import com.furing.book.pojo.Book;
import com.furing.book.pojo.Order;
import com.furing.book.pojo.User;
import com.furing.book.service.BookService;
import com.furing.book.service.OrderService;
import com.furing.book.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author furing
 */
@Controller
public class BookController {


    @Resource
    private BookService bookService;

    @Resource
    private UserService userService;

    @Resource
    private OrderService orderService;

    public void getAllBooksAdministrator() {
        // 过滤数量
        booksShow(bookService.getAllBooks());
    }

    public void getAllBooks(Integer userId) {
        // 过滤数量
        List<Book> allBooks = bookService.getAllBooks().stream().filter(book -> book.getAmount() > 0).collect(Collectors.toList());
        booksShow(allBooks);
        if (allBooks.isEmpty()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("<------ 1. 买书         2. 返回 ------>");
        while (true) {
            int option = sc.nextInt();
            if (option == 1) {
                System.out.println("<--请输入购买的书的id：                 ------------>");
                int bid = sc.nextInt();
                Book book = bookService.getBook(bid);
                if (book == null || book.getAmount() <= 0) {
                    System.out.println("该书不存在");
                    break;
                }
                System.out.println("<--请输入购买的数量：                   ------------>");
                int amount = sc.nextInt();
                float sum = amount * book.getValue();
                User user = userService.getUser(userId);
                if (user.getBalance() < sum) {
                    System.out.println("余额不足");
                    break;
                } else if (book.getAmount() < amount) {
                    System.out.println("库存不足");
                    break;
                } else {
                    userService.updateUser(userId, sum * -1);
                    Order order = new Order();
                    order.setAmount(amount).setBookId(bid).setUserId(userId).setMoney(sum).setStatus("未发货");
                    orderService.insertOrder(order);
                    bookService.updateBook(bid, amount * -1);
                    System.out.println("<-----------------下单成功--------------->");
                    break;
                }
            } else if (option == 2) {
                break;
            } else {
                System.out.println("对不起，输入错误");
            }
        }
    }

    public void booksShow(List<Book> books) {
        Scanner sc = new Scanner(System.in);
        if (books.isEmpty()) {
            System.out.println("对不起，当前没有书本");
            return;
        }
        System.out.println("<-------------订单列表如下-------------->");
        for (Book b : books) {
            System.out.println("<---id: " + b.getBookId() + " --->");
            System.out.println("<---名字: " + b.getName() + " --->");
            System.out.println("<---类型: " + b.getType() + " --->");
            System.out.println("<---作者: " + b.getWriter() + " --->");
            System.out.println("<---评分: " + b.getScore() + "--->");
            System.out.println("<---价格: " + b.getValue() + "--->");
            System.out.println("<---数量: " + b.getAmount() + "--->\n");
        }
        System.out.println("<------------------------------------>\n");
    }

    public void addBooks() {
        List<Book> books = bookService.getAllBooks();
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        try {
            System.out.println("<------------------添加书籍------------------>");
            System.out.println("<-------- 1. 新书            2. 补货 -------->");
            while (true) {
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("<------------------添加书籍------------------>");
                    sc.nextLine();
                    System.out.println("<--请输入书名：                   ------------>");
                    String bookName = sc.nextLine();
                    book.setName(bookName);
                    System.out.println("<--请输入作者：                   ------------>");
                    String bookWriter = sc.nextLine();
                    book.setWriter(bookWriter);
                    System.out.println("<--请输入类型：                   ------------>");
                    String bookType = sc.nextLine();
                    book.setType(bookType);
                    System.out.println("<--请输入评分：                   ------------>");
                    String bookScore = sc.nextLine();
                    book.setScore(bookScore);
                    System.out.println("<--请输入价格：                   ------------>");
                    float bookValue = sc.nextFloat();
                    book.setValue(bookValue);
                    System.out.println("<--请输入数量：                   ------------>");
                    int amount = sc.nextInt();
                    book.setAmount(amount);
                    bookService.addBook(book);
                    System.out.println("<----------------添加书籍成功------------------>\n");
                    break;
                } else if (choice == 2) {
                    System.out.println("<------------------添加书籍------------------>");
                    System.out.println("<--请输入订单：                   ------------>");
                    int bid = sc.nextInt();
                    if (bookService.getBook(bid) == null) {
                        System.out.println("<-单号错误->");
                    } else {
                        System.out.println("<--请输入货量：                   ------------>");
                        int amount = sc.nextInt();
                        bookService.updateBook(bid, amount);
                        System.out.println("<----------------添加书籍成功------------------>\n");
                    }
                    break;
                } else {
                    System.out.println("对不起，输入错误");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("输入异常数据！请重新输入！");
            addBooks();
        }

    }
}
