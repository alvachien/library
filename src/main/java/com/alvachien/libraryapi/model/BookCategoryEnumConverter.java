package com.alvachien.libraryapi.model;
import javax.persistence.AttributeConverter;

public class BookCategoryEnumConverter implements AttributeConverter<BookCategoryEnum, Character> {
 
    @Override
    public Character convertToDatabaseColumn(BookCategoryEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public BookCategoryEnum convertToEntityAttribute(Character value) {
        if ( value == null ) {
            return null;
        }
 
        return BookCategoryEnum.fromCode( value );
    }
    
}
