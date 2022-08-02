package com.ratz.restfullapi.service;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;

import java.util.List;

public interface PersonService {
  PersonDTOv1 findById(Long id);

  List<PersonDTOv1> findAll();

  PersonDTOv1 createPerson(PersonDTOv1 person);

  void deletePerson(String id);

  PersonDTOv1 updatePerson(PersonDTOv1 person);

  PersonDTOv2 createPersonV2(PersonDTOv2 person);

  PersonDTOv1 disablePerson(Long id);
}
