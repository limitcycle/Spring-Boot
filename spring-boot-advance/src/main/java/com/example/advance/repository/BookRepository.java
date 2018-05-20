package com.example.advance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advance.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
