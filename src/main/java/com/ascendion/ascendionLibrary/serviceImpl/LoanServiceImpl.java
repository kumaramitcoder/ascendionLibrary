package com.ascendion.ascendionLibrary.serviceImpl;

import com.ascendion.ascendionLibrary.entity.Book;
import com.ascendion.ascendionLibrary.entity.Borrower;
import com.ascendion.ascendionLibrary.entity.Loan;
import com.ascendion.ascendionLibrary.exception.BookNotFoundException;
import com.ascendion.ascendionLibrary.exception.BorrowerAllreadyExistsException;
import com.ascendion.ascendionLibrary.exception.BorrowerNotFoundException;
import com.ascendion.ascendionLibrary.exception.LoanNotFoundException;
import com.ascendion.ascendionLibrary.repository.BookRepository;
import com.ascendion.ascendionLibrary.repository.BorrowerRepository;
import com.ascendion.ascendionLibrary.repository.LoanRepository;
import com.ascendion.ascendionLibrary.response.LoanResponse;
import com.ascendion.ascendionLibrary.service.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookServiceImpl bookService;
    private final BorrowerServiceImpl borrowerService;

    private  final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;

    public LoanServiceImpl(LoanRepository loanRepository, BookServiceImpl bookService, BorrowerServiceImpl borrowerService, BorrowerRepository borrowerRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.borrowerService = borrowerService;
        this.borrowerRepository = borrowerRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<LoanResponse> borrowBook(Long bookId, Long borrowerId) {
        try {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(()-> new BorrowerNotFoundException("Book not found with id: " + bookId));
            Borrower borrower = borrowerRepository.findById(borrowerId)
                    .orElseThrow(() -> new BorrowerNotFoundException("Borrower not found with id: " + borrowerId));
            if (loanRepository.existsByBookAndReturnDateIsNull(book)) {
                throw new BorrowerAllreadyExistsException("Book with id " + bookId + " is already borrowed");
        }

            Loan loan = new Loan(book, borrower);
            loanRepository.save(loan);


            LoanResponse response = new LoanResponse();
            response.getLoanId(loan.getId());
            response.setBookId(book.getId());
            response.setBookTitle(book.getTitle());
            response.setBorrowerId(borrower.getId());
            response.setBorrowerName(borrower.getName());
            response.setBorrowDate(loan.getBorrowDate());
            response.setDueDate(loan.getDueDate());
            response.setStatus("ACTIVE");
            return ResponseEntity
                    .created(URI.create("/loans/" + response.getLoanId(loan.getId())))
                    .body(response);
        }
        catch (BookNotFoundException  | BorrowerNotFoundException borrowerNotFoundException ) {
            return ResponseEntity.notFound().build();
        } catch (BorrowerAllreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

    @Override
    public ResponseEntity<LoanResponse> returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        Loan loan = loanRepository
                .findByBookAndReturnDateIsNull(book)
                .orElseThrow(() -> new LoanNotFoundException("Book is not currently borrowed"));

        loan.setReturnDate(LocalDate.now());


        loanRepository.save(new Loan(book, loan));


        LoanResponse response = new LoanResponse();
        response.setLoanId(loan.getId());
        response.setBookId(book.getId());
        response.setBookTitle(book.getTitle());
        response.setBorrowerId(loan.getBorrower().getId());
        response.setBorrowerName(loan.getBorrower().getName());
        response.setBorrowDate(loan.getBorrowDate());
        response.setDueDate(loan.getDueDate());
        response.setReturnDate(loan.getReturnDate());
        response.setStatus("RETURNED");

        return ResponseEntity.ok(response);
    }
}
