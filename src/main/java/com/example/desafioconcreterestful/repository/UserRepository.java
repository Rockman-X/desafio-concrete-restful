package com.example.desafioconcreterestful.repository;

import com.example.desafioconcreterestful.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findByToken(UUID token);
    Optional<User> findById(UUID id);
}

