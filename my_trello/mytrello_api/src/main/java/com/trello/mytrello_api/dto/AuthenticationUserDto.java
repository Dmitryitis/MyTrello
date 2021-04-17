package com.trello.mytrello_api.dto;

import lombok.Data;

@Data
public class AuthenticationUserDto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
