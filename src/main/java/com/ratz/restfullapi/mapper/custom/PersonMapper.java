package com.ratz.restfullapi.mapper.custom;

import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import com.ratz.restfullapi.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

  public PersonDTOv2 convertEntityToDTO(Person person) {

    PersonDTOv2 dto = new PersonDTOv2();
    dto.setId(person.getId());
    dto.setAddress(person.getAddress());
    dto.setGender(person.getGender());
    dto.setFirstName(person.getFirstName());
    dto.setLastName(person.getLastName());
    dto.setBirthDay(new Date());

    return dto;
  }

  public Person convertDTOToEntityO(PersonDTOv2 person) {

    Person dto = new Person();
    dto.setId(person.getId());
    dto.setAddress(person.getAddress());
    dto.setGender(person.getGender());
    dto.setFirstName(person.getFirstName());
    dto.setLastName(person.getLastName());
    //dto.setBirthDay(new Date());

    return dto;
  }
}
