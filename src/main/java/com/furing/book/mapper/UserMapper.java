package com.furing.book.mapper;

import com.furing.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author furing
 */
@Mapper
public interface UserMapper {

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 返回账号
     */
    Long register(User user);

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 返回用户对象
     */
    User login(@Param("account") String account, @Param("password") String password);

    List<User> getAllUsers();

    void updateAccount(@Param("userId")Integer userId, @Param("account") String account);

    void updateUser(@Param("userId") Integer userId, @Param("balance") Float balance);

    User getUser(Integer userId);
}
