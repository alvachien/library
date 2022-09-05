package com.alvachien.libraryapi.util;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction.ReturnType;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;

public class FuncImpl implements ODataFunction  {
    public FuncImpl() {
		super();
	}

	@EdmFunction(name = "", returnType = @ReturnType)
	public Integer sum(@EdmParameter(name = "Summand1") int a, @EdmParameter(name = "Summand2") int b) {
		return a + b;
	}    
}
