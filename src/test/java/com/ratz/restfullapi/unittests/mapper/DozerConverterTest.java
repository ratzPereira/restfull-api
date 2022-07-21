package com.ratz.restfullapi.unittests.mapper;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.mapper.DozerMapper;
import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterTest {

  MockPerson inputObject;

  @BeforeEach
  public void setUp() {
    inputObject = new MockPerson();
  }

  @Test
  public void parseEntityToDTOTest() {
    PersonDTOv1 output = DozerMapper.parseObejct(inputObject.mockEntity(), PersonDTOv1.class);
    assertEquals(Long.valueOf(0L), output.getKey());
    assertEquals("First Name Test0", output.getFirstName());
    assertEquals("Last Name Test0", output.getLastName());
    assertEquals("Addres Test0", output.getAddress());
    assertEquals("Male", output.getGender());
  }

  @Test
  public void parseEntityListToDTOListTest() {
    List<PersonDTOv1> outputList = DozerMapper.parseListObejct(inputObject.mockEntityList(), PersonDTOv1.class);
    PersonDTOv1 outputZero = outputList.get(0);

    assertEquals(Long.valueOf(0L), outputZero.getKey());
    assertEquals("First Name Test0", outputZero.getFirstName());
    assertEquals("Last Name Test0", outputZero.getLastName());
    assertEquals("Addres Test0", outputZero.getAddress());
    assertEquals("Male", outputZero.getGender());

    PersonDTOv1 outputSeven = outputList.get(7);

    assertEquals(Long.valueOf(7L), outputSeven.getKey());
    assertEquals("First Name Test7", outputSeven.getFirstName());
    assertEquals("Last Name Test7", outputSeven.getLastName());
    assertEquals("Addres Test7", outputSeven.getAddress());
    assertEquals("Female", outputSeven.getGender());

    PersonDTOv1 outputTwelve = outputList.get(12);

    assertEquals(Long.valueOf(12L), outputTwelve.getKey());
    assertEquals("First Name Test12", outputTwelve.getFirstName());
    assertEquals("Last Name Test12", outputTwelve.getLastName());
    assertEquals("Addres Test12", outputTwelve.getAddress());
    assertEquals("Male", outputTwelve.getGender());
  }

  @Test
  public void parseDTOToEntityTest() {
    Person output = DozerMapper.parseObejct(inputObject.mockDTO(), Person.class);
    assertEquals(Long.valueOf(0L), output.getId());
    assertEquals("First Name Test0", output.getFirstName());
    assertEquals("Last Name Test0", output.getLastName());
    assertEquals("Addres Test0", output.getAddress());
    assertEquals("Male", output.getGender());
  }

  @Test
  public void parserDTOListToEntityListTest() {
    List<Person> outputList = DozerMapper.parseListObejct(inputObject.mockDTOList(), Person.class);
    Person outputZero = outputList.get(0);

    assertEquals(Long.valueOf(0L), outputZero.getId());
    assertEquals("First Name Test0", outputZero.getFirstName());
    assertEquals("Last Name Test0", outputZero.getLastName());
    assertEquals("Addres Test0", outputZero.getAddress());
    assertEquals("Male", outputZero.getGender());

    Person outputSeven = outputList.get(7);

    assertEquals(Long.valueOf(7L), outputSeven.getId());
    assertEquals("First Name Test7", outputSeven.getFirstName());
    assertEquals("Last Name Test7", outputSeven.getLastName());
    assertEquals("Addres Test7", outputSeven.getAddress());
    assertEquals("Female", outputSeven.getGender());

    Person outputTwelve = outputList.get(12);

    assertEquals(Long.valueOf(12L), outputTwelve.getId());
    assertEquals("First Name Test12", outputTwelve.getFirstName());
    assertEquals("Last Name Test12", outputTwelve.getLastName());
    assertEquals("Addres Test12", outputTwelve.getAddress());
    assertEquals("Male", outputTwelve.getGender());
  }
}
