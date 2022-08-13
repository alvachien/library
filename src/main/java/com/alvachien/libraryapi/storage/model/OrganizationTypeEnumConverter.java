package com.alvachien.libraryapi.storage.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrganizationTypeEnumConverter implements AttributeConverter<OrganizationTypeEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(OrganizationTypeEnum role) {
        if ( role == null ) {
            return null;
        }
 
        return role.getCode();
    }
 
    @Override
    public OrganizationTypeEnum convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }
 
        return OrganizationTypeEnum.fromCode( value );
    }
    
}
