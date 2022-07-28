package com.alvachien.libraryapi.edm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alvachien.libraryapi.model.Book;
import com.alvachien.libraryapi.model.Person;
import com.alvachien.libraryapi.repository.BookRepository;
import com.alvachien.libraryapi.repository.PersonRepository;
import com.alvachien.libraryapi.util.Constants;

@Component
public class ODataStorage {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BookRepository bookRepository;

    public EntityCollection readEntitySetData(EdmEntitySet edmEntitySet) throws ODataApplicationException {

        // actually, this is only required if we have more than one Entity Sets
        if (edmEntitySet.getName().equals(Constants.ES_PERSONS_NAME)) {
            return getPersonEntityCollection();
        } else if (edmEntitySet.getName().equals(Constants.ES_BOOKS_NAME)) {
            return getBookEntityCollection();
        }

        return null;
    }

    public Entity readEntityData(EdmEntitySet edmEntitySet, List<UriParameter> keyParams)
            throws ODataApplicationException {

        EdmEntityType edmEntityType = edmEntitySet.getEntityType();

        // actually, this is only required if we have more than one Entity Type
        if (edmEntityType.getName().equals(Constants.ET_PERSON_NAME)) {
            return getPersonEntity(edmEntityType, keyParams);
        } else if (edmEntityType.getName().equals(Constants.ET_BOOK_NAME)) {
            return getBookEntity(edmEntityType, keyParams);
        }

        return null;
    }

	public Entity createEntityData(EdmEntitySet edmEntitySet, Entity entityToCreate) {
		
		EdmEntityType edmEntityType = edmEntitySet.getEntityType();
		
		// actually, this is only required if we have more than one Entity Type
		if(edmEntityType.getName().equals(Constants.ET_PERSON_NAME)) {
			// return createPerson(edmEntityType, entityToCreate);
		}
		
		return null;
	}


	/**
	 * This method is invoked for PATCH or PUT requests
	 * */
	public void updateEntityData(EdmEntitySet edmEntitySet, List<UriParameter> keyParams, Entity updateEntity, HttpMethod httpMethod) throws ODataApplicationException{
		
		EdmEntityType edmEntityType = edmEntitySet.getEntityType();

		// actually, this is only required if we have more than one Entity Type
		if(edmEntityType.getName().equals(Constants.ET_PERSON_NAME)){
			// updateProduct(edmEntityType, keyParams, updateEntity, httpMethod);
		}
	}
	

	public void deleteEntityData(EdmEntitySet edmEntitySet, List<UriParameter> keyParams) throws ODataApplicationException{
		
		EdmEntityType edmEntityType = edmEntitySet.getEntityType();
		
		// actually, this is only required if we have more than one Entity Type
		if(edmEntityType.getName().equals(Constants.ET_PERSON_NAME)){
			// deleteProduct(edmEntityType, keyParams);
		}
	}

    /* INTERNAL */
    private EntityCollection getPersonEntityCollection() {
        EntityCollection retEntitySet = new EntityCollection();

        for (Person author : this.personRepository.findAll()) {
            retEntitySet.getEntities().add(getEntityFromPerson(author));
        }

        return retEntitySet;
    }

    private Entity getEntityFromPerson(Person author) {        
        final Entity e1 = new Entity().addProperty(new Property("int", "ID", ValueType.PRIMITIVE, author.getId()))
						.addProperty(new Property("String", "NativeName", ValueType.PRIMITIVE, author.getNativeName()))
						.addProperty(new Property("String", "ChineseName", ValueType.PRIMITIVE, author.getChineseName()))
						.addProperty(new Property("String", "Description", ValueType.PRIMITIVE, author.getDetail()));
        e1.setId(createId("Authors", author.getId()));;
        return e1;
    }

    private Entity getPersonEntity(EdmEntityType edmEntityType, List<UriParameter> keyParams)
            throws ODataApplicationException {

        // the list of entities at runtime
        EntityCollection entitySet = getPersonEntityCollection();

        // /* generic approach to find the requested entity */
        Entity requestedEntity = ODataUtil.findEntity(edmEntityType, entitySet, keyParams);

        if (requestedEntity == null) {
            // this variable is null if our data doesn't contain an entity for the requested
            // key
            // Throw suitable exception
            throw new ODataApplicationException("Entity for requested key doesn't exist",
                    HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
        }

        return requestedEntity;
    }

    private EntityCollection getBookEntityCollection() {
        EntityCollection retEntitySet = new EntityCollection();

        for (Book book : this.bookRepository.findAll()) {
            retEntitySet.getEntities().add(getEntityFromBook(book));
        }

        return retEntitySet;
    }

    private Entity getEntityFromBook(Book book) {        
        final Entity e1 = new Entity().addProperty(new Property("int", "ID", ValueType.PRIMITIVE, book.getId()))
						.addProperty(new Property("String", "NativeName", ValueType.PRIMITIVE, book.getNativeName()))
						.addProperty(new Property("String", "ChineseName", ValueType.PRIMITIVE, book.getChineseName()))
						.addProperty(new Property("String", "Description", ValueType.PRIMITIVE, book.getDetail()))
                        .addProperty(new Property("String", "OriginLanguage", ValueType.PRIMITIVE, book.getOriginLanguage()))
                        .addProperty(new Property("String", "BookLanguage", ValueType.PRIMITIVE, book.getBookLanguage()))
                        .addProperty(new Property("int", "ReleasedYear", ValueType.PRIMITIVE, book.getReleasedYear()));
        e1.setId(createId("Authors", book.getId()));;
        return e1;
    }

    private Entity getBookEntity(EdmEntityType edmEntityType, List<UriParameter> keyParams)
            throws ODataApplicationException {

        // the list of entities at runtime
        EntityCollection entitySet = getBookEntityCollection();

        // /* generic approach to find the requested entity */
        Entity requestedEntity = ODataUtil.findEntity(edmEntityType, entitySet, keyParams);

        if (requestedEntity == null) {
            // this variable is null if our data doesn't contain an entity for the requested
            // key
            // Throw suitable exception
            throw new ODataApplicationException("Entity for requested key doesn't exist",
                    HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
        }

        return requestedEntity;
    }

	private Entity createPerson(EdmEntityType edmEntityType, Entity entity) {
		
        // the ID of the newly created product entity is generated automatically
        long newId = 1;
        while (personIdExists(newId)){
            newId++;
        }
		
	// 	Property idProperty = entity.getProperty("ID");
	// 	if(idProperty != null){
	// 		idProperty.setValue(ValueType.PRIMITIVE, Integer.valueOf(newId));
	// 	}else{
	// 		// as of OData v4 spec, the key property can be omitted from the POST request body
	// 		entity.getProperties().add(new Property(null, "ID", ValueType.PRIMITIVE, newId));
	// 	}
		
	// 	// this.bookRepository.save(entity);

		return entity;
		
	}


	private boolean personIdExists(long id){
        Optional<Book> obook = this.bookRepository.findById(id);
        return obook.isPresent();
	}
	
	
	// private void updateProduct(EdmEntityType edmEntityType, List<UriParameter> keyParams, Entity entity, HttpMethod httpMethod) throws ODataApplicationException{
		
	// 	Entity productEntity = getProduct(edmEntityType, keyParams);
	// 	if(productEntity == null){
	// 		throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
	// 	}
		
	// 	// loop over all properties and replace the values with the values of the given payload
	// 	// Note: ignoring ComplexType, as we don't have it in our odata model
	// 	List<Property> existingProperties = productEntity.getProperties();
	// 	for(Property existingProp : existingProperties){
	// 		String propName = existingProp.getName();
			
	// 		// ignore the key properties, they aren't updateable
	// 		if(isKey(edmEntityType, propName)){
	// 			continue;
	// 		}
			
	// 		Property updateProperty = entity.getProperty(propName); 
	// 		// the request payload might not consider ALL properties, so it can be null
	// 		if(updateProperty == null){
	// 			// if a property has NOT been added to the request payload
	// 			// depending on the HttpMethod, our behavior is different
	// 			if(httpMethod.equals(HttpMethod.PATCH)){
	// 				// as of the OData spec, in case of PATCH, the existing property is not touched
	// 				continue; // do nothing
	// 			}else if(httpMethod.equals(HttpMethod.PUT)){
	// 				// as of the OData spec, in case of PUT, the existing property is set to null (or to default value)
	// 				existingProp.setValue(existingProp.getValueType(), null);
	// 				continue;
	// 			}
	// 		}
			
	// 		// change the value of the properties 
	// 		existingProp.setValue(existingProp.getValueType(), updateProperty.getValue());
	// 	}
	// }

	// private void deleteProduct(EdmEntityType edmEntityType, List<UriParameter> keyParams) throws ODataApplicationException{ 
		
	// 	Entity productEntity = getProduct(edmEntityType, keyParams);
	// 	if(productEntity == null){
	// 		throw new ODataApplicationException("Entity not found", HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ENGLISH);
	// 	}
		
	// 	this.productList.remove(productEntity);
	// }

    /* HELPER */
    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + String.valueOf(id) + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }

}
