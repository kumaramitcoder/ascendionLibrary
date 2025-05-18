package com.ascendion.ascendionLibrary.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequest {
    @Column(nullable = false)
    @NotBlank(message = "Isbn is required")
    private String isbn;
    @Column(nullable = false)
    @NotBlank(message = "title is required")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "author is required")
    private String author;

    private boolean available;

    public BookRequest( String isbn, String title, String author, boolean available) {

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = available;
    }



    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
