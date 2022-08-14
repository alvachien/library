package com.alvachien.libraryapi.storage.model;
import javax.persistence.AttributeConverter;

import javax.persistence.Converter;

@Converter(autoApply = false)
public class BookCategoryEnumConverter implements AttributeConverter<BookCategoryEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(final BookCategoryEnum role) {
        return role == null ? null : role.getCode();
    }
 
    @Override
    public BookCategoryEnum convertToEntityAttribute(final Integer value) {
        return value == null ? null : BookCategoryEnum.fromCode(value);
    }    
}
