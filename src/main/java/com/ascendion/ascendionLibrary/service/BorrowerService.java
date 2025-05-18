package com.ascendion.ascendionLibrary.service;

import com.ascendion.ascendionLibrary.request.BorrowerRequest;
import com.ascendion.ascendionLibrary.response.BorrowerResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BorrowerService {
    public ResponseEntity<List<BorrowerResponse>> findAllBorrower();

    ResponseEntity<BorrowerResponse> findByIdBorrower(Long id);


    public ResponseEntity<BorrowerResponse> createBorrower(BorrowerRequest borrowerRequest);

    public ResponseEntity<BorrowerResponse> createBorrowerByEmail(BorrowerRequest borrowerRequest);

    public ResponseEntity<BorrowerResponse> updateBorrower(Long id, BorrowerRequest borrowerRequest);

    public ResponseEntity<Void> deleteBorrower(Long id);
}
