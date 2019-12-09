package com.szeng.graphqlDemo.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Note that to ensure the exception work properly with GraphQL, we will want to have the
 *  exception implements `GraphQLError`, and no longer use the `@ResponseStatus` annotation that
 *  we used with the REST API.
 */
public class DogNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public DogNotFoundException(String message, Long invalidDogId) {
        super(message);
        extensions.put("invalidDogId", invalidDogId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
