package com.example.api.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Author {

  @Column(name="author")
  private String name;

}
