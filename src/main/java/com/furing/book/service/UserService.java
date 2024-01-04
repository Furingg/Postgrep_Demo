package com.furing.book.service;

import com.furing.book.pojo.User;

public interface UserService {


    String register(User user);

    void updateAccount(Integer userId, String account);

    User login(String account, String password);

    User loginAdministrator(String password);

    void updateUser(Integer userId, Float money);

    User getUser(Integer userId);
}
