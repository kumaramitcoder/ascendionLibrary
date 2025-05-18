package com.ascendion.ascendionLibrary.serviceImpl;

import com.ascendion.ascendionLibrary.entity.Book;
import com.ascendion.ascendionLibrary.entity.Borrower;
import com.ascendion.ascendionLibrary.exception.BookNotFoundException;
import com.ascendion.ascendionLibrary.exception.BorrowerNotFoundException;
import com.ascendion.ascendionLibrary.exception.IsbnAlreadyExistsException;
import com.ascendion.ascendionLibrary.repository.BookRepository;

import com.ascendion.ascendionLibrary.request.BookRequest;
import com.ascendion.ascendionLibrary.response.BookResponse;
import com.ascendion.ascendionLibrary.response.BorrowerResponse;
import com.ascendion.ascendionLibrary.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<Book> list = bookRepository.findAll();
        List<BookResponse> updatedList = list.stream()
                .map(e-> new BookResponse(e.getId(), e.getIsbn(), e.getTitle(), e.getAuthor(), e.isAvailable()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(updatedList);
    }

    @Override
    public ResponseEntity<BookResponse> findByIdBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book Not Found with id: "+id));
        BookResponse bookResponse = new BookResponse(book.getId(),book.getIsbn(), book.getTitle(),book.getAuthor(), book.isAvailable());
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @Override
    public ResponseEntity<BookResponse> createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setAvailable(bookRequest.isAvailable());

        Book updateBook = bookRepository.save(book);

        BookResponse bookResponse = new BookResponse(updateBook.getId(), updateBook.getIsbn(), updateBook.getTitle(), updateBook.getAuthor(), updateBook.isAvailable());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }


    @Override
    public ResponseEntity<BookResponse> createBookByISBN(BookRequest bookRequest) {

        if (bookRepository.existsByIsbn(bookRequest.getIsbn())) {
            throw new IsbnAlreadyExistsException("ISBN Allready Exists"+bookRequest.getIsbn());
        }

        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setAvailable(bookRequest.isAvailable());

        Book updateBook = bookRepository.save(book);

        BookResponse bookResponse = new BookResponse(updateBook.getId(), updateBook.getIsbn(), updateBook.getTitle(), updateBook.getAuthor(), updateBook.isAvailable());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Not get by id"+id));

        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setAvailable(bookRequest.isAvailable());

        Book updtaeBook = bookRepository.save(book);

        BookResponse bookResponse = new BookResponse(updtaeBook.getId(), updtaeBook.getIsbn(), updtaeBook.getTitle(), updtaeBook.getAuthor(), updtaeBook.isAvailable());
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Id not found : "+id));
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public long countBooksByIsbn(String isbn) {

        return bookRepository.countByIsbn(isbn);
    }

    /*public ResponseEntity<List<BookResponse>> searchBooksByAuthororTitle(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }

        List<Book> books = bookRepository.findByTitleContainingOrAuthorContaining(query, query);

        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(List.of());
        }
        List<BookResponse> responses = books.stream()
                .map(book -> new BookResponse(
                        book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.isAvailable()
                ))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }*/

//    @Override
//    public ResponseEntity<BookResponse> findByISBN(Long id) {
//        // Validate ISBN format if needed
//        if (isbn == null || isbn.trim().isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        List<Book> books = bookRepository.findByIsbn(isbn);
//
//        if (books.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        List<BookResponse> responses = books.stream()
//                .map(book -> new BookResponse(
//                        book.getId(),
//                        book.getIsbn(),
//                        book.getTitle(),
//                        book.getAuthor(),
//                        book.getBookLoans().stream().noneMatch(loan -> loan.getReturnDate() == null)
//                ))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(responses);
//    }


}
