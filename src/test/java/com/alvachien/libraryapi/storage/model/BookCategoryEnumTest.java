package com.alvachien.libraryapi.storage.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Book Category")
public class BookCategoryEnumTest {
    @Test
    public void createNovel() {
        BookCategoryEnum bce = BookCategoryEnum.NOVEL;
        int code = bce.getCode();
        Assertions.assertEquals(code, 1);
    }

    @Test
    public void createOwnDefined() {
        BookCategoryEnum bce = BookCategoryEnum.OWNDEFINED;
        int code = bce.getCode();
        Assertions.assertEquals(code, 0);
    }

    @Test
    public void parseNovel() {
        int codeValue = 1;
        BookCategoryEnumConverter converter = new BookCategoryEnumConverter();
        BookCategoryEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(BookCategoryEnum.NOVEL, bce);
    }

    @Test
    public void parseOWNDEFINED() {
        int codeValue = 0;
        BookCategoryEnumConverter converter = new BookCategoryEnumConverter();
        BookCategoryEnum bce = converter.convertToEntityAttribute(codeValue);
        Assertions.assertEquals(BookCategoryEnum.OWNDEFINED, bce);
    }
}
