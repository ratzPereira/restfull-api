package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.security.AccountCredentialsDTO;
import com.ratz.restfullapi.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


  @SuppressWarnings("rawtypes")
  @Operation(summary = "Refresh token authenticated user and return the token")
  @PutMapping("/refresh/{username}")
  public ResponseEntity refreshToken(@PathVariable String username, @RequestHeader("Authorization") String refreshToken) {

    if (isDataValid(username, refreshToken))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

    ResponseEntity token = authService.refreshToken(username, refreshToken);

    if (token == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");
    }

    return token;
  }

  private boolean isDataValid(String username, String refreshToken) {
    return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
  }


  private boolean isDataValid(AccountCredentialsDTO data) {
    return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword().isBlank();
  }
}
