package com.ratz.restfullapi.service;

import com.ratz.restfullapi.model.Person;

import java.util.List;

public interface PersonService {
  Person findById(String id);
  List<Person> findAll();
  Person createPerson(Person person);
  void deletePerson(String id);
  Person updatePerson(Person person);
}
