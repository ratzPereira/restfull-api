package com.ratz.restfullapi.repository;

import com.ratz.restfullapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
