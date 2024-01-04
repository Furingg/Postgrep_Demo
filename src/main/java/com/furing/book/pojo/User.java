package com.furing.book.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author furing
 */
@Data
@Accessors(chain = true)
public class User {

    private Integer userId;

    private String userName;

    private String account;

    private String password;

    private Float balance;

}
