package com.alvachien.libraryapi.model;

import javax.persistence.AttributeConverter;

public class OrganizationTypeConverter implements AttributeConverter<OrganizationType, Character> {
 
    @Override
    public Character convertToDatabaseColumn(OrganizationType role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public OrganizationType convertToEntityAttribute(Character value) {
        if ( value == null ) {
            return null;
        }
 
        return OrganizationType.fromCode( value );
    }
    
}
