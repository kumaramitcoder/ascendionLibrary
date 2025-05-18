package com.ascendion.ascendionLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(BookNotFoundException b, WebRequest w){
        Map<String, Object> map = new HashMap<>();
        map.put("timeStamp", LocalDateTime.now());
        map.put("message", b.getMessage());
        map.put("details", w.getDescription(false));

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest w){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("timeStamp", LocalDateTime.now());
        map1.put("message", ex.getMessage());
        map1.put("details", w.getDescription(false));

        return new ResponseEntity<>(map1, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException rx,WebRequest webRequest){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found");
        errorBody.put("message", rx.getMessage());
        errorBody.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IsbnAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(IsbnAlreadyExistsException isbn,WebRequest webRequest){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found");
        errorBody.put("message", isbn.getMessage());
        errorBody.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BorrowerNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(BorrowerNotFoundException borower,WebRequest webRequest){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found");
        errorBody.put("message", borower.getMessage());
        errorBody.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BorrowerAllreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(BorrowerAllreadyExistsException borowerexist,WebRequest webRequest){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found");
        errorBody.put("message", borowerexist.getMessage());
        errorBody.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(LoanNotFoundException loan,WebRequest webRequest){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Not Found");
        errorBody.put("message", loan.getMessage());
        errorBody.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }


    public  ResponseEntity<String> handlegenericException(Exception ex){
        return  new ResponseEntity<>("Something went wrong "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
