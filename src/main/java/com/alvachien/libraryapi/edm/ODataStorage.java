package com.alvachien.libraryapi.edm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.UriParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alvachien.libraryapi.model.Person;
import com.alvachien.libraryapi.repository.PersonRepository;
import com.alvachien.libraryapi.util.Constants;

@Component
public class ODataStorage {
    @Autowired
    private PersonRepository authorRepository;

    public EntityCollection readEntitySetData(EdmEntitySet edmEntitySet) throws ODataApplicationException {

        // actually, this is only required if we have more than one Entity Sets
        if (edmEntitySet.getName().equals(Constants.ES_PERSONS_NAME)) {
            return getAuthorEntityCollection();
        }

        return null;
    }

    public Entity readEntityData(EdmEntitySet edmEntitySet, List<UriParameter> keyParams)
            throws ODataApplicationException {

        EdmEntityType edmEntityType = edmEntitySet.getEntityType();

        // actually, this is only required if we have more than one Entity Type
        if (edmEntityType.getName().equals(Constants.ET_PERSON_NAME)) {
            return getAuthorEntity(edmEntityType, keyParams);
        }

        return null;
    }

    /* INTERNAL */
    private EntityCollection getAuthorEntityCollection() {
        EntityCollection retEntitySet = new EntityCollection();

        for (Person author : this.authorRepository.findAll()) {
            retEntitySet.getEntities().add(getEntityFromAuthor(author));
        }

        return retEntitySet;
    }

    private Entity getEntityFromAuthor(Person author) {        
        final Entity e1 = new Entity().addProperty(new Property("int", "ID", ValueType.PRIMITIVE, author.getId()))
						.addProperty(new Property("String", "NativeName", ValueType.PRIMITIVE, author.getNativeName()))
						.addProperty(new Property("String", "ChineseName", ValueType.PRIMITIVE, author.getChineseName()))
						.addProperty(new Property("String", "Description", ValueType.PRIMITIVE, author.getDetail()));
        e1.setId(createId("Authors", author.getId()));;
        return e1;
    }

    private Entity getAuthorEntity(EdmEntityType edmEntityType, List<UriParameter> keyParams)
            throws ODataApplicationException {

        // the list of entities at runtime
        EntityCollection entitySet = getAuthorEntityCollection();

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

    /* HELPER */
    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + String.valueOf(id) + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }

}
