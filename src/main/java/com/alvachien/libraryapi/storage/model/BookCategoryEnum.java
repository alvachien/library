package com.alvachien.libraryapi.storage.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmEnumeration;

@EdmEnumeration
public enum BookCategoryEnum {
    OWNDEFINED(  0 ),
    NOVEL( 1 );
 
    private final int code;
 
    BookCategoryEnum(int code) {
        this.code = code;
    }
 
    public static BookCategoryEnum fromCode(final int code) {
        if ( code == 1 ) {
            return BookCategoryEnum.NOVEL;
        }
        if (code == 0) {
            return BookCategoryEnum.OWNDEFINED;
        }
        
        throw new UnsupportedOperationException("The code " + code + " is not supported!");
    }
 
    public int getCode() {
        return code;
    }    
}
