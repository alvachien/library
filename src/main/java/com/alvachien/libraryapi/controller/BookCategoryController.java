package com.alvachien.libraryapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.exception.BookCategoryNotFoundException;
import com.alvachien.libraryapi.model.BookCategory;
import com.alvachien.libraryapi.service.StorageService;

@RestController
public class BookCategoryController {
    private StorageService storage;

    public BookCategoryController(StorageService storage) {
        this.storage = storage;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/bookcategories")
    Iterable<BookCategory> all() {
        return this.storage.findBookCategories();
    }
    // end::get-aggregate-root[]

    @PostMapping("/bookcategories")
    BookCategory createObject(@RequestBody BookCategory newbook) {
        return this.storage.saveBookCategory(newbook);
    }

    // Single item
    @GetMapping("/bookcategories/{id}")
    BookCategory one(@PathVariable Long id) {

        return this.storage.findBookCategoryByID(id)
                .orElseThrow(() -> new BookCategoryNotFoundException(id));
    }

    @PutMapping("/bookcategories/{id}")
    BookCategory updateBookCategory(@RequestBody BookCategory newBook, @PathVariable Long id) {

        return this.storage.findBookCategoryByID(id)
                .map(bookctgy -> {
                    bookctgy.setCategoryName(newBook.getCategoryName());
                    bookctgy.setCategoryValue(newBook.getCategoryValue());
                    return this.storage.saveBookCategory(bookctgy);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return storage.saveBookCategory(newBook);
                });
    }

    @DeleteMapping("/bookcategories/{id}")
    void deleteEmployee(@PathVariable Long id) {
        this.storage.deleteBookCategoryByID(id);
    }
    
}
