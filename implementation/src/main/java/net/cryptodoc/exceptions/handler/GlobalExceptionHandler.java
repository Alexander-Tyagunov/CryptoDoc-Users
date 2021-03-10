package net.cryptodoc.exceptions.handler;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import net.cryptodoc.exceptions.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalExceptionHandler implements DataFetcherExceptionHandler {

    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof UserExistsException) {
            GraphQLError graphQLError = buildGraphQLError(handlerParameters,
                    ErrorType.BAD_REQUEST,
                    "User already exists");
            return DataFetcherExceptionHandlerResult
                    .newResult()
                    .error(graphQLError)
                    .build();
        }

        if (handlerParameters.getException() instanceof UserEmailNotFoundException) {
            GraphQLError graphQLError = buildGraphQLError(handlerParameters,
                    ErrorType.NOT_FOUND,
                    "User not found");
            return DataFetcherExceptionHandlerResult
                    .newResult()
                    .error(graphQLError)
                    .build();
        }

        if (handlerParameters.getException() instanceof UserIdNotFoundException) {
            GraphQLError graphQLError = buildGraphQLError(handlerParameters,
                    ErrorType.NOT_FOUND,
                    "User not found");
            return DataFetcherExceptionHandlerResult
                    .newResult()
                    .error(graphQLError)
                    .build();
        }

        if (handlerParameters.getException() instanceof NoValuePresentException) {
            GraphQLError graphQLError = buildGraphQLError(handlerParameters,
                    ErrorType.BAD_REQUEST,
                    "No value present");
            return DataFetcherExceptionHandlerResult
                    .newResult()
                    .error(graphQLError)
                    .build();
        }

        if (handlerParameters.getException() instanceof SignatureIsNotValidException) {
            GraphQLError graphQLError = buildGraphQLError(handlerParameters,
                    ErrorType.PERMISSION_DENIED,
                    "Signature not valid");
            return DataFetcherExceptionHandlerResult
                    .newResult()
                    .error(graphQLError)
                    .build();
        }

        if (handlerParameters.getException() instanceof SignatureIsValidException) {
            GraphQLError graphQLError = buildGraphQLError(handlerParameters,
                    ErrorType.BAD_REQUEST,
                    "Signature is valid");
            return DataFetcherExceptionHandlerResult
                    .newResult()
                    .error(graphQLError)
                    .build();
        }

        return defaultHandler.onException(handlerParameters);
    }

    private GraphQLError buildGraphQLError(DataFetcherExceptionHandlerParameters handlerParameters,
                                           ErrorType errorType,
                                           String errorMessage) {
        Map<String, Object> debugInfo = new HashMap<>();
        debugInfo.put("Error message", handlerParameters.getException().getMessage());
        return TypedGraphQLError
                .newBuilder()
                .errorType(errorType)
                .message(errorMessage)
                .debugInfo(debugInfo)
                .path(handlerParameters.getPath())
                .build();
    }
}
