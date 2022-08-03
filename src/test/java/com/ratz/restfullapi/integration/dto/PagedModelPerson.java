package com.ratz.restfullapi.integration.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class PagedModelPerson {

  @XmlElement(name = "content")
  private List<PersonDTO> content;

  public PagedModelPerson() {
  }

  public List<PersonDTO> getContent() {
    return content;
  }

  public void setContent(List<PersonDTO> content) {
    this.content = content;
  }
}
