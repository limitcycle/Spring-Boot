package com.example.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.api.domain.vo.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.api.domain.Book;
import com.example.api.dto.BookDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookServiceImpl bookService;

  @Test
  public void testFindAllBooks() {
    // given
    List<Book> books = new ArrayList<>();
    when(bookRepository.findAll()).thenReturn(books);

    // when
    List<Book> actualBook = bookService.findAllBooks();

    // then
    assertEquals(0, actualBook.size());
  }

  @Test(expected = NotFoundException.class)
  public void getBookById_IncorrectId_Throw_Exception() {
    long id = 1;
    Optional<Book> book = Optional.empty();
    when(bookRepository.findById(id)).thenReturn(book);

    bookService.getBookById(id);
  }

  @Test
  public void getBookById_Input_CorrectId_Return_Book() {
    Author author = new Author();
    author.setName("author");
    long id = 1;
    Book book = new Book();
    book.setName("name");

    book.setAuthor(author);
    book.setId(id);
    book.setStatus(0);
    book.setDescription("description");
    Optional<Book> expectedBook = Optional.of(book);
    when(bookRepository.findById(id)).thenReturn(expectedBook);

    Book actualBook = bookService.getBookById(id);

    assertEquals(book, actualBook);
  }

  @Test
  public void saveBook() {
    Author author = new Author();
    author.setName("author");
    Book book = new Book();
    book.setAuthor(author);
    book.setDescription("description");
    book.setName("name");
    book.setStatus(1);
    when(bookRepository.save(book)).thenReturn(book);

    Book actualBook = bookService.saveBook(book);

    assertEquals(book, actualBook);
  }

  public void updateBook() {
    long id = 1;
    BookDTO bookDTO = new BookDTO();
    bookDTO.setAuthor("author");
    bookDTO.setDescription("description");
    bookDTO.setName("name");
    bookDTO.setStatus(2);

    Author author = new Author();
    author.setName("book author");
    Book currentBook = new Book();
    currentBook.setAuthor(author);
    currentBook.setDescription("test description");
    currentBook.setId(id);
    currentBook.setName("test name");
    currentBook.setStatus(1);
    when(bookRepository.findById(id)).thenReturn(Optional.of(currentBook));

    Book expected = new Book();
    expected.setAuthor(author);
    expected.setDescription("description");
    expected.setId(id);
    expected.setName("name");
    expected.setStatus(2);

    when(bookRepository.save(expected)).thenReturn(expected);

    Book actualBook = bookService.updateBook(id, bookDTO);

    assertEquals(expected.getStatus(), actualBook.getStatus());
  }

  @Test
  public void testDeleteBookById() {
    long id = 1;
    bookService.deleteBookById(id);

    verify(bookRepository, atLeastOnce()).deleteById(id);
  }

  @Test
  public void testDeleteAllBook() {
    bookService.deleteAllBooks();

    verify(bookRepository, atLeastOnce()).deleteAll();
  }
}
