package com.alvachien.libraryapi.storage.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmEnumeration;

@EdmEnumeration
public enum PersonRoleEnum {
    OWNDEFINED(0),
    AUTHOR( 1 ),
    ACTOR( 2 ),
    DIRECTOR(3);
 
    private final int code;
 
    PersonRoleEnum(int code) {
        this.code = code;
    }
 
    public static PersonRoleEnum fromCode(int code) {
        if ( code == 1 ) {
            return AUTHOR;
        }
        if ( code == 2 ) {
            return ACTOR;
        }
        if ( code == 3 ) {
            return DIRECTOR;
        }
        if ( code == 0) {
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
