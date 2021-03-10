package net.cryptodoc.service.impl;

import net.cryptodoc.exceptions.UserEmailNotFoundException;
import net.cryptodoc.exceptions.UserIdNotFoundException;
import net.cryptodoc.model.User;
import net.cryptodoc.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @AfterEach
    void tearDown() {
        reset(userRepository);
    }

    @Test
    @DisplayName("getByEmail: Should return user")
    void getByEmail() {
        String email = "testmail@mail.com";
        User expected = new User().setEmail(email);
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(expected));

        User actual = userService.getByEmail(email);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getByEmail: Should thrown when not found")
    void getByEmail_shouldThrow() {
        assertThrows(UserEmailNotFoundException.class, () -> userService.getByEmail("wrong@mail.com"));
    }

    @Test
    @DisplayName("getById: Should return user")
    void getById() {
        String id = "abc123";
        User expected = new User().setId(id);
        when(userRepository.findById(id))
                .thenReturn(Optional.of(expected));

        User actual = userService.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("getById: Should thrown when not found")
    void getById_shouldThrow() {
        assertThrows(UserIdNotFoundException.class, () -> userService.getById("wrongId"));
    }
}