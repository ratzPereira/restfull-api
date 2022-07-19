package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.model.Person;
import com.ratz.restfullapi.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class.getSimpleName());


  @Override
  public Person findById(String id) {
    return new Person(1L,"Ratz","Pereira","Dunno","Male");
  }
}
