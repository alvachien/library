package com.alvachien.libraryapi.model;

public enum OrganizationTypeEnum {
    PUBLISHHOUSE( 'A' ), 
    OWNDEFINED('Z');
 
    private final char code;
 
    OrganizationTypeEnum(char code) {
        this.code = code;
    }
 
    public static OrganizationTypeEnum fromCode(char code) {
        if ( code == 'A' || code == 'a' ) {
            return PUBLISHHOUSE;
        }
        if ( code == 'Z' || code == 'z' ) {
            return OWNDEFINED;
        }
        
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }
 
    public char getCode() {
        return code;
    }    
}
