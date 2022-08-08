package com.alvachien.libraryapi.notfoundadfvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alvachien.libraryapi.exception.BookCategoryNotFoundException;

@ControllerAdvice
public class BookCategoryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BookCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(BookCategoryNotFoundException ex) {
      return ex.getMessage();
    }       
}
