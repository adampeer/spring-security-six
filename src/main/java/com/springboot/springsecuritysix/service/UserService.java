package com.springboot.springsecuritysix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.springsecuritysix.entity.Role;
import com.springboot.springsecuritysix.entity.User;
import com.springboot.springsecuritysix.repository.RoleRepository;
import com.springboot.springsecuritysix.repository.UserRepository;
import com.springboot.springsecuritysix.utils.StringUtils;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

  private UserRepository userRepository;

  private RoleRepository roleRepository;

  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(StringUtils.INVALID_USERNAME));
  }

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
