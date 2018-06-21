package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Book;
import com.example.api.dto.BookDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public List<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book getBookById(long id) {
    Optional<Book> book = bookRepository.findById(id);
    return book.orElseThrow(() ->
        new NotFoundException(String.format("book by id %s not found", id)));
  }

  @Override
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book updateBook(long id, BookDTO bookDTO) {
    Book book = getBookById(id);
    bookDTO.convertToBook(book);
    return bookRepository.save(book);
  }

  @Override
  public void deleteBookById(long id) {
    bookRepository.deleteById(id);
  }

  @Override
  public void deleteAllBooks() {
    bookRepository.deleteAll();
  }

}
