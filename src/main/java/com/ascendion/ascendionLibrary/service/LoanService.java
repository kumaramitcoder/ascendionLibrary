package com.ascendion.ascendionLibrary.service;

import com.ascendion.ascendionLibrary.request.BookRequest;
import com.ascendion.ascendionLibrary.response.BookResponse;
import com.ascendion.ascendionLibrary.response.LoanResponse;
import org.springframework.http.ResponseEntity;

public interface LoanService {

    public ResponseEntity<LoanResponse> borrowBook(Long bookId, Long borrowerId);
    public ResponseEntity<LoanResponse> returnBook(Long bookId);
}
