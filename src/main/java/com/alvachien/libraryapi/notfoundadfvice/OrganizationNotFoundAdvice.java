package com.alvachien.libraryapi.notfoundadfvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alvachien.libraryapi.exception.OrganizationNotFoundException;

@ControllerAdvice
public class OrganizationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(OrganizationNotFoundException ex) {
      return ex.getMessage();
    }        
}
