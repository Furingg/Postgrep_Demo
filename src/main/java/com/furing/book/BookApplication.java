package com.furing.book;

import com.furing.book.controller.UserController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author furing
 */
@SpringBootApplication
@MapperScan("com.furing.book.mapper")
public class BookApplication implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UserController userController = applicationContext.getBean(UserController.class);
        userController.view();
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
