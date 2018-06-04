package com.example.advance.com.example.advance.controller;

import com.example.advance.controller.LoginController;
import com.example.advance.domain.User;
import com.example.advance.form.UserForm;
import com.example.advance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testIndexPage() throws Exception {
        this.mockMvc.perform(get("/").sessionAttr("user", new User()))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testRegisterPage() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(view().name("register"));
    }

    @Test
    public void testLoginPostTest() throws Exception {
        // given
        String username = "username";
        String password = "password";
        // when
        when(userRepository.findByUsernameAndPassword(username, password))
                .thenReturn(Optional.of(new User()));
        // then
        this.mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", username)
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testLogout() throws Exception {
        this.mockMvc.perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testsRegitsterMethod() throws Exception {
        // given
        String username = "username";
        String password = "111111";
        String email = "test@gmail.com";
        String phone = "0912345678";
        UserForm userForm = new UserForm();
        userForm.setUsername(username);
        userForm.setPassword(password);
        userForm.setConfirmPassword(password);
        userForm.setEmail(email);
        userForm.setPhone(phone);

        User user = userForm.convertToUser();
        // when
        when(userRepository.save(user)).thenReturn(user);
        // then
        this.mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", username)
                .param("password", password)
                .param("confirmPassword", password)
                .param("email", email)
                .param("phone", phone))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"))
                .andExpect(view().name("redirect:/login"))
                .andExpect(model().errorCount(0));

    }
}
