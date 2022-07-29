package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.security.AccountCredentialsDTO;
import com.ratz.restfullapi.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication endpoint")
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthServiceImpl authService;


  @SuppressWarnings("rawtypes")
  @Operation(summary = "Authenticates a user and return the token")
  @PostMapping("/signIn")
  public ResponseEntity signIn(@RequestBody AccountCredentialsDTO data) {

    if (isDataValid(data))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

    ResponseEntity token = authService.singIn(data);

    if (token == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");
    }

    return token;
  }


  private boolean isDataValid(AccountCredentialsDTO data) {
    return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword().isBlank();
  }
}
