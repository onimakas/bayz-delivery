package com.bayzdelivery.exceptions;

import java.util.AbstractMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(Exception exception) {
    LOG.error("Request could not be processed: ", exception);
    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message", "Request could not be processed");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleIllegalArgumentException(IllegalArgumentException ex){
    LOG.error("Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff] ", ex);
    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("Error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
