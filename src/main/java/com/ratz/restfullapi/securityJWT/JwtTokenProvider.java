package com.ratz.restfullapi.securityJWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ratz.restfullapi.DTO.v1.security.TokenDTO;
import com.ratz.restfullapi.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

  Algorithm algorithm = null;

  @Value("${security.jwt.token.secret-key:secret}")
  private String secretKey = "secret";

  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMilliseconds = 3600000;


  @Autowired
  private UserDetailsService userDetailsService;


  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public TokenDTO createAccessToken(String username, List<String> roles) {

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    String accessToken = getAccessToken(username, roles, now, validity);
    String refreshToken = getRefreshToken(username, roles, validity);

    return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
  }

  private String getRefreshToken(String username, List<String> roles, Date now) {

    Date validityRefreshToken = new Date(now.getTime() + validityInMilliseconds * 3);


    return JWT.create()
        .withClaim("roles", roles)
        .withIssuedAt(now)
        .withExpiresAt(validityRefreshToken)
        .withSubject(username)
        .sign(algorithm)
        .strip();
  }

  private String getAccessToken(String username, List<String> roles, Date now, Date valid) {

    String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

    return JWT.create()
        .withClaim("roles", roles)
        .withIssuedAt(now)
        .withExpiresAt(valid)
        .withSubject(username)
        .withIssuer(issuerUrl)
        .sign(algorithm)
        .strip();
  }

  public Authentication getAuthentication(String token) {

    DecodedJWT decodedJWT = decodedToken(token);
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());

    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  private DecodedJWT decodedToken(String token) {

    Algorithm alg = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));

    JWTVerifier verifier = JWT.require(alg).build();
    DecodedJWT decodedJWT = verifier.verify(token);

    return decodedJWT;
  }

  public String resolveToken(HttpServletRequest request) {

    String bearerToken = request.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer")) {
      return bearerToken.substring("Bearer ".length());
    }

    return null;
  }

  public boolean validateToken(String token) {

    DecodedJWT decodedJWT = decodedToken(token);

    try {

      if (decodedJWT.getExpiresAt().before(new Date())) {

        return false;
      }
      return true;

    } catch (Exception e) {
      throw new InvalidJwtAuthenticationException("Expired or invalid JWT token.");
    }
  }
}
