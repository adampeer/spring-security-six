package com.springboot.springsecuritysix.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.springsecuritysix.entity.Role;
import com.springboot.springsecuritysix.entity.User;
import com.springboot.springsecuritysix.service.UserServiceInterface;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

  private UserServiceInterface userServiceInterface;

  public AuthenticationController(UserServiceInterface userServiceInterface) {
    this.userServiceInterface = userServiceInterface;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    List<Role> roles = userServiceInterface.getAllRoles();
    model.addAttribute("roles", roles);
    return "register";
  }

  @PostMapping("/register/save")
  public String registerProcess(@ModelAttribute("user") User user) {

    if (userServiceInterface.findByEmail(user.getEmail()).isPresent()) {
      return "redirect:/auth/register?error";
    } else {
      userServiceInterface.save(user);
      return "redirect:/auth/login?success";
    }

  }

}