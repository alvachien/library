package com.alvachien.libraryapi.util;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

public final class Constants {
	private Constants() {
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

	// Entity Set Names
	public static final String ES_PERSONS_NAME = "Persons";
}
