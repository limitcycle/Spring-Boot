package com.example.basic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.basic.domain.Book;

public interface BookResitory extends JpaRepository<Book, Long>{
	
	Page<Book> findAll(Pageable pageable);
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByAuthorAndStatus(String author, int status);
	
	List<Book> findByDescriptionContains(String desc);
	
	@Query("select b from Book b where length(b.name) > ?1")
	List<Book> findByJPQL(int len);
	
	@Query(value = "select * from book where LENGTH(name) > ?1", nativeQuery = true)
	List<Book> findByNativeQuery(int len);
	
	@Modifying
	@Query("update Book b set b.status = ?1 where b.id = ?2")
	int updateByJPQL(int status, long id);
	
	@Modifying
	@Query("delete from Book b where b.id = ?1")
	int deleteByJPQL(long id);
}
