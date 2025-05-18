package com.ascendion.ascendionLibrary.controller;

import com.ascendion.ascendionLibrary.request.BookRequest;
import com.ascendion.ascendionLibrary.request.BorrowerRequest;
import com.ascendion.ascendionLibrary.response.BookResponse;
import com.ascendion.ascendionLibrary.response.BorrowerResponse;
import com.ascendion.ascendionLibrary.service.BorrowerService;
import com.ascendion.ascendionLibrary.serviceImpl.BorrowerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowerController {

    private final BorrowerServiceImpl borrowerService;

    public BorrowerController(BorrowerServiceImpl borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/borrowers")
    public ResponseEntity<List<BorrowerResponse>> getAllBorrowers() {
        return borrowerService.findAllBorrower();
    }

    @GetMapping("/borrower/{id}")
    public ResponseEntity<BorrowerResponse> getBorrowerById(@PathVariable Long id){
        return borrowerService.findByIdBorrower(id);
    }

    @PostMapping("/createborrower")
    public ResponseEntity<BorrowerResponse> createBorrower(@RequestBody BorrowerRequest borrowerRequest){
        return borrowerService.createBorrower(borrowerRequest);
    }

}
