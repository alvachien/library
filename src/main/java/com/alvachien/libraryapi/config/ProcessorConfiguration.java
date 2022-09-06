package com.alvachien.libraryapi.config;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.debug.DefaultDebugSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sap.olingo.jpa.processor.core.api.JPAODataRequestContext;
import com.sap.olingo.jpa.processor.core.api.JPAODataServiceContext;
import com.sap.olingo.jpa.processor.core.api.JPAODataSessionContextAccess;

@Configuration
public class ProcessorConfiguration {
  @Value("${odata.jpa.punit_name}")
  private String punit;
  @Value("${odata.jpa.root_packages}") 
  private String rootPackages;
  @Value("${odata.jpa.request_mapping_path}")
  private String requestMappingPath;
  // Database
  final private JPASQLServerDatabaseProcessor processor = new JPASQLServerDatabaseProcessor();

  @Bean
  public JPAODataSessionContextAccess odataSessionContext(@Autowired final EntityManagerFactory emf) throws ODataException {

    return JPAODataServiceContext.with()
        .setPUnit(punit)
        .setEntityManagerFactory(emf)
        .setTypePackage(rootPackages)
        .setRequestMappingPath(requestMappingPath)
        .setDatabaseProcessor(processor)
        .build();
  }
  
  @Bean
  @Scope(scopeName = SCOPE_REQUEST)
  public JPAODataRequestContext odataRequestContext() {

    return JPAODataRequestContext.with()
        .setCUDRequestHandler(new JPACUDRequestHandler())
        .setDebugSupport(new DefaultDebugSupport())
        .build();
  }
}
