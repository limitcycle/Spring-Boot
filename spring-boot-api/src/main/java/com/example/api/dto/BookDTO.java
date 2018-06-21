package com.example.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.example.api.domain.Book;
import com.example.api.util.CustomBeanUtils;

@Data
public class BookDTO {

  @NotBlank
  private String author;
  @Length(max = 20)
  private String description;
  @NotBlank
  private String name;
  @NotNull
  private Integer status;


  /**
   * 轉換傳輸對象
   */
  public void convertToBook(Book book) {
    new BookConvert().convert(this, book);
  }

  public Book convertToBook() {
    return new BookConvert().convert(this);
  }

  private class BookConvert implements Convert<BookDTO, Book> {

    public Book convert(BookDTO bookDTO, Book book) {
      String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(bookDTO);
      BeanUtils.copyProperties(bookDTO, book, nullPropertyNames);
      return book;
    }

    public Book convert(BookDTO bookDTO) {
      Book book = new Book();
      BeanUtils.copyProperties(bookDTO, book);
      return book;
    }
  }
}
