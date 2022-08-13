package com.alvachien.libraryapi.storage.model;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
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
