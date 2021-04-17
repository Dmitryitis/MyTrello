package com.trello.mytrello_api.repository;

import com.trello.mytrello_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);
}
