package com.alvachien.libraryapi.model;

import javax.persistence.AttributeConverter;

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

