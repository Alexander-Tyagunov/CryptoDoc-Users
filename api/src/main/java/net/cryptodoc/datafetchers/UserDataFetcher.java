package net.cryptodoc.datafetchers;

import graphql.schema.DataFetchingEnvironment;
import net.cryptodoc.model.User;

import java.util.List;

public interface UserDataFetcher {

    List<User> getUsers();
    User getUserByEmail(String email);
    User updateSignatureStatus(DataFetchingEnvironment dataFetchingEnvironment);
    User getById(String id);
    User create(DataFetchingEnvironment dataFetchingEnvironment);
}
