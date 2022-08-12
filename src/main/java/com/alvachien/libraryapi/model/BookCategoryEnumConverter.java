package com.alvachien.libraryapi.model;
import javax.persistence.AttributeConverter;

public class BookCategoryEnumConverter implements AttributeConverter<BookCategoryEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(BookCategoryEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public BookCategoryEnum convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }
 
        return BookCategoryEnum.fromCode( value );
    }
    
}
