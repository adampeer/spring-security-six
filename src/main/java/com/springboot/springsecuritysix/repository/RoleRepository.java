package com.springboot.springsecuritysix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springsecuritysix.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByAuthority(String authority);

}
