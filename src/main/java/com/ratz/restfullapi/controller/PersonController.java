package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.model.Person;
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
  public Person findById(@PathVariable(value = "id") String id) {

    return personService.findById(id);
  }

  @GetMapping
  public List<Person> findAll(){

    return personService.findAll();
  }

  @PostMapping
  public Person createPerson(@RequestBody Person person){

    return personService.createPerson(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePerson(@PathVariable String id){

    personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public Person updatePerson(@RequestBody Person person){

    return personService.updatePerson(person);
  }
}
