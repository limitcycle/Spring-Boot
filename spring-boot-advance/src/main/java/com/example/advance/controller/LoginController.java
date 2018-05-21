package com.example.advance.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.advance.domain.User;
import com.example.advance.form.UserForm;
import com.example.advance.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 跳轉到登入頁面
	 * @return
	 */
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	/**
	 * 跳轉到註冊頁面
	 * @return
	 */
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}
	
	/**
	 * 登入事件
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public String loginPost(@RequestParam String username,
							@RequestParam String password,
							HttpSession session) {
		Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
		if (user.isPresent()) {
			session.setAttribute("user", user.get());
			return "index";
		}
		return "login";
	}
	
	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	/**
	 * 註冊提交
	 * @param userForm
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/register")
	public String register(@Valid UserForm userForm, BindingResult result, Model model) {
		if (!userForm.confirmPassword()) {
			result.rejectValue("confirmPassword", "confirmError", "兩次密碼不一致");
		}
		if (result.hasErrors()) {
			return "register";
		}
		User user = userForm.convertToUser();
		userRepository.save(user);
		return "redirect:/login";
	}
}
