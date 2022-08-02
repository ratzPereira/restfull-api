package com.ratz.restfullapi.integration.controller.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ratz.restfullapi.DTO.v1.security.TokenDTO;
import com.ratz.restfullapi.config.TestConfigs;
import com.ratz.restfullapi.integration.AbstractIntegrationTest;
import com.ratz.restfullapi.integration.dto.AccountCredentialsDTO;
import com.ratz.restfullapi.integration.dto.PersonDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerXmlTest extends AbstractIntegrationTest {

  private static RequestSpecification specification;
  private static XmlMapper objectMapper;

  private static PersonDTO person;

  @BeforeAll
  public static void setup() {
    objectMapper = new XmlMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    person = new PersonDTO();
  }

  @Test
  @Order(0)
  public void authorization() throws JsonMappingException, JsonProcessingException {

    AccountCredentialsDTO user = new AccountCredentialsDTO("Ratz", "admin123");

    var accessToken = given()
        .basePath("/auth/signIn")
        .port(TestConfigs.SERVER_PORT)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .body(user)
        .when()
        .post()
        .then()
        .statusCode(200)
        .extract()
        .body()
        .as(TokenDTO.class)
        .getAccessToken();

    specification = new RequestSpecBuilder()
        .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();
  }

  @Test
  @Order(1)
  public void testCreate() throws JsonMappingException, JsonProcessingException {
    mockPerson();

    var content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .body(person)
        .when()
        .post()
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();

    PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
    person = persistedPerson;

    assertNotNull(persistedPerson);

    assertNotNull(persistedPerson.getId());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());

    assertTrue(persistedPerson.getId() > 0);

    //assertEquals("Nelson", persistedPerson.getFirstName());
    //assertEquals("Piquet", persistedPerson.getLastName());
    assertEquals("Brasília - DF - Brasil", persistedPerson.getAddress());
    assertEquals("Male", persistedPerson.getGender());
  }

  @Test
  @Order(2)
  public void testUpdate() throws JsonMappingException, JsonProcessingException {
    person.setLastName("Piquet Souto Maior");

    var content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .body(person)
        .when()
        .post()
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();

    PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
    person = persistedPerson;

    assertNotNull(persistedPerson);

    assertNotNull(persistedPerson.getId());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());

    assertEquals(person.getId(), persistedPerson.getId());

    //assertEquals("Nelson", persistedPerson.getFirstName());
    //assertEquals("Piquet Souto Maior", persistedPerson.getLastName());
    assertEquals("Brasília - DF - Brasil", persistedPerson.getAddress());
    assertEquals("Male", persistedPerson.getGender());
  }

  @Test
  @Order(3)
  public void testFindById() throws JsonMappingException, JsonProcessingException {
    mockPerson();

    var content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .pathParam("id", person.getId())
        .when()
        .get("{id}")
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();

    PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
    person = persistedPerson;

    assertNotNull(persistedPerson);

    assertNotNull(persistedPerson.getId());
    assertNotNull(persistedPerson.getFirstName());
    assertNotNull(persistedPerson.getLastName());
    assertNotNull(persistedPerson.getAddress());
    assertNotNull(persistedPerson.getGender());

    assertEquals(person.getId(), persistedPerson.getId());

    //assertEquals("Nelson", persistedPerson.getFirstName());
    //assertEquals("Piquet Souto Maior", persistedPerson.getLastName());
    assertEquals("Brasília - DF - Brasil", persistedPerson.getAddress());
    assertEquals("Male", persistedPerson.getGender());
  }

  @Test
  @Order(4)
  public void testDelete() throws JsonMappingException, JsonProcessingException {

    given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .pathParam("id", person.getId())
        .when()
        .delete("{id}")
        .then()
        .statusCode(204);
  }

  @Test
  @Order(5)
  public void testFindAll() throws JsonMappingException, JsonProcessingException {

    var content = given().spec(specification)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .when()
        .get()
        .then()
        .statusCode(200)
        .extract()
        .body()
        .asString();

    List<PersonDTO> people = objectMapper.readValue(content, new TypeReference<List<PersonDTO>>() {
    });

    PersonDTO foundPersonOne = people.get(0);

    assertNotNull(foundPersonOne.getId());
    assertNotNull(foundPersonOne.getFirstName());
    assertNotNull(foundPersonOne.getLastName());
    assertNotNull(foundPersonOne.getAddress());
    assertNotNull(foundPersonOne.getGender());

    assertEquals(3, foundPersonOne.getId());

    assertEquals("Joao", foundPersonOne.getFirstName());
    assertEquals("Pereira", foundPersonOne.getLastName());
    assertEquals("Nao sei", foundPersonOne.getAddress());
    assertEquals("Male", foundPersonOne.getGender());

    PersonDTO foundPersonSix = people.get(3);

    assertNotNull(foundPersonSix.getId());
    assertNotNull(foundPersonSix.getFirstName());
    assertNotNull(foundPersonSix.getLastName());
    assertNotNull(foundPersonSix.getAddress());
    assertNotNull(foundPersonSix.getGender());

    assertEquals(6, foundPersonSix.getId());

    assertEquals("Joao", foundPersonOne.getFirstName());
    assertEquals("Pereira", foundPersonOne.getLastName());
    assertEquals("Nao sei", foundPersonOne.getAddress());
    assertEquals("Male", foundPersonOne.getGender());
  }


  @Test
  @Order(6)
  public void testFindAllWithoutToken() throws JsonMappingException, JsonProcessingException {

    RequestSpecification specificationWithoutToken = new RequestSpecBuilder()
        .setBasePath("/api/person/v1")
        .setPort(TestConfigs.SERVER_PORT)
        .addFilter(new RequestLoggingFilter(LogDetail.ALL))
        .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
        .build();

    given().spec(specificationWithoutToken)
        .contentType(TestConfigs.CONTENT_TYPE_XML)
        .accept(TestConfigs.CONTENT_TYPE_XML)
        .when()
        .get()
        .then()
        .statusCode(403);
  }

  private void mockPerson() {
    person.setId(1L);
    person.setFirstName("Nelson");
    person.setLastName("Piquet");
    person.setAddress("Brasília - DF - Brasil");
    person.setGender("Male");
  }
}
