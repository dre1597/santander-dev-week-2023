package org.example.devweek.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(final IllegalArgumentException exception) {
    logger.warn(exception.getMessage(), exception);
    return ResponseEntity.unprocessableEntity().body(exception.getMessage());
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleNoSuchElementException(final NoSuchElementException exception) {
    logger.warn(exception.getMessage(), exception);
    
    var message = exception.getMessage();

    if (message == null) {
      message = "Resource Not found";
    }

    logger.warn(message, exception);
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<String> handleUnknownException(final Throwable exception) {
    logger.error("Unexpected error", exception);
    return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
