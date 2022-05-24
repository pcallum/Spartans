/*
 * package com.example.spartans;
 * 
 * import org.springframework.context.annotation.Configuration;
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 * 
 * @EnableWebSecurity
 * 
 * @Configuration
 * 
 * @EnableWebMvc
 * public class Config extends WebSecurityConfigurerAdapter {
 * 
 * @Override
 * protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests()
 * .antMatchers("/api/login")
 * .permitAll()
 * .antMatchers("/api/**")
 * .authenticated().and().httpBasic();
 * }
 * }
 */