package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import com.ratz.restfullapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ratz.restfullapi.utils.MediaTypeUtils.*;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping(value = "/{id}", produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  public PersonDTOv1 findById(@PathVariable(value = "id") Long id) {

    return personService.findById(id);
  }

  @GetMapping(produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  public List<PersonDTOv1> findAll() {

    return personService.findAll();
  }

  @PostMapping(
      produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
      consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  public PersonDTOv1 createPerson(@RequestBody PersonDTOv1 person) {

    return personService.createPerson(person);
  }

  @PostMapping(value = "/api/person/v2",
      produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
      consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  public PersonDTOv2 createPersonV2(@RequestBody PersonDTOv2 person) {

    return personService.createPersonV2(person);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deletePerson(@PathVariable String id) {

    personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(
      produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
      consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  public PersonDTOv1 updatePerson(@RequestBody PersonDTOv1 person) {

    return personService.updatePerson(person);
  }
}
