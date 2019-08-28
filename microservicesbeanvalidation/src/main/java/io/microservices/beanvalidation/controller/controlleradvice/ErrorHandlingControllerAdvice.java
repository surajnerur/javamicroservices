package io.microservices.beanvalidation.controller.controlleradvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    /*for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    } old implementation*/
    /* Java 8 implementation below*/
    e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
    	error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    });
    return error;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ValidationErrorResponse onException(Exception e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    /*for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }*/
    error.getViolations().add(new Violation(e.getMessage(),e.getLocalizedMessage()));
    return error;
  }  
}
