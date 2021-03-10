package net.cryptodoc.repository;

import net.cryptodoc.model.User;
import net.cryptodoc.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest extends IntegrationTest {

    @Test
    @DisplayName("insert: Should return inserted user")
    public void insert() {
        User expected = new User()
                .setFirstName("first")
                .setLastName("last")
                .setEmail("email@user.com")
                .setIsSignatureReady(false);
        User actual = userRepository.insert(expected);
        assertEquals(expected, actual);
    }
}
