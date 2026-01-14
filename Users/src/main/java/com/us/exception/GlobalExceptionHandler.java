package com.us.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleExists(ResourceAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalid(InvalidCredentialsException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

}
