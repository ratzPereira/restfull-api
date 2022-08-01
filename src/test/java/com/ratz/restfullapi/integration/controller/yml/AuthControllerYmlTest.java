package com.ratz.restfullapi.integration.controller.yml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ratz.restfullapi.config.TestConfigs;
import com.ratz.restfullapi.integration.AbstractIntegrationTest;
import com.ratz.restfullapi.integration.controller.yml.mapper.YmlMapper;
import com.ratz.restfullapi.integration.dto.AccountCredentialsDTO;
import com.ratz.restfullapi.integration.dto.TokenDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerYmlTest extends AbstractIntegrationTest {

  private static YmlMapper objectMapper;
  private static TokenDTO tokenVO;

  @BeforeAll
  public static void setup() {
    objectMapper = new YmlMapper();
  }

  @Test
  @Order(1)
  public void testSignin() throws JsonMappingException, JsonProcessingException {

    AccountCredentialsDTO user =
        new AccountCredentialsDTO("Ratz", "admin123");

    RequestSpecification specification = new RequestSpecBuilder()
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();

    tokenVO = given().spec(specification)
        .config(
            RestAssuredConfig
                .config()
                .encoderConfig(EncoderConfig.encoderConfig()
                    .encodeContentTypeAs(
                        TestConfigs.CONTENT_TYPE_YML,
                        ContentType.TEXT)))
        .accept(TestConfigs.CONTENT_TYPE_YML)
        .basePath("/auth/signIn")
        .port(TestConfigs.SERVER_PORT)
        .contentType(TestConfigs.CONTENT_TYPE_YML)
        .body(user, objectMapper)
        .when()
        .post()
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(TokenDTO.class, objectMapper);

    assertNotNull(tokenVO.getAccessToken());
    assertNotNull(tokenVO.getRefreshToken());
  }

  @Test
  @Order(2)
  public void testRefresh() throws JsonMappingException, JsonProcessingException {

    var newTokenVO = given()
        .config(
            RestAssuredConfig
                .config()
                .encoderConfig(EncoderConfig.encoderConfig()
                    .encodeContentTypeAs(
                        TestConfigs.CONTENT_TYPE_YML,
                        ContentType.TEXT)))
        .accept(TestConfigs.CONTENT_TYPE_YML)
        .basePath("/auth/refresh")
        .port(TestConfigs.SERVER_PORT)
        .contentType(TestConfigs.CONTENT_TYPE_YML)
        .pathParam("username", tokenVO.getUsername())
        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenVO.getRefreshToken())
        .when()
        .put("{username}")
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(TokenDTO.class, objectMapper);

    assertNotNull(newTokenVO.getAccessToken());
    assertNotNull(newTokenVO.getRefreshToken());
  }
}
