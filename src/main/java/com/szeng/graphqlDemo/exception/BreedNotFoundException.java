package com.szeng.graphqlDemo.exception;

import graphql.ErrorType;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreedNotFoundException extends RuntimeException implements GraphQLError {
    private Map<String, Object> extension = new HashMap<>();

    public BreedNotFoundException(String message, String invalidBreed) {
        super(message);
        extension.put("invalidDogBreed", invalidBreed);
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
        return extension;
    }
}
