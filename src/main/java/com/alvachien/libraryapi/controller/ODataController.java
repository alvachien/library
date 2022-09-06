package com.alvachien.libraryapi.controller;

import com.sap.olingo.jpa.processor.core.api.JPAClaimsPair;
import com.sap.olingo.jpa.processor.core.api.JPAODataClaimsProvider;
import com.sap.olingo.jpa.processor.core.api.JPAODataRequestContext;
import com.sap.olingo.jpa.processor.core.api.JPAODataRequestHandler;
import com.sap.olingo.jpa.processor.core.api.JPAODataSessionContextAccess;

import org.apache.olingo.commons.api.ex.ODataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${odata.jpa.request_mapping_path}/")
@RequestScope
public class ODataController {

    @Autowired
    private JPAODataSessionContextAccess odataSessionContext;
    @Autowired
    private JPAODataRequestContext odataRequestContext;

    @RequestMapping(value = "**", method = { RequestMethod.GET, RequestMethod.PATCH, // NOSONAR
            RequestMethod.POST, RequestMethod.DELETE })
    public void crud(final HttpServletRequest req, final HttpServletResponse resp) throws ODataException {

        System.out.println(odataSessionContext.getDatabaseProcessor().getClass().getName());
        System.out.println(odataRequestContext.getCUDRequestHandler().getClass().getName());

        new JPAODataRequestHandler(odataSessionContext, odataRequestContext).process(req, resp);
    }
}
