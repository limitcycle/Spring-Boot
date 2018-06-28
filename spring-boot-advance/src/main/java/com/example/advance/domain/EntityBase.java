package com.example.advance.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class EntityBase<T extends EntityBase<T>> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
