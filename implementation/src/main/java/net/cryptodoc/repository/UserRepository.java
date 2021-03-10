package net.cryptodoc.repository;

import net.cryptodoc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByEmail(String email);
    Optional<User> findByFirstNameLike(String firstName);
}
