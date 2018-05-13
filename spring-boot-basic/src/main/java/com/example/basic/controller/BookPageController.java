package com.example.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.basic.domain.Book;
import com.example.basic.service.BookService;

@Controller
public class BookPageController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("books")
	public String books(@PageableDefault(size = 5, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {
		Page<Book> page1 = bookService.findAllByPage(pageable);
		model.addAttribute("page", page1);
		return "books";
	}
	
	@GetMapping("books/{id}")
	public String detail(@PathVariable long id, Model model) {
		Book book = bookService.getOne(id);
		model.addAttribute("book", book);
		return "book";
	}
	
	@GetMapping("books/modify")
	public String modifyPage(Model model) {
		model.addAttribute("book", new Book());
		return "modify";
	}
	
	@GetMapping("books/modify/{id}")
	public String modifyEditPage(@PathVariable long id, Model model) {
		Book book = bookService.getOne(id);
		model.addAttribute("book", book);
		return "modify";
	}
	
	@PostMapping("books")
	public String post(Book book, final RedirectAttributes attributes) {
		Book book1 = bookService.save(book);
		if (book1 != null) {
			attributes.addFlashAttribute("message", "《" + book1.getName() + "》信息提交成功");
		}
		return "redirect:/books";
	}
	
	/**
	 * POST ---> redirect ----> GET
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("books/delete/{id}")
	public String delete(@PathVariable long id, final RedirectAttributes attributes) {
		bookService.deleteOne(id);
		attributes.addFlashAttribute("message", "刪除成功");
		return "redirect:/books";
	}
}
