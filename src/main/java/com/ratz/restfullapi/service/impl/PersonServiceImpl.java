package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.DTO.v1.PersonDTO;
import com.ratz.restfullapi.exceptions.ResourceNotFoundException;
import com.ratz.restfullapi.mapper.DozerMapper;
import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.repository.PersonRepository;
import com.ratz.restfullapi.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class.getSimpleName());

  @Autowired
  private PersonRepository personRepository;

  @Override
  public PersonDTO findById(String id) {

    Person person = personRepository.findById(Long.valueOf(id))
        .orElseThrow(()-> new ResourceNotFoundException("Person with this ID not Found"));

    return DozerMapper.parseObejct(person, PersonDTO.class);
  }

  @Override
  public List<PersonDTO> findAll() {

    List<Person> personList = personRepository.findAll();

    return DozerMapper.parseListObejct(personList, PersonDTO.class);
  }

  @Override
  public PersonDTO createPerson(PersonDTO person) {

    Person personToSave = new Person();
    personToSave.setAddress(person.getAddress());
    personToSave.setFirstName(person.getFirstName());
    personToSave.setLastName(person.getLastName());
    personToSave.setGender(person.getGender());

    personRepository.save(personToSave);
    return DozerMapper.parseObejct(personToSave, PersonDTO.class);
  }

  @Override
  public void deletePerson(String id) {

    Person person = personRepository.findById(Long.valueOf(id))

        .orElseThrow(()-> new ResourceNotFoundException("Person with this ID not Found"));
    personRepository.delete(person);
  }

  @Override
  public PersonDTO updatePerson(PersonDTO person) {

    Person personToSave = personRepository.findById(person.getId())
        .orElseThrow(()-> new ResourceNotFoundException("Person with this ID not Found"));

    personToSave .setAddress(person.getAddress());
    personToSave .setFirstName(person.getFirstName());
    personToSave .setLastName(person.getLastName());
    personToSave .setGender(person.getGender());

    personRepository.save(personToSave);

    return DozerMapper.parseObejct(personToSave, PersonDTO.class);
  }
}
