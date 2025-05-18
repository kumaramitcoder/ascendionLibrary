package com.ascendion.ascendionLibrary.serviceImpl;

import com.ascendion.ascendionLibrary.entity.Book;
import com.ascendion.ascendionLibrary.entity.Borrower;
import com.ascendion.ascendionLibrary.exception.BookNotFoundException;
import com.ascendion.ascendionLibrary.exception.BorrowerAllreadyExistsException;
import com.ascendion.ascendionLibrary.exception.BorrowerNotFoundException;
import com.ascendion.ascendionLibrary.exception.IsbnAlreadyExistsException;
import com.ascendion.ascendionLibrary.repository.BorrowerRepository;
import com.ascendion.ascendionLibrary.request.BorrowerRequest;
import com.ascendion.ascendionLibrary.response.BookResponse;
import com.ascendion.ascendionLibrary.response.BorrowerResponse;
import com.ascendion.ascendionLibrary.service.BorrowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }


    @Override
    public ResponseEntity<List<BorrowerResponse>> findAllBorrower() {
        List<Borrower> list = borrowerRepository.findAll();
        List<BorrowerResponse> updatedList = list.stream()
                .map(e-> new BorrowerResponse(e.getId(), e.getName(), e.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(updatedList);
    }

    @Override
    public ResponseEntity<BorrowerResponse> findByIdBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(()->new BorrowerNotFoundException("Borrower Not Found with id: "+id));
        BorrowerResponse borrowerResponse = new BorrowerResponse(borrower.getId(),borrower.getName(), borrower.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(borrowerResponse);
    }

    @Override
    public ResponseEntity<BorrowerResponse> createBorrower(BorrowerRequest borrowerRequest) {

        Borrower borrower = new Borrower();
        borrower.setName(borrowerRequest.getName());
        borrower.setEmail(borrowerRequest.getEmail());
        Borrower updateBorrower = borrowerRepository.save(borrower);

        BorrowerResponse borrowerResponse = new BorrowerResponse(updateBorrower.getId(), updateBorrower.getName(), updateBorrower.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowerResponse);
    }

    @Override
    public ResponseEntity<BorrowerResponse> createBorrowerByEmail(BorrowerRequest borrowerRequest) {
        if (borrowerRepository.findByEmail(borrowerRequest.getEmail()).isPresent()) {
            throw new BorrowerAllreadyExistsException("Borrower with email " + borrowerRequest.getEmail() + " already exists");
        }

        Borrower borrower = new Borrower();
        borrower.setName(borrowerRequest.getName());
        borrower.setEmail(borrowerRequest.getEmail());
        Borrower updateBorrower = borrowerRepository.save(borrower);

        BorrowerResponse borrowerResponse = new BorrowerResponse(updateBorrower.getId(), updateBorrower.getName(), updateBorrower.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowerResponse);
    }

    @Override
    public ResponseEntity<BorrowerResponse> updateBorrower(Long id, BorrowerRequest borrowerRequest) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(()->new BorrowerNotFoundException("Not get by id"+id));

        borrower.setName(borrowerRequest.getName());
        borrower.setEmail(borrowerRequest.getEmail());

        Borrower updtaeBorrower = borrowerRepository.save(borrower);

        BorrowerResponse borrowerResponse = new BorrowerResponse(updtaeBorrower.getId(), updtaeBorrower.getName(), updtaeBorrower.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(borrowerResponse);
    }

    @Override
    public ResponseEntity<Void> deleteBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Id not found : "+id));
        borrowerRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
