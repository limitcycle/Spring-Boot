package com.example.advance.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.example.advance.domain.User;

@Data
public class UserForm {

  private final String PHONE_REG = "(09)+[\\d]{8}";

  @NotBlank(message = "請填寫用戶名稱")
  private String username;
  @Length(min = 6, message = "密碼最少需要6位數")
  private String password;
  @Pattern(regexp = PHONE_REG, message = "請輸入正確手機號碼")
  private String phone;
  @Email(message = "請輸入正確電子信箱格式")
  private String email;
  @NotBlank(message = "請填寫確認密碼")
  private String confirmPassword;

  public boolean confirmPassword() {
    if (this.password.equals(this.confirmPassword)) {
      return true;
    }
    return false;
  }

  public User convertToUser() {
    User user = new UserFormConvert().convert(this);
    return user;
  }

  private class UserFormConvert implements FormConvert<UserForm, User> {

    @Override
    public User convert(UserForm userForm) {
      User user = new User();
      BeanUtils.copyProperties(userForm, user);
      return user;
    }

  }
}
