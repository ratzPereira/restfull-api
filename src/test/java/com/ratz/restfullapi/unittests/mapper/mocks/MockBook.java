package com.ratz.restfullapi.unittests.mapper.mocks;

import com.ratz.restfullapi.DTO.v1.BookDTO;
import com.ratz.restfullapi.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

  public Book mockEntity() {
    return mockEntity(0);
  }

  public BookDTO mockVO() {
    return mockVO(0);
  }

  public List<Book> mockEntityList() {
    List<Book> books = new ArrayList<Book>();
    for (int i = 0; i < 14; i++) {
      books.add(mockEntity(i));
    }
    return books;
  }

  public List<BookDTO> mockVOList() {
    List<BookDTO> books = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      books.add(mockVO(i));
    }
    return books;
  }

  public Book mockEntity(Integer number) {
    Book book = new Book();
    book.setId(number.longValue());
    book.setAuthor("Some Author" + number);
    book.setLaunchDate(new Date());
    book.setPrice(25D);
    book.setTitle("Some Title" + number);
    return book;
  }

  public BookDTO mockVO(Integer number) {
    BookDTO book = new BookDTO();
    book.setKey(number.longValue());
    book.setAuthor("Some Author" + number);
    book.setLaunchDate(new Date());
    book.setPrice(25D);
    book.setTitle("Some Title" + number);
    return book;
  }
}
