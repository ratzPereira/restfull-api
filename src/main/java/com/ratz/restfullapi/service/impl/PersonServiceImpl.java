package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import com.ratz.restfullapi.controller.PersonController;
import com.ratz.restfullapi.exceptions.ResourceNotFoundException;
import com.ratz.restfullapi.mapper.DozerMapper;
import com.ratz.restfullapi.mapper.custom.PersonMapper;
import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.repository.PersonRepository;
import com.ratz.restfullapi.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class.getSimpleName());

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonMapper personMapper;

  @Override
  public PersonDTOv1 findById(String id) {

    Person person = personRepository.findById(Long.valueOf(id))
        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));

    PersonDTOv1 personDTOv1 = DozerMapper.parseObejct(person, PersonDTOv1.class);
    personDTOv1.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    return personDTOv1;
  }

  @Override
  public List<PersonDTOv1> findAll() {

    List<Person> personList = personRepository.findAll();

    return DozerMapper.parseListObejct(personList, PersonDTOv1.class);
  }

  @Override
  public PersonDTOv1 createPerson(PersonDTOv1 person) {

    Person personToSave = new Person();
    personToSave.setAddress(person.getAddress());
    personToSave.setFirstName(person.getFirstName());
    personToSave.setLastName(person.getLastName());
    personToSave.setGender(person.getGender());

    personRepository.save(personToSave);
    return DozerMapper.parseObejct(personToSave, PersonDTOv1.class);
  }

  @Override
  public void deletePerson(String id) {

    Person person = personRepository.findById(Long.valueOf(id))

        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));
    personRepository.delete(person);
  }

  @Override
  public PersonDTOv1 updatePerson(PersonDTOv1 person) {

    Person personToSave = personRepository.findById(person.getKey())
        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));

    personToSave.setAddress(person.getAddress());
    personToSave.setFirstName(person.getFirstName());
    personToSave.setLastName(person.getLastName());
    personToSave.setGender(person.getGender());

    personRepository.save(personToSave);

    return DozerMapper.parseObejct(personToSave, PersonDTOv1.class);
  }

  @Override
  public PersonDTOv2 createPersonV2(PersonDTOv2 person) {

    Person person1 = personMapper.convertDTOToEntityO(person);
    PersonDTOv2 personDTOv2 = personMapper.convertEntityToDTO(personRepository.save(person1));

    return personDTOv2;
  }
}
