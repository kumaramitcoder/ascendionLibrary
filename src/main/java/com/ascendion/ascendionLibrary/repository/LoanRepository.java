package com.ascendion.ascendionLibrary.repository;

import com.ascendion.ascendionLibrary.entity.Book;
import com.ascendion.ascendionLibrary.entity.Borrower;
import com.ascendion.ascendionLibrary.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByBookAndReturnDateIsNull(Book book);
    List<Loan> findByBorrowerAndReturnDateIsNull(Borrower borrower);
    Optional<Loan> findByBookAndBorrowerAndReturnDateIsNull(Book book, Borrower borrower);

    boolean existsByBookAndReturnDateIsNull(Book book);
}
