package com.example.api.domain.vo;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Author {

  private String name;

}
