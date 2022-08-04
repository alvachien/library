package com.alvachien.libraryapi.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;

import com.alvachien.libraryapi.model.BookCategory;
import com.alvachien.libraryapi.model.BookCategoryEnum;

@SpringBootTest
//@ActiveProfiles("unittest")
public class BookCategoryControllerTest {
    @Autowired
    private BookCategoryController controller;
    
    @BeforeEach
    void beforeEach() {        
    }

    @Test
    void createBookShallWork() {
        BookCategory ctgy = new BookCategory();
        ctgy.setCategoryValue(BookCategoryEnum.OWNDEFINED);
        ctgy.setCategoryName("Test");

        // Create
        BookCategory rst = controller.createObject(ctgy);
        assertTrue(rst != null);
        assertTrue(rst.getCategoryName().equals(ctgy.getCategoryName()));
        assertTrue(rst.getCategoryValue().equals(BookCategoryEnum.OWNDEFINED));

    }
}
