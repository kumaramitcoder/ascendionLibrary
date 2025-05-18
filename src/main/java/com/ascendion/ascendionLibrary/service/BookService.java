package com.ascendion.ascendionLibrary.service;


import com.ascendion.ascendionLibrary.entity.Book;
import com.ascendion.ascendionLibrary.request.BookRequest;
import com.ascendion.ascendionLibrary.response.BookResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    public ResponseEntity<List<BookResponse>> findAllBooks();

    ResponseEntity<BookResponse> findByIdBook(Long id);

    public ResponseEntity<BookResponse> createBook(BookRequest bookRequset);

    public ResponseEntity<BookResponse> updateBook(Long id, BookRequest bookRequest);

    public ResponseEntity<Void> deleteBook(Long id);

    long countBooksByIsbn(String isbn);

   public ResponseEntity<BookResponse> createBookByISBN(BookRequest bookRequest);

    /*public ResponseEntity<List<BookResponse>> searchBooksByAuthororTitle(String query);*/



}
