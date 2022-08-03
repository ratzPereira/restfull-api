package com.ratz.restfullapi.integration.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serial;
import java.io.Serializable;

@XmlRootElement
public class WrapperPersonDTO implements Serializable {


  @Serial
  private static final long serialVersionUID = 4346192559973483897L;

  @JsonProperty(value = "_embedded")
  private PersonEmbeddedDTO embedded;

  public WrapperPersonDTO() {
  }

  public PersonEmbeddedDTO getEmbedded() {
    return embedded;
  }

  public void setEmbedded(PersonEmbeddedDTO embedded) {
    this.embedded = embedded;
  }
}
