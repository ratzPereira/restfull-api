package com.ratz.restfullapi.unittests.service;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.exceptions.RequiredObjectIsNullException;
import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.repository.PersonRepository;
import com.ratz.restfullapi.service.impl.PersonServiceImpl;
import com.ratz.restfullapi.unittests.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  MockPerson input;

  @Mock
  PersonRepository repository;

  @InjectMocks
  private PersonServiceImpl service;

  @BeforeEach
  void setUpMocks() throws Exception {
    input = new MockPerson();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Person entity = input.mockEntity(1);
    entity.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));

    var result = service.findById(1L);
    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());

    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
  }

  @Test
  void testCreate() {
    Person entity = input.mockEntity(1);
    //entity.setId(1L);

    Person persisted = entity;
    //persisted.setId(1L);

    PersonDTOv1 vo = input.mockDTO(1);
    //vo.setKey(1L);

    when(repository.save(any())).thenReturn(persisted);

    var result = service.createPerson(vo);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());

    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
  }

  @Test
  void testCreateWithNullPerson() {
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
      service.createPerson(null);
    });

    String expectedMessage = "It is not allowed to persist a null object!";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }


  @Test
  void testUpdate() {
    Person entity = input.mockEntity(1);

    Person persisted = entity;
    persisted.setId(1L);

    PersonDTOv1 vo = input.mockDTO(1);
    vo.setKey(1L);


    when(repository.findById(1L)).thenReturn(Optional.of(entity));
    when(repository.save(entity)).thenReturn(persisted);

    var result = service.updatePerson(vo);

    assertNotNull(result);
    assertNotNull(result.getKey());
    assertNotNull(result.getLinks());

    assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
  }


  @Test
  void testUpdateWithNullPerson() {
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
      service.updatePerson(null);
    });

    String expectedMessage = "It is not allowed to persist a null object!";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void testDelete() {
    Person entity = input.mockEntity(1);
    entity.setId(1L);

    when(repository.findById(1L)).thenReturn(Optional.of(entity));

    service.deletePerson(String.valueOf(1L));
  }

  @Test
  void testFindAll() {
    List<Person> list = input.mockEntityList();

    when(repository.findAll()).thenReturn(list);

    var people = service.findAll();

    assertNotNull(people);
    assertEquals(14, people.size());

    var personOne = people.get(1);

    assertNotNull(personOne);
    assertNotNull(personOne.getKey());
    assertNotNull(personOne.getLinks());

    assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", personOne.getAddress());
    assertEquals("First Name Test1", personOne.getFirstName());
    assertEquals("Last Name Test1", personOne.getLastName());
    assertEquals("Female", personOne.getGender());

    var personFour = people.get(4);

    assertNotNull(personFour);
    assertNotNull(personFour.getKey());
    assertNotNull(personFour.getLinks());

    assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
    assertEquals("Addres Test4", personFour.getAddress());
    assertEquals("First Name Test4", personFour.getFirstName());
    assertEquals("Last Name Test4", personFour.getLastName());
    assertEquals("Male", personFour.getGender());

    var personSeven = people.get(7);

    assertNotNull(personSeven);
    assertNotNull(personSeven.getKey());
    assertNotNull(personSeven.getLinks());

    assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
    assertEquals("Addres Test7", personSeven.getAddress());
    assertEquals("First Name Test7", personSeven.getFirstName());
    assertEquals("Last Name Test7", personSeven.getLastName());
    assertEquals("Female", personSeven.getGender());

  }
}