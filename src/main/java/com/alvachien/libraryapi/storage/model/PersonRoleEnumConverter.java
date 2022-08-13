package com.alvachien.libraryapi.storage.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PersonRoleEnumConverter implements AttributeConverter<PersonRoleEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(PersonRoleEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public PersonRoleEnum convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }
 
        return PersonRoleEnum.fromCode( value );
    }
}

