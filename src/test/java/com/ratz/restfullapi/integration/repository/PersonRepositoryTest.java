package com.ratz.restfullapi.integration.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ratz.restfullapi.integration.AbstractIntegrationTest;
import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonRepositoryTest extends AbstractIntegrationTest {

  private static Person person;

  @Autowired
  private PersonRepository personRepository;

  @BeforeAll
  public static void setUp() {
    person = new Person();
  }

  @Test
  @Order(1)
  public void testFindPersonsByName() throws JsonMappingException, JsonProcessingException {

    Pageable pageable = PageRequest.of(0, 6, Sort.Direction.ASC, "firstName");

    Person person = personRepository.findPersonsByName("bekk", pageable).getContent().get(0);

    assertNotNull(person.getId());
    assertNotNull(person.getFirstName());
    assertNotNull(person.getLastName());
    assertNotNull(person.getAddress());
    assertNotNull(person.getGender());

    assertEquals(754, person.getId());

    assertEquals("Bekki", person.getFirstName());
    assertEquals("Hollingshead", person.getLastName());
    assertEquals("Seremban", person.getAddress());
    assertEquals("Female", person.getGender());

  }

  @Test
  @Order(2)
  public void testDisablePerson() throws JsonMappingException, JsonProcessingException {

    personRepository.disablePerson(person.getId());

    Pageable pageable = PageRequest.of(0, 6, Sort.Direction.ASC, "firstName");

    Person person = personRepository.findPersonsByName("bekk", pageable).getContent().get(0);

    assertNotNull(person.getId());
    assertNotNull(person.getFirstName());
    assertNotNull(person.getLastName());
    assertNotNull(person.getAddress());
    assertNotNull(person.getGender());

    assertFalse(person.getEnabled());

    assertEquals(754, person.getId());

    assertEquals("Bekki", person.getFirstName());
    assertEquals("Hollingshead", person.getLastName());
    assertEquals("Seremban", person.getAddress());
    assertEquals("Female", person.getGender());

  }
}
