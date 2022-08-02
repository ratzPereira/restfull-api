package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import com.ratz.restfullapi.controller.PersonController;
import com.ratz.restfullapi.exceptions.RequiredObjectIsNullException;
import com.ratz.restfullapi.exceptions.ResourceNotFoundException;
import com.ratz.restfullapi.mapper.DozerMapper;
import com.ratz.restfullapi.mapper.custom.PersonMapper;
import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.repository.PersonRepository;
import com.ratz.restfullapi.service.PersonService;
import jakarta.transaction.Transactional;
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
  public PersonDTOv1 findById(Long id) {

    Person person = personRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));

    PersonDTOv1 personDTOv1 = DozerMapper.parseObejct(person, PersonDTOv1.class);
    personDTOv1.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    return personDTOv1;
  }

  @Override
  public List<PersonDTOv1> findAll() {

    List<Person> personList = personRepository.findAll();

    List<PersonDTOv1> personDTOv1s = DozerMapper.parseListObejct(personList, PersonDTOv1.class);
    personDTOv1s.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
    return personDTOv1s;
  }

  @Override
  public PersonDTOv1 createPerson(PersonDTOv1 person) {

    if (person == null) throw new RequiredObjectIsNullException();

    Person entity = DozerMapper.parseObejct(person, Person.class);
    Person save = personRepository.save(entity);

    PersonDTOv1 dto = DozerMapper.parseObejct(save, PersonDTOv1.class);

    dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
    return dto;
  }

  @Override
  public void deletePerson(String id) {

    Person person = personRepository.findById(Long.valueOf(id))

        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));
    personRepository.delete(person);
  }

  @Override
  public PersonDTOv1 updatePerson(PersonDTOv1 person) {

    if (person == null) throw new RequiredObjectIsNullException();

    Person personToSave = personRepository.findById(person.getKey())
        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));

    personToSave.setAddress(person.getAddress());
    personToSave.setFirstName(person.getFirstName());
    personToSave.setLastName(person.getLastName());
    personToSave.setGender(person.getGender());

    personRepository.save(personToSave);

    PersonDTOv1 dto = DozerMapper.parseObejct(personRepository.save(personToSave), PersonDTOv1.class);
    dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
    return dto;
  }

  @Override
  @Transactional
  public PersonDTOv1 disablePerson(Long id) {

    personRepository.disablePerson(id);

    Person person = personRepository.findById(Long.valueOf(id))
        .orElseThrow(() -> new ResourceNotFoundException("Person with this ID not Found"));

    PersonDTOv1 personDTOv1 = DozerMapper.parseObejct(person, PersonDTOv1.class);
    personDTOv1.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    return personDTOv1;
  }

  @Override
  public PersonDTOv2 createPersonV2(PersonDTOv2 person) {
//
//    Person person1 = personMapper.convertDTOToEntityO(person);
//    PersonDTOv2 personDTOv2 = personMapper.convertEntityToDTO(personRepository.save(person1));
//
//    return personDTOv2;

    return null;
  }
}
