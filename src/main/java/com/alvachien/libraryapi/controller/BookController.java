package com.alvachien.libraryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.BookNotFoundException;
import com.alvachien.libraryapi.model.Book;
import com.alvachien.libraryapi.repository.BookRepository;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    public BookController() {
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    Iterable<Book> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/books")
    Book newBook(@RequestBody Book newbook) {
        return repository.save(newbook);
    }

    // Single item
    @GetMapping("/books/{id}")
    Book one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    Book replaceAuthor(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(book -> {
                    book.setNativeName(newBook.getNativeName());
                    book.setChineseName(newBook.getChineseName());
                    book.setDetail(newBook.getDetail());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/books/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
