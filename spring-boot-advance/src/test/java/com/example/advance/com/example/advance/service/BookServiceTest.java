package com.example.advance.com.example.advance.service;

import com.example.advance.domain.Book;
import com.example.advance.repository.BookRepository;
import com.example.advance.service.BookService;
import com.example.advance.service.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void givenId_whenGetBookById_thenReturnBook() {
        // given
        long id = 1;
        Book book = new Book();
        book.setAuthor("author");
        book.setDescription("description");
        book.setName("name");
        book.setStatus(2);
        book.setId(id);

        Optional<Book> expectedBook = Optional.of(book);

        // when
        when(bookRepository.findById(id)).thenReturn(expectedBook);
        Book actualBook = bookService.getBookById(id);

        // then
        Assert.assertEquals(book, actualBook);
    }
}
