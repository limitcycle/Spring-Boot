package com.example.advance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.advance.domain.Book;
import com.example.advance.exception.BookNotFoundException;
import com.example.advance.repository.BookRepository;

public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book getBookById(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		return book.orElseThrow(() -> new BookNotFoundException("找不到此書單"));
	}

}
