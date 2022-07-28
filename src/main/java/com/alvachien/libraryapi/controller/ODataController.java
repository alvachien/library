package com.alvachien.libraryapi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.commons.api.edm.provider.CsdlEdmProvider;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.processor.EntityProcessor;
import org.apache.olingo.server.api.processor.PrimitiveProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ODataController.URI)
public class ODataController {
	public static final String URI = "/odata";

	@Autowired
	CsdlEdmProvider edmProvider;
	
	@Autowired
	EntityCollectionProcessor collprocessor;

	@Autowired
	EntityProcessor processor;
	
	@Autowired
	PrimitiveProcessor primitiveProcessor;
	
	@RequestMapping(value = "*")
	public void process(HttpServletRequest request, HttpServletResponse response) {
		OData odata = OData.newInstance();
		ServiceMetadata edm = odata.createServiceMetadata(edmProvider, new ArrayList<>());
		ODataHttpHandler handler = odata.createHandler(edm);
		handler.register(collprocessor);
		handler.register(processor);
		handler.register(primitiveProcessor);

		handler.process(new HttpServletRequestWrapper(request) {
			// Spring MVC matches the whole path as the servlet path
			// Olingo wants just the prefix, ie upto /OData/V1.0, so that it
			// can parse the rest of it as an OData path. So we need to override
			// getServletPath()
			@Override
			public String getServletPath() {
				return ODataController.URI;
			}
		}, response);
	}    
}
