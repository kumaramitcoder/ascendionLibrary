package com.ascendion.ascendionLibrary.repository;

import com.ascendion.ascendionLibrary.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByEmail(String email);
    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
