package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.models.User;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setHashPassword(passwordEncoder.encode(user.getHashPassword()));
        user.setRole(User.Role.USER);
        user.setState(User.State.ACTIVE);

        return userRepository.save(user);
    }


    @Override
    public User findEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

}
