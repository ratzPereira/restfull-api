package com.ratz.restfullapi.integration.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ratz.restfullapi.integration.dto.PersonDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PersonEmbeddedDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 4346192559973483897L;

  @JsonProperty(value = "personDTOv1List")
  private List<PersonDTO> persons;


  public PersonEmbeddedDTO() {

  }

  public List<PersonDTO> getPersons() {
    return persons;
  }

  public void setPersons(List<PersonDTO> persons) {
    this.persons = persons;
  }
}
