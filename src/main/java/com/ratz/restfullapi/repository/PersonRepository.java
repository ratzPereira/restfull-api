package com.ratz.restfullapi.repository;

import com.ratz.restfullapi.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

  @Modifying
  @Query(value = "UPDATE Person p set p.enabled = false WHERE p.id =:id")
  void disablePerson(@Param("id") Long id);

  @Query(value = "select p from Person p where p.firstName like lower(concat('%', :firstName, '%'))")
  Page<Person> findPersonsByName(@Param("firstName") String firstName, Pageable pageable);
}
