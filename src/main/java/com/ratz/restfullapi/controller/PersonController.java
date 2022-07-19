package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping("/{id}")
  public Person findById(@PathVariable(value = "id") String id) {

    return personService.findById(id);
  }
}
