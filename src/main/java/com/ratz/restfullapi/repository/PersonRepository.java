package com.ratz.restfullapi.repository;

import com.ratz.restfullapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
