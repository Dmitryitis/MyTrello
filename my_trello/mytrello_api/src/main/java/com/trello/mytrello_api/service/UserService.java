package com.trello.mytrello_api.service;

import com.trello.mytrello_api.models.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User findEmail(String email);

    List<User> allUsers();
}
