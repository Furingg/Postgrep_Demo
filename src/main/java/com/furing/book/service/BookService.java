package com.furing.book.service;

import com.furing.book.pojo.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    void addBook(Book book);

    Book getBook(Integer bookId);

    void updateBook(Integer bookId, Integer amount);
}
