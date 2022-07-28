package com.alvachien.libraryapi.model;

public enum OrganizationType {
    PUBLISHHOUSE( 'A' );
 
    private final char code;
 
    OrganizationType(char code) {
        this.code = code;
    }
 
    public static OrganizationType fromCode(char code) {
        if ( code == 'A' || code == 'a' ) {
            return PUBLISHHOUSE;
        }
        
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }
 
    public char getCode() {
        return code;
    }    
}
