package com.trello.mytrello_api.dto;

import lombok.Data;

@Data
public class RegistrDto {
    private String email;
    private String password;
    private String username;
    private String surname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surnmame) {
        this.surname = surnmame;
    }
}
