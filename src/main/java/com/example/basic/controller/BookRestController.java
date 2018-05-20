package com.example.basic.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.domain.Book;
import com.example.basic.service.BookService;

@RestController
@RequestMapping("api/v1")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	/**
	 * 獲取所有書單
	 * 
	 * @return
	 */
	@GetMapping("books")
	public List<Book> getAll() {
		return bookService.getAll();
	}
	
	/**
	 * 利用id查詢書單
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("books/{id}")
	public Book getOne(@PathVariable long id) {
		return bookService.getOne(id);
	}
	
	/**
	 * 新增一個書單
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping("books")
	public Book addOne(Book book) {
		return bookService.save(book);
	}
	
	/**
	 * 修改一個書單
	 * 
	 * @param book
	 * @return
	 */
	@PutMapping("books")
	public Book modifyBook(@RequestParam long id,
						   @RequestParam String name,
						   @RequestParam String author,
						   @RequestParam String description,
						   @RequestParam int status) {
		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setStatus(status);
		
		return bookService.save(book);
	}
	
	/**
	 * 刪除一個書單
	 * @param id
	 */
	@DeleteMapping("books/{id}")
	public void deleteBook(@PathVariable long id) {
		bookService.deleteOne(id);
	}
	
	@PostMapping("books/byAuthor")
	public List<Book> findByAuthor(@RequestParam String author) {
		return bookService.findByAuthor(author);
	}
	
	@PostMapping("books/byAuthorAndStatus")
	public List<Book> findByAuthorAndStatus(@RequestParam String author, @RequestParam int status) {
		return bookService.findByAuthorAndStatus(author, status);
	}
	
	@PostMapping("books/byDescription")
	public List<Book> findByDescription(@RequestParam String description) {
		return bookService.findByDescription(description);
	}
	
	@PostMapping("books/byJPQL")
	public List<Book> findByJPQL(@RequestParam int len) {
		return bookService.findByJPQL(len);
	}
	
	@PostMapping("books/byNativeQuery")
	public List<Book> findByNativeQuery(@RequestParam int len) {
		return bookService.findByNativeQuery(len);
	}
	
	@PostMapping("books/updateByJPQL")
	public int updateByJPQL(@RequestParam int status, @RequestParam long id) {
		return bookService.updateByJPQL(status, id);
	}
	
	@PostMapping("books/deleteByJPQL")
	public int deleteByJPQL(@RequestParam long id) {
		return bookService.deleteByJPQL(id);
	}
	
	@PostMapping("books/deleteAndUpdate")
	public int deleteAndUpdate(@RequestParam long id, @RequestParam int status, @RequestParam long uid) {
		return bookService.deleteAndUpdate(id, status, uid);
	}
}
