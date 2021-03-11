package net.cryptodoc.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.cryptodoc.model.User;
import net.cryptodoc.service.UserService;

import java.util.List;

@Slf4j
@DgsComponent
@RequiredArgsConstructor
public class UserDataFetcherImpl implements UserDataFetcher {

    private final UserService userService;

    @Override
    @DgsData(parentType = "Query", field = "getUsers")
    public List<User> getUsers() {
        log.debug("getUsers - start");

        List<User> result = userService.getAll();

        log.debug("getUsers - end: result = {}", result);
        return result;
    }

    @Override
    @DgsData(parentType = "Query", field = "getUserByEmail")
    public User getUserByEmail(@InputArgument("email") String email) {
        log.debug("getUserByEmail - start, email = {}", email);

        User result = userService.getByEmail(email);

        log.debug("getUserByEmail - end, result = {}", result);
        return result;
    }

    @Override
    @DgsData(parentType = "Mutation", field = "updateSignatureStatus")
    public User updateSignatureStatus(DataFetchingEnvironment dataFetchingEnvironment) {
        log.debug("updateSignatureStatus - start, dataFetchingEnvironment = {}", dataFetchingEnvironment);

        User result = userService.updateSignatureStatus(dataFetchingEnvironment);

        log.debug("updateSignatureStatus - end, result = {}", result);
        return result;
    }

    @Override
    @DgsData(parentType = "Query", field = "getUserById")
    public User getById(String id) {
        log.debug("getById - start, id = {}", id);

        User result = userService.getById(id);

        log.debug("getById - end, result = {}", result);
        return result;
    }

    @Override
    @DgsData(parentType = "Mutation", field = "createUser")
    public User create(DataFetchingEnvironment dataFetchingEnvironment) {
        log.debug("create - start, dataFetchingEnvironment = {}", dataFetchingEnvironment);

        User result = userService.create(dataFetchingEnvironment);

        log.debug("create - end, result = {}", result);
        return result;
    }
}
