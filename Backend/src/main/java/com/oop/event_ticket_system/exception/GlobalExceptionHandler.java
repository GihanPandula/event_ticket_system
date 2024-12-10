package com.oop.event_ticket_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handle TicketConfigNotFoundException
  @ExceptionHandler(TicketConfigNotFoundException.class)
  public ResponseEntity<String> handleTicketConfigNotFoundException(TicketConfigNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  // Handle TicketPurchaseException
  @ExceptionHandler(TicketPurchaseException.class)
  public ResponseEntity<String> handleTicketPurchaseException(TicketPurchaseException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  // Handle other exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
  }
}
