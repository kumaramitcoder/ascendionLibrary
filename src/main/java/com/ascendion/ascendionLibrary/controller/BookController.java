package com.ascendion.ascendionLibrary.controller;


import com.ascendion.ascendionLibrary.request.BookRequest;
import com.ascendion.ascendionLibrary.response.BookResponse;
import com.ascendion.ascendionLibrary.serviceImpl.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return bookService.findAllBooks();
    }

   /* @GetMapping("/searchbytitleorauthor")
    public ResponseEntity<List<BookResponse>> searchBooksByTitleOrAuthor(@RequestParam String query) {
        try {
            return bookService.searchBooksByAuthororTitle(query);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        return bookService.findByIdBook(id);
    }

    /*@PostMapping("/book")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequset bookRequset){
        return bookService.createBook(bookRequset);
    }*/

    @PostMapping("/createbook")
    public ResponseEntity<BookResponse> createBookByISBN(@RequestBody BookRequest bookRequest){
        return bookService.createBookByISBN(bookRequest);
    }

    @PutMapping("/updatebook/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest){
        return bookService.updateBook(id, bookRequest);
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

}
