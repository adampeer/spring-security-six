package com.springboot.springsecuritysix.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.springsecuritysix.utils.StringUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authManager(UserDetailsService detailsService) {
    DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
    daoProvider.setUserDetailsService(detailsService);
    daoProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(daoProvider);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/admin/**").hasAuthority(StringUtils.ADMIN);
          auth.requestMatchers("/user/**").hasAnyAuthority(StringUtils.USER, StringUtils.ADMIN);
          auth.requestMatchers("/auth/**").permitAll();
          auth.requestMatchers("/hello/**").permitAll();
          auth.requestMatchers("/js/**").permitAll();
        });
    http
        .formLogin(form -> {
          form.loginPage("/auth/login");
          form.usernameParameter("email");
          form.defaultSuccessUrl("/user");
          form.failureUrl("/auth/login?error");
          form.permitAll();
        });
    http
        .logout(logout -> {
          logout.logoutUrl("/auth/logout");
          logout.logoutSuccessUrl("/auth/login?logout");
        });

    return http.build();
  }

}
