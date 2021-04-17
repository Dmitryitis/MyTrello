package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.dto.RegistrDto;
import com.trello.mytrello_api.models.User;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.RegistrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrUserImpl implements RegistrUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<Object, Object> createUser(RegistrDto registrDto) {
        User user = new User();
        user.setEmail(registrDto.getEmail());
        user.setSurname(registrDto.getSurname());
        user.setUsername(registrDto.getUsername());
        user.setHashPassword(passwordEncoder.encode(registrDto.getPassword()));
        user.setState(User.State.ACTIVE);
        user.setRole(User.Role.USER);
        user.setLastVisit(LocalDateTime.now());
        userRepository.save(user);

        Map<Object, Object> response = new HashMap<>();
        response.put("status", 200);
        return response;
    }
}
