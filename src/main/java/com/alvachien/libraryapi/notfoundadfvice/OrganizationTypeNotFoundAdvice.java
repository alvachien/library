package com.alvachien.libraryapi.notfoundadfvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alvachien.libraryapi.exception.OrganizationTypeNotFoundException;

@ControllerAdvice
public class OrganizationTypeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OrganizationTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(OrganizationTypeNotFoundException ex) {
      return ex.getMessage();
    }    
}
