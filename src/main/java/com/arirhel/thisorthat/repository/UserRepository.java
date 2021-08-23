package com.arirhel.thisorthat.repository;


import com.arirhel.thisorthat.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
