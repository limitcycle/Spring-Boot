package com.example.advance.com.example.advance.controller;

import com.example.advance.controller.BookController;
import com.example.advance.domain.Book;
import com.example.advance.domain.User;
import com.example.advance.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void givenId_whenGetBook_thenReturnString() throws  Exception {
        // given
        long id = 1;
        Book book = new Book();
        book.setId(id);
        book.setStatus(2);
        book.setName("name");
        book.setDescription("description");
        book.setAuthor("author");

        //when
        when(bookService.getBookById(id)).thenReturn(book);

        // then
        this.mockMvc.perform(get("/books/{id}", id).sessionAttr("user", new User()))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attribute("book", hasProperty("id", is(id))))
                .andExpect(model().attribute("book", hasProperty("status", is(2))))
                .andExpect(model().attribute("book", hasProperty("name", is("name"))))
                .andExpect(model().attribute("book", hasProperty("description", is("description"))))
                .andExpect(model().attribute("book", hasProperty("author", is("author"))));

        verify(bookService, times(1)).getBookById(id);
        verifyNoMoreInteractions(bookService);
    }
}
