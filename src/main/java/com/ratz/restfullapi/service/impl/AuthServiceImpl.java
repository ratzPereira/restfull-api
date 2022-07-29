package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.DTO.v1.security.AccountCredentialsDTO;
import com.ratz.restfullapi.DTO.v1.security.TokenDTO;
import com.ratz.restfullapi.model.User;
import com.ratz.restfullapi.repository.UserRepository;
import com.ratz.restfullapi.securityJWT.JwtTokenProvider;
import com.ratz.restfullapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Override
  public ResponseEntity singIn(AccountCredentialsDTO data) {

    try {
      String username = data.getUsername();
      String password = data.getPassword();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));

      User user = userRepository.findByUsername(username);

      var tokenResponse = new TokenDTO();
      if (user != null) {

        tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
      } else {

        throw new UsernameNotFoundException("Username " + username + " not found!");
      }

      return ResponseEntity.ok(tokenResponse);

    } catch (Exception e) {

      throw new BadCredentialsException("Invalid username or password!");
    }
  }
}
