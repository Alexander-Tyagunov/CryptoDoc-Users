package net.cryptodoc.service;

import net.cryptodoc.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getByEmail(String email);
    User getById(String userId);
    User create(User user);
    User updateSignatureStatus(User user);
    void checkIfExists(String id);
    void checkSignatureStatus(User user);
}
