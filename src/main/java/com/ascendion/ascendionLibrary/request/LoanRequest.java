package com.ascendion.ascendionLibrary.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class LoanRequest {
    private Long bookId;
    private Long borrowerId;
    private LocalDate dueDate;

    public LoanRequest(Long bookId, Long borrowerId, LocalDate dueDate) {
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.dueDate = dueDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
