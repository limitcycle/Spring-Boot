package com.example.api.handler;

import com.example.api.exception.InvalidRequestException;
import com.example.api.exception.NotFoundException;
import com.example.api.resource.FieldResource;
import com.example.api.resource.InvalidErrorResource;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

  /**
   * 處理找不到書異常
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseBody
  public ResponseEntity<?> handleNofFound(RuntimeException e) {
//        ErrorResource errorResource = new ErrorResource(e.getMessage());
    ResponseEntity<Object> result = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        logger.warn("Return ------ {}",result );
    return result;
  }

  @ExceptionHandler(InvalidRequestException.class)
  @ResponseBody
  public ResponseEntity<?> handleInvalidRequest(InvalidRequestException e) {
    Errors errors = e.getErrors();
    List<FieldResource> fieldResources = new ArrayList<>();
    List<FieldError> fieldErrors = errors.getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      FieldResource fieldResource = new FieldResource(fieldError.getObjectName(),
          fieldError.getField(),
          fieldError.getCode(),
          fieldError.getDefaultMessage());

      fieldResources.add(fieldResource);
    }

    InvalidErrorResource ier = new InvalidErrorResource(e.getMessage(), fieldResources);
    ResponseEntity<Object> result = new ResponseEntity<>(ier, HttpStatus.BAD_REQUEST);
//        logger.warn("Return ------ {}",result );
    return result;
  }

  /**
   * 處理全局異常
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<?> handleException(Exception e) {
    logger.error("Error ---- {}", e);
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
