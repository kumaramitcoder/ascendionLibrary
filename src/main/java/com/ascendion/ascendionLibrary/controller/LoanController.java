package com.ascendion.ascendionLibrary.controller;

import com.ascendion.ascendionLibrary.response.LoanResponse;
import com.ascendion.ascendionLibrary.serviceImpl.LoanServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoanController {
    private final LoanServiceImpl loanService;

    public LoanController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<LoanResponse> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long borrowerId) {
        return loanService.borrowBook(bookId, borrowerId);
    }

    @PutMapping("/return")
    public ResponseEntity<LoanResponse> returnBook(@RequestParam Long bookId) {
        return loanService.returnBook(bookId);
    }
}
