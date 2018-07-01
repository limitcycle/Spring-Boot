package com.example.api.dto;

import com.example.api.domain.Book;
import com.example.api.domain.vo.Author;
import com.example.api.util.CustomBeanUtils;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

@Data
public class BookDTO {

  @NotNull(groups = {ValidationFirst.class})
  private Author author;

  @Length(max = 20, groups = {ValidationFirst.class})
  private String description;

  @NotBlank(groups = {ValidationFirst.class})
  private String name;

  @PositiveOrZero(groups = {ValidationFirst.class})
  @Min(0)
  @Max(99)
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
