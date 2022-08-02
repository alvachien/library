package com.alvachien.libraryapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.BookNotFoundException;
import com.alvachien.libraryapi.model.Book;
import com.alvachien.libraryapi.service.StorageService;

@RestController
public class BookController {
    private StorageService storage;

    public BookController(StorageService storage) {
        this.storage = storage;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    Iterable<Book> all() {
        return this.storage.findBooks();
    }
    // end::get-aggregate-root[]

    @PostMapping("/books")
    Book newBook(@RequestBody Book newbook) {
        return this.storage.saveBook(newbook);
    }

    // Single item
    @GetMapping("/books/{id}")
    Book one(@PathVariable Long id) {

        return this.storage.findBookByID(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    Book replaceAuthor(@RequestBody Book newBook, @PathVariable Long id) {

        return this.storage.findBookByID(id)
                .map(book -> {
                    book.setNativeName(newBook.getNativeName());
                    book.setChineseName(newBook.getChineseName());
                    book.setDetail(newBook.getDetail());
                    return this.storage.saveBook(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return storage.saveBook(newBook);
                });
    }

    @DeleteMapping("/books/{id}")
    void deleteEmployee(@PathVariable Long id) {
        this.storage.deleteBookByID(id);
    }
}
