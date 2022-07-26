package com.ratz.restfullapi.integration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratz.restfullapi.config.TestConfigs;
import com.ratz.restfullapi.integration.AbstractIntegrationTest;
import com.ratz.restfullapi.integration.dto.PersonDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

  private static RequestSpecification specification;
  private static ObjectMapper objectMapper;


  @BeforeAll
  public static void setup() {
    objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


  }

  private static PersonDTO makePersonDTO() {
    PersonDTO person = new PersonDTO();
    person.setId(1l);
    person.setFirstName("Richard");
    person.setLastName("Stallman");
    person.setAddress("New York City, New York, US");
    person.setGender("Male");

    return person;
  }

  @Test
  @Order(1)
  public void testCreate() throws JsonMappingException, JsonProcessingException {

    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();

    PersonDTO personDTO = makePersonDTO();
    String content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_JSON)
        .body(personDTO)
        .when()
        .post()
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();

    PersonDTO persistedPerson = makePersonDTO();

    assertNotNull(persistedPerson);

    assertNotNull(persistedPerson.getId());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());

    assertTrue(persistedPerson.getId() > 0);

    assertEquals("Richard", persistedPerson.getFirstName());
    assertEquals("Stallman", persistedPerson.getLastName());
    assertEquals("New York City, New York, US", persistedPerson.getAddress());
    assertEquals("Male", persistedPerson.getGender());
  }

  @Test
  @Order(2)
  public void testCreateWithWrongOrigin() throws JsonMappingException, JsonProcessingException {


    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();

    String content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_JSON)
        .body(makePersonDTO())
        .when()
        .post()
        .then()
        .statusCode(403)
        .extract()
        .body()
        .asString();

    assertNotNull(content);
    assertEquals("Invalid CORS request", content);
  }

  @Test
  @Order(3)
  public void testFindById() throws JsonMappingException, JsonProcessingException {

    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();

    String content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_JSON)
        .pathParam("id", makePersonDTO().getId())
        .when()
        .get("{id}")
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();

    PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
    persistedPerson = makePersonDTO();

    assertNotNull(persistedPerson);

    assertNotNull(persistedPerson.getId());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());

    assertTrue(persistedPerson.getId() > 0);

    assertEquals("Richard", persistedPerson.getFirstName());
    assertEquals("Stallman", persistedPerson.getLastName());
    assertEquals("New York City, New York, US", persistedPerson.getAddress());
    assertEquals("Male", persistedPerson.getGender());
  }


  @Test
  @Order(4)
  public void testFindByIdWithWrongOrigin() {

    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();

    String content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_JSON)
        .pathParam("id", makePersonDTO().getId())
        .when()
        .get("{id}")
        .then()
        .statusCode(403)
        .extract()
        .body()
        .asString();


    assertNotNull(content);
    assertEquals("Invalid CORS request", content);
  }
}
