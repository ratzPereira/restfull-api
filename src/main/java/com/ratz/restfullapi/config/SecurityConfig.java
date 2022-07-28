package com.ratz.restfullapi.config;

import com.ratz.restfullapi.DTO.v1.security.JwtConfigurer;
import com.ratz.restfullapi.securityJWT.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {

    Map<String, PasswordEncoder> encoders = new HashMap<>();

    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

    DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

    passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

    return passwordEncoder;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests().antMatchers("/auth/signIn", "/auth/refresh", "/api-docs/**", "/swagger-ui.html**").permitAll()
        .antMatchers("/api/**").authenticated()
        .antMatchers("/users").denyAll()
        .and()
        .cors()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }
}
