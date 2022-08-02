package com.ratz.restfullapi.service;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface PersonService {
  PersonDTOv1 findById(Long id);

  PagedModel<EntityModel<PersonDTOv1>> findAll(Pageable pageable);

  PagedModel<EntityModel<PersonDTOv1>> findPersonsByName(String firstName, Pageable pageable);

  PersonDTOv1 createPerson(PersonDTOv1 person);

  void deletePerson(String id);

  PersonDTOv1 updatePerson(PersonDTOv1 person);

  PersonDTOv2 createPersonV2(PersonDTOv2 person);

  PersonDTOv1 disablePerson(Long id);
}
