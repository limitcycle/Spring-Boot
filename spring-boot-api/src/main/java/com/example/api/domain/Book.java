package com.example.api.domain;

import com.example.api.domain.vo.Author;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Book extends EntityBase<Book> {

  @Embedded
  @AttributeOverride(name="name", column=@Column(name="author"))
  private Author author;
  private String description;
  private String name;
  private int status;

}
