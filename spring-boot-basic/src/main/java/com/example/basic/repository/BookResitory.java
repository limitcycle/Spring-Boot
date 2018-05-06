package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.domain.Book;

public interface BookResitory extends JpaRepository<Book, Long>{
	
}
