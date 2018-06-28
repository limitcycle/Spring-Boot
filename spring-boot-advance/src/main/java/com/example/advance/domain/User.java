package com.example.advance.domain;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends EntityBase<User> {

  private String username;
  private String password;
  private String phone;
  private String email;

  @Override
  public String toString() {
    return "User{" +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", phone=" + phone +
        ", email='" + email + '\'' +
        '}';
  }
}
