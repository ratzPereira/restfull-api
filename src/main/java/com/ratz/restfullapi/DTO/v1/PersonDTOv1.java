package com.ratz.restfullapi.DTO.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
public class PersonDTOv1 extends RepresentationModel<PersonDTOv1> implements Serializable {

  @Serial
  private static final long serialVersionUID = -5452647583822206968L;

  @Mapping("id")
  @JsonProperty("id")
  private Long key;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  private String address;

  //@JsonIgnore
  private String gender;

  public PersonDTOv1() {
  }

//  public PersonDTOv1(Long key, String firstName, String lastName, String address, String gender) {
//    this.key = key;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.address = address;
//    this.gender = gender;
//  }

  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
