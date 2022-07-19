package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class.getSimpleName());


  @Override
  public Person findById(String id) {
    return new Person(1L,"Ratz","Pereira","Dunno","Male");
  }

  @Override
  public List<Person> findAll() {

    List<Person> personList = new ArrayList<>();

    for (int i = 0; i < 10 ; i++) {
      Person person = new Person(1L,"Ratz","Pereira","Dunno","Male");
      personList.add(person);
    }

    return personList;
  }

  @Override
  public Person createPerson(Person person) {
    return null;
  }

  @Override
  public void deletePerson(String id) {

  }

  @Override
  public Person updatePerson(Person person) {
    return null;
  }
}
