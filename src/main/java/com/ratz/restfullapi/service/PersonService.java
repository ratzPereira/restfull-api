package com.ratz.restfullapi.service;

import com.ratz.restfullapi.model.Person;

public interface PersonService {
  Person findById(String id);
}
