package com.ascendion.ascendionLibrary.repository;

import com.ascendion.ascendionLibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
   /* List<Book> findByTitleContainingOrAuthorContaining(String title, String author);
    Optional<Book> findByIdAndBookLoansReturnDateIsNull(Long id);*/
    boolean existsByIsbn(String isbn);
    long countByIsbn(String isbn);
}
