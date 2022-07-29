package com.ratz.restfullapi.service;

import com.ratz.restfullapi.DTO.v1.security.AccountCredentialsDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  ResponseEntity singIn(AccountCredentialsDTO data);

  ResponseEntity refreshToken(String username, String refreshToken);
}
