package com.trello.mytrello_api.service;

import com.trello.mytrello_api.dto.RegistrDto;

import java.util.Map;

public interface RegistrUser {
    Map<Object, Object> createUser(RegistrDto registrDto);
}
