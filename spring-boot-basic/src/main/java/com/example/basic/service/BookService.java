package com.example.basic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.basic.domain.Book;
import com.example.basic.repository.BookResitory;



@Service
public class BookService {
	
	@Autowired
	private BookResitory bookResitory;
	
	/**
	 * 獲取所有書單
	 * 
	 * @return
	 */
	public List<Book> getAll() {
		return bookResitory.findAll();
	}
	
	/**
	 * 分頁查詢書單列表
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<Book> findAllByPage(Pageable pageable) {
		return bookResitory.findAll(pageable);
	}
	/**
	 * 利用id查詢書單
	 * 
	 * @param id
	 * @return
	 */
	public Book getOne(long id) {
		return bookResitory.findById(id).orElse(new Book());
	}
	
	/**
	 * 新增 or 修改一個書單
	 * 
	 * @param book
	 * @return
	 */
	public Book save(Book book) {
		return bookResitory.save(book);
	}
	
	/**
	 * 刪除特定書單by id
	 * 
	 * @param id
	 */
	public void deleteOne(long id) {
		bookResitory.deleteById(id);
	}
	
	/**
	 * 利用作者找尋書單
	 * 
	 * @param author
	 * @return
	 */
	public List<Book> findByAuthor(String author) {
		return bookResitory.findByAuthor(author);
	}
	
	/**
	 * 利用作者＆狀態找尋書單
	 * 
	 * @param author
	 * @param status
	 * @return
	 */
	public List<Book> findByAuthorAndStatus(String author, int status) {
		return bookResitory.findByAuthorAndStatus(author, status);
	}
	
	/**
	 * 利用敘述找尋書單
	 * 
	 * @param description
	 * @return
	 */
	public List<Book> findByDescription(String description) {
		return bookResitory.findByDescriptionContains(description);
	}
	
	/**
	 * 自定義查詢(orm方式查詢)
	 * 
	 * @param len
	 * @return
	 */
	public List<Book> findByJPQL(int len) {
		return bookResitory.findByJPQL(len);
	}
	
	/**
	 * 自定義查詢(原生sql查詢)
	 * 
	 * @param len
	 * @return
	 */
	public List<Book> findByNativeQuery(int len) {
		return bookResitory.findByNativeQuery(len);
	}
	
	/**
	 * 自定義更新
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@Transactional
	public int updateByJPQL(int status, long id) {
		return bookResitory.updateByJPQL(status, id);
	}
	
	/**
	 * 自定義刪除
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public int deleteByJPQL(long id) {
		return bookResitory.deleteByJPQL(id);
	}
	
	/**
	 * 測試事務操作方法
	 * 
	 * @param id
	 * @param status
	 * @param uid
	 * @return
	 */
	@Transactional
	public int deleteAndUpdate(long id, int status, long uid) {
		int dcount = bookResitory.deleteByJPQL(id);
		int ucount = bookResitory.updateByJPQL(status, uid);
		
		return dcount + ucount;
	}
	
	
}
