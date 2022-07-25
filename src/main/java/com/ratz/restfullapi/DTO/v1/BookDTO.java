package com.ratz.restfullapi.DTO.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Mapping("id")
  private Long key;
  private String author;
  private Date launchDate;
  private Double price;
  private String title;

  public BookDTO() {
  }

  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getLaunchDate() {
    return launchDate;
  }

  public void setLaunchDate(Date launchDate) {
    this.launchDate = launchDate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((launchDate == null) ? 0 : launchDate.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }

}
