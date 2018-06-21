package com.example.api.service;

import java.util.List;

import com.example.api.domain.Book;
import com.example.api.dto.BookDTO;

public interface BookService {

  List<Book> findAllBooks();

  Book getBookById(long id);

  Book saveBook(Book book);

  Book updateBook(long id, BookDTO bookDTO);

  void deleteBookById(long id);

  void deleteAllBooks();
}
