package com.furing.book.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author furing
 */
@Data
@Accessors(chain = true)
public class Book {

    private Integer bookId;

    private String writer;

    private String type;

    private Float value;

    private String name;

    private Integer amount;

    private String score;
}
