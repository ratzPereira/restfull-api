package com.ratz.restfullapi.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@Entity
public class Permission implements GrantedAuthority {

  @Serial
  private static final long serialVersionUID = 3905055687532527066L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String description;

  public Permission() {
  }

  @Override
  public String getAuthority() {
    return this.description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
