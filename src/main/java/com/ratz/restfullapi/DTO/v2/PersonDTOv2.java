package com.ratz.restfullapi.DTO.v2;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class PersonDTOv2 implements Serializable {

  @Serial
  private static final long serialVersionUID = -5452647583822206968L;


  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private Date birthDay;
  private String gender;

  public PersonDTOv2() {
  }

  public PersonDTOv2(Long id, String firstName, String lastName, String address, String gender, Date birthDay) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.gender = gender;
    this.birthDay = birthDay;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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


  public Date getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }
}
