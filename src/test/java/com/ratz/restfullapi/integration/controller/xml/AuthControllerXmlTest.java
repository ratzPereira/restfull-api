package com.ratz.restfullapi.integration.controller.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ratz.restfullapi.config.TestConfigs;
import com.ratz.restfullapi.integration.AbstractIntegrationTest;
import com.ratz.restfullapi.integration.dto.AccountCredentialsDTO;
import com.ratz.restfullapi.integration.dto.TokenDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerXmlTest extends AbstractIntegrationTest {

  private static TokenDTO tokenDTO;

  @Test
  @Order(0)
  public void signIn() {

    AccountCredentialsDTO user = new AccountCredentialsDTO("Ratz", "admin123");

    tokenDTO = given()
        .basePath("/auth/signIn")
        .port(TestConfigs.SERVER_PORT)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .body(user)
        .when()
        .post()
        .then()
        .statusCode(200)
        .extract().body().as(TokenDTO.class);

    assertNotNull(tokenDTO.getAccessToken());
  }

  @Test
  @Order(1)
  public void refresh() throws JsonMappingException, JsonProcessingException {

    TokenDTO newTokenDTO = given()
        .basePath("/auth/refresh")
        .port(TestConfigs.SERVER_PORT)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .pathParam("username", tokenDTO.getUsername())
        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDTO.getRefreshToken())
        .when()
        .put("{username}")
        .then()
        .statusCode(200)
        .extract().body().as(TokenDTO.class);

    assertNotNull(newTokenDTO.getAccessToken());
    assertNotNull(newTokenDTO.getRefreshToken());
  }
}
