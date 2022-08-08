package com.alvachien.libraryapi.notfoundadfvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alvachien.libraryapi.exception.PersonRoleNotFoundException;

@ControllerAdvice
public class PersonRoleNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PersonRoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(PersonRoleNotFoundException ex) {
      return ex.getMessage();
    }     
}
