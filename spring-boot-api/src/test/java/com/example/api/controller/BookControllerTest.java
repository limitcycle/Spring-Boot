package com.example.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.api.domain.vo.Author;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.api.domain.Book;
import com.example.api.dto.BookDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @Test
  public void givenBook_whenGetBooks_thenReturnJson() throws Exception {
    // given
    Book book1 = new Book();
    book1.setId(1);
    Author author = new Author();
    author.setName("author1");
    book1.setAuthor(author);
    book1.setDescription("description1");
    book1.setName("name1");
    book1.setStatus(0);
    List<Book> books = Arrays.asList(book1);
    // when
    when(bookService.findAllBooks()).thenReturn(books);
    // then
    mockMvc.perform(get("/api/v1/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", is(book1.getName())));
  }

  @Test
  public void givenNoBook_whenGetBooks_thenThrowsNotFoundException() throws Exception {
    // given
    List<Book> books = new ArrayList<>();
    // when
    when(bookService.findAllBooks()).thenReturn(books);
    // then
    mockMvc.perform(get("/api/v1/books"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenBook_whenGetBook_thenReturnJson() throws Exception {
    // given
    Author author = new Author();
    author.setName("author");
    long id = 1;
    Book book = new Book();
    book.setId(id);
    book.setAuthor(author);
    book.setDescription("description");
    book.setName("name");
    book.setStatus(2);
    // when
    when(bookService.getBookById(id)).thenReturn(book);
    // then
    mockMvc.perform(get("/api/v1/books/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.author", is(book.getAuthor())));
  }

  @Test
  public void givenNoBook_whenGetBook_thenThrowNoFoundException() throws Exception {
    // given
    long id = 1;
    // when
    when(bookService.getBookById(id)).thenThrow(new NotFoundException(""));
    // then
    mockMvc.perform(get("/api/v1/books/" + id))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenBook_whenSaveBook_thenReturnJson() throws Exception {
    // given
    // DB的seq要怎處理?? (id)
    BookDTO bookDTO = new BookDTO();
    Author author = new Author();
    author.setName("author");
    bookDTO.setAuthor(author);
    bookDTO.setDescription("description");
    bookDTO.setName("name");
    bookDTO.setStatus(1);

    Book book = bookDTO.convertToBook();
    book.setId(1L);
    ObjectMapper mapper = new ObjectMapper();
    String bookAsString = mapper.writeValueAsString(book);

    // when
    when(bookService.saveBook(any(Book.class))).thenReturn(book);

    // then
    this.mockMvc.perform(post("/api/v1/books")
        .contentType(MediaType.APPLICATION_JSON).content(bookAsString))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.author", is(book.getAuthor())));
  }

  @Test
  public void givenBook_whenUpdateBook_thenReturnJson() throws Exception {
    // given
    long id = 1;
    BookDTO bookDTO = new BookDTO();
    Author author = new Author();
    author.setName("author");
    bookDTO.setAuthor(author);
    bookDTO.setDescription("description");
    bookDTO.setName("name");
    bookDTO.setStatus(1);

    author = new Author();
    author.setName("author");
    Book book = new Book();
    book.setId(id);
    book.setAuthor(author);
    book.setDescription("description");
    book.setName("name");
    book.setStatus(1);
//		Book book = bookDTO.convertToBook();

    ObjectMapper mapper = new ObjectMapper();
    String bookAsString = mapper.writeValueAsString(bookDTO);

    // when
    when(bookService.updateBook(anyLong(), any(BookDTO.class))).thenReturn(book);

    // then
    this.mockMvc.perform(put("/api/v1/books/" + id)
        .contentType(MediaType.APPLICATION_JSON).content(bookAsString))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.author", is(book.getAuthor())));
  }

  @Test
  public void givenId_whenDeleteBook_thenReturnHttpStatus() throws Exception {
    // given
    long id = 1;
    // then
    this.mockMvc.perform(delete("/api/v1/books/" + id))
        .andExpect(status().isNoContent());
  }

  @Test
  public void deleteAllBooks() throws Exception {
    this.mockMvc.perform(delete("/api/v1/books"))
        .andExpect(status().isNoContent());
  }
}
