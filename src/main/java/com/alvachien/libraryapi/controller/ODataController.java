package com.alvachien.libraryapi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.olingo.commons.api.edm.provider.CsdlEdmProvider;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.processor.EntityProcessor;
import org.apache.olingo.server.api.processor.PrimitiveProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.libraryapi.edm.ODataEntityCollectionProcessor;
import com.alvachien.libraryapi.edm.ODataEntityPrimitiveProcessor;
import com.alvachien.libraryapi.edm.ODataEntityProcessor;
import com.alvachien.libraryapi.edm.ODataStorage;

@RestController
@RequestMapping(ODataController.URI)
public class ODataController {
	public static final String URI = "/odata";
	private static final Logger LOG = LoggerFactory.getLogger(ODataController.class);
	private static final long serialVersionUID = 1L;
	private static final String PUNIT_NAME = "LibraryAPI";
	
	@Autowired
	CsdlEdmProvider edmProvider;

	@RequestMapping(value = "*")
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, ODataException {
		// --------------------------------------------------------------------------------------
		// Old version
		// ====> Without JPA
		// --------------------------------------------------------------------------------------
		OData odata = OData.newInstance();
		ServiceMetadata edm = odata.createServiceMetadata(edmProvider, new ArrayList<>());

		try {
			HttpSession session = request.getSession(true);
			ODataStorage storage = (ODataStorage) session.getAttribute(ODataStorage.class.getName());
			if (storage == null) {
				storage = new ODataStorage(odata, edm.getEdm());
				session.setAttribute(ODataStorage.class.getName(), storage);
			}

			// create odata handler and configure it with EdmProvider and Processor
			ODataHttpHandler handler = odata.createHandler(edm);
			handler.register(new ODataEntityCollectionProcessor(storage));
			handler.register(new ODataEntityProcessor(storage));
			handler.register(new ODataEntityPrimitiveProcessor(storage));
			// handler.register(new DemoActionProcessor(storage));
			// handler.register(new DemoBatchProcessor(storage));

			// let the handler do the work
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
		} catch (RuntimeException e) {
			LOG.error("Server Error occurred in ExampleServlet", e);
			throw new ServletException(e);
		}

		// --------------------------------------------------------------------------------------
		// New version
		// ====> With JPA
		// --------------------------------------------------------------------------------------
		// try {

		// 	EntityManagerFactory emf = JPAEntityManagerFactory.getEntityManagerFactory(PUNIT_NAME, new HashMap<String, Object>());
		// 	JPAEdmProvider metadataProvider = new JPAEdmProvider(PUNIT_NAME, emf, null, null);

		// 	OData odata = OData.newInstance();
		// 	ServiceMetadata edm = odata.createServiceMetadata(metadataProvider, new ArrayList<>());
		// 	ODataHttpHandler handler = odata.createHandler(edm);

		// 	// let the handler do the work
		// 	handler.process(new HttpServletRequestWrapper(request) {
		// 		// Spring MVC matches the whole path as the servlet path
		// 		// Olingo wants just the prefix, ie upto /OData/V1.0, so that it
		// 		// can parse the rest of it as an OData path. So we need to override
		// 		// getServletPath()
		// 		@Override
		// 		public String getServletPath() {
		// 			return ODataController.URI;
		// 		}
		// 	}, response);
		// } catch (RuntimeException e) {
		// 	LOG.error("Server Error occurred in ExampleServlet", e);
		// 	throw new ServletException(e);
		// }
	}
}
