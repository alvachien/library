package com.alvachien.libraryapi.storage.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PersonRoleEnumConverter implements AttributeConverter<PersonRoleEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(PersonRoleEnum role) {
        return role == null ? null : role.getCode();
    }
 
    @Override
    public PersonRoleEnum convertToEntityAttribute(Integer value) {
        return value == null ? null : PersonRoleEnum.fromCode( value );
    }
}

