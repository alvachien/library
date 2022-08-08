package com.alvachien.libraryapi.edm;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

public final class ODataConstants {
	private ODataConstants() {
		throw new IllegalStateException("Utility class");
	}

	// Service Namespace
	public static final String NAMESPACE = "AlvaChien.Library";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_PERSON_NAME = "Person";
	public static final FullQualifiedName ET_PERSON_FQN = new FullQualifiedName(NAMESPACE, ET_PERSON_NAME);
	public static final String ET_BOOK_NAME = "Book";
	public static final FullQualifiedName ET_BOOK_FQN = new FullQualifiedName(NAMESPACE, ET_BOOK_NAME);
	public static final String ET_BOOKCATEGORY_NAME = "Book";
	public static final FullQualifiedName ET_BOOKCATEGORY_FQN = new FullQualifiedName(NAMESPACE, ET_BOOKCATEGORY_NAME);
	public static final String ET_PERSONROLE_NAME = "PersonRole";
	public static final FullQualifiedName ET_PERSONROLE_FQN = new FullQualifiedName(NAMESPACE, ET_PERSONROLE_NAME);
	public static final String ET_ORGANIZATION_NAME = "Organization";
	public static final FullQualifiedName ET_ORGANIZATION_FQN = new FullQualifiedName(NAMESPACE, ET_ORGANIZATION_NAME);

	// Entity Set Names
	public static final String ES_PERSONS_NAME = "Persons";
	public static final String ES_BOOKS_NAME = "Books";
	public static final String ES_BOOKCATEGORIESS_NAME = "BookCategories";
	public static final String ES_PERSONROLES_NAME = "PersonRoles";
	public static final String ES_ORGANIZATION_NAME = "Organizations";

	// Action
	public static final String ACTION_RESET = "Reset";
	public static final FullQualifiedName ACTION_RESET_FQN = new FullQualifiedName(NAMESPACE, ACTION_RESET);

	// Function
	public static final String FUNCTION_COUNT_CATEGORIES = "CountCategories";
	public static final FullQualifiedName FUNCTION_COUNT_CATEGORIES_FQN = new FullQualifiedName(NAMESPACE,
			FUNCTION_COUNT_CATEGORIES);

	// Function/Action Parameters
	public static final String PARAMETER_AMOUNT = "Amount";
}
