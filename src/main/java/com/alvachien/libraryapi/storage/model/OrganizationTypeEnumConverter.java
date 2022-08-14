package com.alvachien.libraryapi.storage.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrganizationTypeEnumConverter implements AttributeConverter<OrganizationTypeEnum, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(OrganizationTypeEnum evalue) {
        return evalue == null ? null : evalue.getCode();
    }
 
    @Override
    public OrganizationTypeEnum convertToEntityAttribute(Integer value) {
        return value == null ? null : OrganizationTypeEnum.fromCode( value );
    }    
}
