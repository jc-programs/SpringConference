package io.bcn.springConference.repository;

import io.bcn.springConference.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
