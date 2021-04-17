package com.trello.mytrello_api.service;

import com.trello.mytrello_api.models.User;

public interface UserService {
    User register(User user);

    User findEmail(String email);
}
