package com.furing.book.service.impl;

import com.furing.book.mapper.BookMapper;
import com.furing.book.pojo.Book;
import com.furing.book.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author furing
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public Book getBook(Integer bookId) {
        return bookMapper.getBook(bookId);
    }

    @Override
    public void updateBook(Integer bookId, Integer amount) {
        bookMapper.updateBook(bookId, amount + bookMapper.getBook(bookId).getAmount());
    }
}
