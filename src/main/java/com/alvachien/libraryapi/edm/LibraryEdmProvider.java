package com.alvachien.libraryapi.edm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlAction;
import org.apache.olingo.commons.api.edm.provider.CsdlActionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumMember;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumType;
import org.apache.olingo.commons.api.edm.provider.CsdlFunction;
import org.apache.olingo.commons.api.edm.provider.CsdlFunctionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationPropertyBinding;
import org.apache.olingo.commons.api.edm.provider.CsdlParameter;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlReturnType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;
import org.springframework.stereotype.Component;

@Component
public class LibraryEdmProvider extends CsdlAbstractEdmProvider {
    @Override
    public List<CsdlAction> getActions(final FullQualifiedName actionName) {
        if (actionName.equals(ODataConstants.ACTION_RESET_FQN)) {
            // It is allowed to overload actions, so we have to provide a list of Actions
            // for each action name
            final List<CsdlAction> actions = new ArrayList<CsdlAction>();

            // Create parameters
            final List<CsdlParameter> parameters = new ArrayList<CsdlParameter>();
            final CsdlParameter parameter = new CsdlParameter();
            parameter.setName(ODataConstants.PARAMETER_AMOUNT);
            parameter.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            parameters.add(parameter);

            // Create the Csdl Action
            final CsdlAction action = new CsdlAction();
            action.setName(ODataConstants.ACTION_RESET_FQN.getName());
            action.setParameters(parameters);
            actions.add(action);

            return actions;
        }

        return null;
    }

    @Override
    public CsdlActionImport getActionImport(final FullQualifiedName entityContainer, final String actionImportName) {
        if (entityContainer.equals(ODataConstants.CONTAINER)) {
            if (actionImportName.equals(ODataConstants.ACTION_RESET_FQN.getName())) {
                return new CsdlActionImport()
                        .setName(actionImportName)
                        .setAction(ODataConstants.ACTION_RESET_FQN);
            }
        }

        return null;
    }

    @Override
    public List<CsdlFunction> getFunctions(final FullQualifiedName functionName) {
        // if (functionName.equals(FUNCTION_COUNT_CATEGORIES_FQN)) {
        // // It is allowed to overload functions, so we have to provide a list of
        // // functions for each function name
        // final List<CsdlFunction> functions = new ArrayList<CsdlFunction>();

        // // Create the parameter for the function
        // final CsdlParameter parameterAmount = new CsdlParameter();
        // parameterAmount.setName(PARAMETER_AMOUNT);
        // parameterAmount.setNullable(false);
        // parameterAmount.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

        // // Create the return type of the function
        // final CsdlReturnType returnType = new CsdlReturnType();
        // returnType.setCollection(true);
        // returnType.setType(ET_CATEGORY_FQN);

        // // Create the function
        // final CsdlFunction function = new CsdlFunction();
        // function.setName(FUNCTION_COUNT_CATEGORIES_FQN.getName())
        // .setParameters(Arrays.asList(parameterAmount))
        // .setReturnType(returnType);
        // functions.add(function);

        // return functions;
        // }

        return null;
    }

    @Override
    public CsdlFunctionImport getFunctionImport(FullQualifiedName entityContainer, String functionImportName) {
        if (entityContainer.equals(ODataConstants.CONTAINER)) {
            // if (functionImportName.equals(FUNCTION_COUNT_CATEGORIES_FQN.getName())) {
            // return new CsdlFunctionImport()
            // .setName(functionImportName)
            // .setFunction(FUNCTION_COUNT_CATEGORIES_FQN)
            // .setEntitySet(ES_CATEGORIES_NAME)
            // .setIncludeInServiceDocument(true);
            // }
        }

        return null;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() throws ODataException {
        // create EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
        entitySets.add(getEntitySet(ODataConstants.CONTAINER, ODataConstants.ES_PERSONS_NAME));
        entitySets.add(getEntitySet(ODataConstants.CONTAINER, ODataConstants.ES_BOOKS_NAME));
        entitySets.add(getEntitySet(ODataConstants.CONTAINER, ODataConstants.ES_BOOKCATEGORIESS_NAME));
        entitySets.add(getEntitySet(ODataConstants.CONTAINER, ODataConstants.ES_PERSONROLES_NAME));
        entitySets.add(getEntitySet(ODataConstants.CONTAINER, ODataConstants.ES_ORGANIZATIONS_NAME));
        entitySets.add(getEntitySet(ODataConstants.CONTAINER, ODataConstants.ES_ORGANIZATIONTYPES_NAME));

        // // Create function imports
        // List<CsdlFunctionImport> functionImports = new
        // ArrayList<CsdlFunctionImport>();
        // functionImports.add(getFunctionImport(CONTAINER, FUNCTION_COUNT_CATEGORIES));

        // // Create action imports
        // List<CsdlActionImport> actionImports = new ArrayList<CsdlActionImport>();
        // actionImports.add(getActionImport(CONTAINER, ACTION_RESET));

        // create EntityContainer
        CsdlEntityContainer entityContainer = new CsdlEntityContainer();
        entityContainer.setName(ODataConstants.CONTAINER_NAME);
        // entityContainer.setActionImports(actionImports);
        // entityContainer.setFunctionImports(functionImports);
        entityContainer.setEntitySets(entitySets);

        return entityContainer;
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) throws ODataException {
        if (entityContainerName == null || entityContainerName.equals(ODataConstants.CONTAINER)) {
            CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
            entityContainerInfo.setContainerName(ODataConstants.CONTAINER);
            return entityContainerInfo;
        }

        return null;
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) throws ODataException {
        if (entityContainer.equals(ODataConstants.CONTAINER)) {
            if (entitySetName.equals(ODataConstants.ES_PERSONS_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ODataConstants.ES_PERSONS_NAME);
                entitySet.setType(ODataConstants.ET_PERSON_FQN);

                CsdlNavigationPropertyBinding navAuthorBinding = new CsdlNavigationPropertyBinding();
                navAuthorBinding.setPath(ODataConstants.ET_PERSON_NAME); // the path from entity type to navigation property
                navAuthorBinding.setTarget(ODataConstants.ET_BOOK_NAME); // target entitySet, where the nav prop points to
                List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
                navPropBindingList.add(navAuthorBinding);
                entitySet.setNavigationPropertyBindings(navPropBindingList);

                return entitySet;
            } else if (entitySetName.equals(ODataConstants.ES_BOOKS_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ODataConstants.ES_BOOKS_NAME);
                entitySet.setType(ODataConstants.ET_BOOK_FQN);
                CsdlNavigationPropertyBinding navAuthorBinding = new CsdlNavigationPropertyBinding();
                navAuthorBinding.setPath(ODataConstants.ET_BOOK_NAME); // the path from entity type to navigation property
                navAuthorBinding.setTarget(ODataConstants.ET_PERSON_NAME); // target entitySet, where the nav prop points to
                List<CsdlNavigationPropertyBinding> navPropBindingList = new ArrayList<CsdlNavigationPropertyBinding>();
                navPropBindingList.add(navAuthorBinding);
                entitySet.setNavigationPropertyBindings(navPropBindingList);

                return entitySet;
            }
        }

        return null;
    }

    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {
        // this method is called for one of the EntityTypes that are configured in the
        // Schema
        if (entityTypeName.equals(ODataConstants.ET_PERSON_FQN)) {

            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID")
                .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty nativeName = new CsdlProperty().setName("NativeName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty chineseName = new CsdlProperty().setName("ChineseName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty detail = new CsdlProperty().setName("Detail")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            
            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ODataConstants.ET_PERSON_NAME);
            entityType.setProperties(Arrays.asList(id, nativeName, chineseName, detail));
            entityType.setKey(Collections.singletonList(propertyRef));
            entityType.setNavigationProperties(Arrays.asList(
                new CsdlNavigationProperty().setName("Books").setType(ODataConstants.ET_BOOK_FQN).setCollection(true),
                new CsdlNavigationProperty().setName("TranslatedBooks").setType(ODataConstants.ET_BOOK_FQN).setCollection(true),
                new CsdlNavigationProperty().setName("Roles").setType(ODataConstants.ET_PERSONROLE_FQN).setCollection(true)
                ));

            return entityType;
        } else if (entityTypeName.equals(ODataConstants.ET_PERSONROLE_FQN)) {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID")
                .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty roleName = new CsdlProperty().setName("RoleName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty roleValue = new CsdlProperty().setName("RoleValue")
                .setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName());
            
            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ODataConstants.ET_PERSONROLE_NAME);
            entityType.setProperties(Arrays.asList(id, roleName, roleValue));
            entityType.setKey(Collections.singletonList(propertyRef));
            entityType.setNavigationProperties(Arrays.asList(
                new CsdlNavigationProperty().setName("Persons").setType(ODataConstants.ET_PERSON_FQN).setCollection(true)
                ));

            return entityType;

        } else if (entityTypeName.equals(ODataConstants.ET_ORGANIZATIONTYPE_FQN)) {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID")
                .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty roleName = new CsdlProperty().setName("RoleName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty roleValue = new CsdlProperty().setName("RoleValue")
                .setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName());
            
            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ODataConstants.ET_ORGANIZATIONTYPE_NAME);
            entityType.setProperties(Arrays.asList(id, roleName, roleValue));
            entityType.setKey(Collections.singletonList(propertyRef));
            entityType.setNavigationProperties(Arrays.asList(
                new CsdlNavigationProperty().setName("Organizations").setType(ODataConstants.ET_ORGANIZATION_FQN).setCollection(true)
                ));

            return entityType;
        } else if (entityTypeName.equals(ODataConstants.ET_ORGANIZATION_FQN)) {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID")
                .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty nativeName = new CsdlProperty().setName("NativeName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty chineseName = new CsdlProperty().setName("ChineseName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty detail = new CsdlProperty().setName("Detail")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            
            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ODataConstants.ET_PERSON_NAME);
            entityType.setProperties(Arrays.asList(id, nativeName, chineseName, detail));
            entityType.setKey(Collections.singletonList(propertyRef));
            entityType.setNavigationProperties(Arrays.asList(
                new CsdlNavigationProperty().setName("OrganizationTypes").setType(ODataConstants.ET_ORGANIZATIONTYPE_FQN).setCollection(true)
                ));

            return entityType;

        } else if (entityTypeName.equals(ODataConstants.ET_BOOKCATEGORY_FQN)) {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID")
                .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty roleName = new CsdlProperty().setName("RoleName")
                .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty roleValue = new CsdlProperty().setName("RoleValue")
                .setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName());
            
            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ODataConstants.ET_BOOKCATEGORY_NAME);
            entityType.setProperties(Arrays.asList(id, roleName, roleValue));
            entityType.setKey(Collections.singletonList(propertyRef));
            entityType.setNavigationProperties(Arrays.asList(
                new CsdlNavigationProperty().setName("Books").setType(ODataConstants.ET_BOOK_FQN).setCollection(true)
                ));

            return entityType;

        } else if (entityTypeName.equals(ODataConstants.ET_BOOK_FQN)) {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID")
                    .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty nativeName = new CsdlProperty().setName("NativeName")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty chineseName = new CsdlProperty().setName("ChineseName")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty detail = new CsdlProperty().setName("Detail")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty originLanguage = new CsdlProperty().setName("OriginLanguage")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty bookLanguage = new CsdlProperty().setName("BookLanguage")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty releasedYear = new CsdlProperty().setName("ReleasedYear")
                    .setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ODataConstants.ET_BOOK_NAME);
            entityType.setProperties(
                    Arrays.asList(id, nativeName, chineseName, detail, originLanguage, bookLanguage, releasedYear));
            entityType.setKey(Collections.singletonList(propertyRef));
            entityType.setNavigationProperties(Arrays.asList(
                new CsdlNavigationProperty().setName("Authors").setType(ODataConstants.ET_PERSON_FQN).setCollection(true),
                new CsdlNavigationProperty().setName("Translators").setType(ODataConstants.ET_PERSON_FQN).setCollection(true)
                ));

            return entityType;
        }

        return null;
    }

    @Override
    public List<CsdlSchema> getSchemas() throws ODataException {
        // create Schema
        CsdlSchema schema = new CsdlSchema();
        schema.setNamespace(ODataConstants.NAMESPACE);

        // add enum
        List<CsdlEnumType> enumTypes = new ArrayList<>();
        CsdlEnumType etype = new CsdlEnumType().setName(ODataConstants.ET_BOOKCATEGORYENUM_NAME).setUnderlyingType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
        final List<CsdlEnumMember> members = Arrays.asList(
            new CsdlEnumMember().setName("NOVEL").setValue("1"),
            new CsdlEnumMember().setName("OWNDEFINED").setValue("-1"));
        etype.setMembers(members);
        enumTypes.add(etype);
        CsdlEnumType etype2 = new CsdlEnumType().setName(ODataConstants.ET_PERSONROLEENUM_NAME);
        final List<CsdlEnumMember> members2 = Arrays.asList(
            new CsdlEnumMember().setName("AUTHOR").setValue("1"),
            new CsdlEnumMember().setName("ACTOR").setValue("2"),
            new CsdlEnumMember().setName("DIRECTOR").setValue("3"),
            new CsdlEnumMember().setName("OWNDEFINED").setValue("-1"));
        etype2.setMembers(members2);
        enumTypes.add(etype2);
        CsdlEnumType etype3 = new CsdlEnumType().setName(ODataConstants.ET_ORGANIZATIONTYPEENUM_NAME);
        final List<CsdlEnumMember> members3 = Arrays.asList(
            new CsdlEnumMember().setName("PUBLISHHOUSE").setValue("1"),
            new CsdlEnumMember().setName("OWNDEFINED").setValue("-1"));
        etype3.setMembers(members3);
        enumTypes.add(etype3);
        schema.setEnumTypes(enumTypes);

        // add EntityTypes
        List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
        entityTypes.add(getEntityType(ODataConstants.ET_PERSON_FQN));
        entityTypes.add(getEntityType(ODataConstants.ET_PERSONROLE_FQN));
        entityTypes.add(getEntityType(ODataConstants.ET_BOOK_FQN));
        entityTypes.add(getEntityType(ODataConstants.ET_BOOKCATEGORY_FQN));
        entityTypes.add(getEntityType(ODataConstants.ET_ORGANIZATION_FQN));
        entityTypes.add(getEntityType(ODataConstants.ET_ORGANIZATIONTYPE_FQN));
        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // finally
        List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
        schemas.add(schema);

        return schemas;
    }
}
