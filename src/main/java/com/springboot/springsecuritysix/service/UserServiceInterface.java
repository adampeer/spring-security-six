package com.springboot.springsecuritysix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.springsecuritysix.entity.Role;
import com.springboot.springsecuritysix.entity.User;

public interface UserServiceInterface {

  // Get user by username (in this case email)
  public UserDetails loadUserByUsername(String email);

  // Save user
  public void save(User user);

  // Get all roles
  public List<Role> getAllRoles();

  // Optional - Get user by email
  public Optional<User> findByEmail(String email);

}