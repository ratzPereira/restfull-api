package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.DTO.v1.BookDTO;
import com.ratz.restfullapi.controller.BookController;
import com.ratz.restfullapi.exceptions.RequiredObjectIsNullException;
import com.ratz.restfullapi.exceptions.ResourceNotFoundException;
import com.ratz.restfullapi.mapper.DozerMapper;
import com.ratz.restfullapi.model.Book;
import com.ratz.restfullapi.repository.BookRepository;
import com.ratz.restfullapi.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository repository;
  private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class.getName());

  @Override
  public List<BookDTO> findAll() {

    logger.info("Finding all book!");

    List<BookDTO> books = DozerMapper.parseListObejct(repository.findAll(), BookDTO.class);
    books
        .stream()
        .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
    return books;
  }

  @Override
  public BookDTO findById(Long id) {

    logger.info("Finding one book!");

    Book entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    BookDTO vo = DozerMapper.parseObejct(entity, BookDTO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
    return vo;
  }

  @Override
  public BookDTO create(BookDTO book) {

    if (book == null) throw new RequiredObjectIsNullException();

    logger.info("Creating one book!");
    Book entity = DozerMapper.parseObejct(book, Book.class);
    BookDTO vo = DozerMapper.parseObejct(repository.save(entity), BookDTO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
    return vo;
  }

  @Override
  public BookDTO update(BookDTO book) {

    if (book == null) throw new RequiredObjectIsNullException();

    logger.info("Updating one book!");

    Book entity = repository.findById(book.getKey())
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    entity.setAuthor(book.getAuthor());
    entity.setLaunchDate(book.getLaunchDate());
    entity.setPrice(book.getPrice());
    entity.setTitle(book.getTitle());

    BookDTO vo = DozerMapper.parseObejct(repository.save(entity), BookDTO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
    return vo;
  }

  @Override
  public void delete(Long id) {

    logger.info("Deleting one book!");

    var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    repository.delete(entity);
  }
}
