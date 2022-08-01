package com.ratz.restfullapi.integration.dto;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;

@XmlRootElement
public class AccountCredentialsDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = -5761938655044589959L;


  private String username;
  private String password;

  public AccountCredentialsDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public AccountCredentialsDTO() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
