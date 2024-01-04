package com.furing.book.service.impl;

import com.furing.book.mapper.UserMapper;
import com.furing.book.pojo.User;
import com.furing.book.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author furing
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String register(User user) {
        if (userMapper.register(user) != 1) {
            return "0";
        }
        List<User> users = userMapper.getAllUsers();
        // 得到所有账号的集合
        List<String> accounts = users.stream().map(User::getAccount).collect(Collectors.toList());
        return "" + (accounts.size() + 10000);
    }

    @Override
    public void updateAccount(Integer userId, String account) {
        userMapper.updateAccount(userId, account);
    }

    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }

    @Override
    public User loginAdministrator(String password) {
        return userMapper.login("10001", password);
    }

    @Override
    public void updateUser(Integer userId, Float money) {
        Float balance = getUser(userId).getBalance();
        userMapper.updateUser(userId, money + balance);
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.getUser(userId);
    }
}
