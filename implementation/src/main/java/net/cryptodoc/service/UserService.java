package net.cryptodoc.service;

import graphql.schema.DataFetchingEnvironment;
import net.cryptodoc.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getByEmail(String email);
    User getById(String userId);
    User create(DataFetchingEnvironment environment);
    User updateSignatureStatus(DataFetchingEnvironment environment);
    void checkIfExists(String id);
    void checkSignatureStatus(User user);
}
