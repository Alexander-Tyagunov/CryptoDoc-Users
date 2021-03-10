package net.cryptodoc.service.impl;

import lombok.RequiredArgsConstructor;
import net.cryptodoc.exceptions.SignatureIsValidException;
import net.cryptodoc.exceptions.UserExistsException;
import net.cryptodoc.exceptions.UserEmailNotFoundException;
import net.cryptodoc.exceptions.UserIdNotFoundException;
import net.cryptodoc.model.User;
import net.cryptodoc.repository.UserRepository;
import net.cryptodoc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(String userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    @Override
    public User create(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserExistsException(user.getEmail());
        }
        return repository.insert(user);
    }

    @Override
    public User updateSignatureStatus(User user) {
        User actualUser = getById(user.getId());
        checkSignatureStatus(actualUser);
        actualUser.setIsSignatureReady(true);
        return repository.save(actualUser);
    }

    @Override
    @Transactional(readOnly = true)
    public void checkSignatureStatus(User user) {
        checkIfExists(user.getId());
        if (user.getIsSignatureReady()) {
            throw new SignatureIsValidException(user.getEmail());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void checkIfExists(String id) {
        if (!repository.existsById(id)) {
            throw new UserIdNotFoundException(id);
        }
    }
}
