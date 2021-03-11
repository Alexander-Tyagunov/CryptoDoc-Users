package net.cryptodoc.datafetchers;

import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.types.errors.ErrorType;
import graphql.ExecutionResult;
import ma.glasnost.orika.MapperFacade;
import net.cryptodoc.IntegrationTest;
import net.cryptodoc.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserDataFetcherImplTest extends IntegrationTest {

    @Autowired
    DgsQueryExecutor queryExecutor;
    @Autowired
    MapperFacade mapperFacade;

    @Test
    @DisplayName("Should return list of existing users")
    void getUsers() {
        List<User> users = queryExecutor.executeAndExtractJsonPathAsObject(
                "query getAllUsers { getUsers {id, firstName, lastName, email, isSignatureReady} }",
                "data.getUsers",
                new TypeRef<List<User>>() {});
        // Two users are inserted with setUp method in Integration test
        assertFalse(users.isEmpty());
    }

    @Test
    @DisplayName("Get user by EMAIL, Should return user")
    void getUserByEmail() {
        User expected = createUser();
        User actual = queryExecutor.executeAndExtractJsonPathAsObject(
                "query getUserByEmail { getUserByEmail(email: \"" + expected.getEmail() + "\") {id, firstName, lastName, email, isSignatureReady} }",
                "data.getUserByEmail",
                new TypeRef<User>() {});
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get user by EMAIL, Should return status code not found")
    void getUserByEmail_shouldThrowNotFound() {
        User wrongUser = new User().setEmail("no_value");
        ExecutionResult execute = queryExecutor.execute(
                "query getUserByEmail { getUserByEmail(email: \"" + wrongUser.getEmail() + "\") {id, firstName, lastName, email, isSignatureReady} }");
        assertFalse(execute.getErrors().isEmpty());
        String errorType = execute.getErrors()
                .stream()
                .map(e -> e.getExtensions().get("errorType").toString())
                .collect(Collectors.joining());
        assertEquals(errorType, ErrorType.NOT_FOUND.toString());
    }

    @Test
    @DisplayName("Get user by ID, Should return user")
    void getById() {
        User expected = createUser();
        User actual = queryExecutor.executeAndExtractJsonPathAsObject(
                "query getUserById { getUserById(id: \"" + expected.getId() + "\") {id, firstName, lastName, email, isSignatureReady} }",
                "data.getUserById",
                new TypeRef<User>() {});
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get user by ID, Should return status code not found")
    void getUserById_shouldThrowNotFound() {
        User wrongUser = new User().setId("0");
        ExecutionResult execute = queryExecutor.execute(
                "query getUserById { getUserById(id: \"" + wrongUser.getId() + "\") {id, firstName, lastName, email, isSignatureReady} }");
        assertFalse(execute.getErrors().isEmpty());
        String errorType = execute.getErrors()
                .stream()
                .map(e -> e.getExtensions().get("errorType").toString())
                .collect(Collectors.joining());
        assertEquals(errorType, ErrorType.NOT_FOUND.toString());
    }

    private User createUser() {
        return userRepository.insert(new User().setEmail("tester@mail.com"));
    }
}