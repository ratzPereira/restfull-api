package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.PersonDTO;
import com.ratz.restfullapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping("/{id}")
  public PersonDTO findById(@PathVariable(value = "id") String id) {

    return personService.findById(id);
  }

  @GetMapping
  public List<PersonDTO> findAll(){

    return personService.findAll();
  }

  @PostMapping
  public PersonDTO createPerson(@RequestBody PersonDTO person){

    return personService.createPerson(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePerson(@PathVariable String id){

    personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public PersonDTO updatePerson(@RequestBody PersonDTO person){

    return personService.updatePerson(person);
  }
}
