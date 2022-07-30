package com.alvachien.libraryapi.edm;

import java.util.Locale;
import java.util.Map;

import org.apache.olingo.commons.api.data.Parameter;
import org.apache.olingo.commons.api.edm.EdmAction;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.deserializer.ODataDeserializer;
import org.apache.olingo.server.api.processor.ActionVoidProcessor;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResourceAction;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

//@Component
public class ODataActionProcessor implements ActionVoidProcessor {
    private OData odata;
    //@Autowired
    private ODataStorage storage;
  
    @Override
    public void init(final OData odata, final ServiceMetadata serviceMetadata) {
      this.odata = odata;
    }
  
    @Override
    public void processActionVoid(ODataRequest request, ODataResponse response, UriInfo uriInfo,
        ContentType requestFormat) throws ODataApplicationException, ODataLibraryException {
  
      // 1st Get the action from the resource path
      final EdmAction edmAction = ((UriResourceAction) uriInfo.asUriInfoResource().getUriResourceParts()
                                                                                  .get(0)).getAction();
  
      // 2nd Deserialize the parameter
      // In our case there is only one action. So we can be sure that parameter "Amount" has been provided by the client
      if (requestFormat == null) {
        throw new ODataApplicationException("The content type has not been set in the request.",
            HttpStatusCode.BAD_REQUEST.getStatusCode(), Locale.ROOT);
      }
      
      final ODataDeserializer deserializer = odata.createDeserializer(requestFormat);
      final Map<String, Parameter> actionParameter = deserializer.actionParameters(request.getBody(), edmAction)
          .getActionParameters();
      final Parameter parameterAmount = actionParameter.get(ODataConstants.PARAMETER_AMOUNT);
      
      // The parameter amount is nullable
      if(parameterAmount.isNull()) {
        storage.resetDataSet();
      } else {
        final Integer amount = (Integer) parameterAmount.asPrimitive();
        storage.resetDataSet(amount);
      }
  
      response.setStatusCode(HttpStatusCode.NO_CONTENT.getStatusCode());
    }      
}
