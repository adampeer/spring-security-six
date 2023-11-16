package com.springboot.springsecuritysix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello World! This page can be accessed by anyone without the need to login.");
  }

  @GetMapping("/user")
  public ResponseEntity<String> user() {
    return ResponseEntity.ok(
        "This page can only be accessed by users who have been authenticated and have the role of either 'USER' or 'ADMIN'. <a href=\"/auth/logout\">LOGOUT</a>");
  }

  @GetMapping("/admin")
  public ResponseEntity<String> admin() {
    return ResponseEntity
        .ok("This page can only be accessed by users who have been authenticated and have the role of 'ADMIN'. <a href=\"/auth/logout\">LOGOUT</a>");
  }

}