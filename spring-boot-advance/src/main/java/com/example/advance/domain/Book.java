package com.example.advance.domain;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Book extends EntityBase<Book> {

  private String author;
  private String description;
  private String name;
  private Integer status;

  @Override
  public String toString() {
    return "Book author=" + author + ", description=" + description + ", name="
        + name + ", status="
        + status + "]";
  }
}
