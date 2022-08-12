package com.alvachien.libraryapi.model;

public enum OrganizationTypeEnum {
    PUBLISHHOUSE( 1 ), 
    OWNDEFINED(-1);
 
    private final int code;
 
    OrganizationTypeEnum(int code) {
        this.code = code;
    }
 
    public static OrganizationTypeEnum fromCode(int code) {
        if ( code == 1) {
            return PUBLISHHOUSE;
        }
        if ( code == -1 ) {
            return OWNDEFINED;
        }
        
        throw new UnsupportedOperationException(
            "The code " + code + " is not supported!"
        );
    }
 
    public int getCode() {
        return code;
    }    
}
