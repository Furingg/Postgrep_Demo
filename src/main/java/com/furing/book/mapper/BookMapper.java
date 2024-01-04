package com.furing.book.mapper;

import com.furing.book.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author furing
 */
@Mapper
public interface BookMapper {

    List<Book> getAllBooks();

    void addBook(Book book);

    Book getBook(@Param("bookId") Integer bookId);

    void updateBook(@Param("bookId") Integer bookId, @Param("amount") Integer amount);
}
