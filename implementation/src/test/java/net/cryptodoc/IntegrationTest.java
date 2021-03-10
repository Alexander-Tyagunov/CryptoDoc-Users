package net.cryptodoc;

import net.cryptodoc.model.User;
import net.cryptodoc.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    protected UserRepository userRepository;

    protected User testUserWithNoSignature = new User()
            .setFirstName("firstName")
            .setLastName("lastName")
            .setEmail("integrationone@test.com")
            .setIsSignatureReady(false);

    protected User testUserWithSignature = new User()
            .setFirstName("firstName")
            .setLastName("lastName")
            .setEmail("integrationtwo@test.com")
            .setIsSignatureReady(true);

    @BeforeEach
    final void setUp() {
        userRepository.insert(testUserWithNoSignature);
        userRepository.insert(testUserWithSignature);
    }

    @AfterEach
    final void tearDown() {
        userRepository.deleteAll();
    }
}
