package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import com.ratz.restfullapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping("/{id}")
  public PersonDTOv1 findById(@PathVariable(value = "id") String id) {

    return personService.findById(id);
  }

  @GetMapping
  public List<PersonDTOv1> findAll() {

    return personService.findAll();
  }

  @PostMapping
  public PersonDTOv1 createPerson(@RequestBody PersonDTOv1 person) {

    return personService.createPerson(person);
  }

  @PostMapping("/api/person/v2")
  public PersonDTOv2 createPersonV2(@RequestBody PersonDTOv2 person) {

    return personService.createPersonV2(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePerson(@PathVariable String id) {

    personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public PersonDTOv1 updatePerson(@RequestBody PersonDTOv1 person) {

    return personService.updatePerson(person);
  }
}
