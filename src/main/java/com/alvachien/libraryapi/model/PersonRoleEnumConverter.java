package com.alvachien.libraryapi.model;

import javax.persistence.AttributeConverter;

public class PersonRoleEnumConverter implements AttributeConverter<PersonRoleEnum, Character> {
 
    @Override
    public Character convertToDatabaseColumn(PersonRoleEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public PersonRoleEnum convertToEntityAttribute(Character value) {
        if ( value == null ) {
            return null;
        }
 
        return PersonRoleEnum.fromCode( value );
    }
}

