package com.alvachien.libraryapi.storage.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmEnumeration;

@EdmEnumeration
public enum BookCategoryEnum {
    NOVEL( 1 ),
    OWNDEFINED(-1);
 
    private final int code;
 
    BookCategoryEnum(int code) {
        this.code = code;
    }
 
    public static BookCategoryEnum fromCode(int code) {
        if ( code == 1 ) {
            return NOVEL;
        }
        if (code == -1) {
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
