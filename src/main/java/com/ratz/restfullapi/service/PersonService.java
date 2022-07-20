package com.ratz.restfullapi.service;

import com.ratz.restfullapi.DTO.v1.PersonDTO;
import com.ratz.restfullapi.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
  PersonDTO findById(String id);
  List<PersonDTO> findAll();
  PersonDTO createPerson(PersonDTO person);
  void deletePerson(String id);
  PersonDTO updatePerson(PersonDTO person);
}
