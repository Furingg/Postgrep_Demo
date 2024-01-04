package com.furing.book.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author furing
 */
@Data
@Accessors(chain = true)
public class Order {

    private Integer orderId;

    private Integer bookId;

    private Integer userId;

    private Float money;

    private String status;

    private Integer amount;
}
