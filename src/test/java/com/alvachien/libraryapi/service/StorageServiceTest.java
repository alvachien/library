package com.alvachien.libraryapi.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;

import com.alvachien.libraryapi.model.Book;

@SpringBootTest
//@ActiveProfiles("unittest")
public class StorageServiceTest {
    @Autowired
    private StorageService service;

    @BeforeEach
    void beforeEach() {
    }

    @Test
    void createBookShallWork() {
        Book newbook = new Book();
        newbook.setChineseName("test");
        newbook.setNativeName("test");

        // Create
        Book rst = service.saveBook(newbook);        
        assertTrue(rst != null);
        assertTrue(rst.getChineseName().equals(newbook.getChineseName()));
        assertTrue(rst.getNativeName().equals(newbook.getNativeName()));

        // List
        List<Book> books = service.findBooks();
        assertTrue(books.size() == 1);

        // Create second book
        newbook = new Book();
        newbook.setChineseName("test2");
        newbook.setNativeName("test2");
        rst = service.saveBook(newbook);
        assertTrue(rst != null);
        assertTrue(rst.getChineseName().equals(newbook.getChineseName()));
        assertTrue(rst.getNativeName().equals(newbook.getNativeName()));

        // List
        books = service.findBooks();
        assertTrue(books.size() == 2);

        // Change the second book
        newbook.setNativeName("test2 - changed");
        rst = service.saveBook(newbook);

        service.deleteAllBooks();
    }
}
