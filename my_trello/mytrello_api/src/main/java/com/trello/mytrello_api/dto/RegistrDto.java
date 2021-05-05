package com.trello.mytrello_api.dto;

import com.trello.mytrello_api.validation.annotation.ValidPassword;
import com.trello.mytrello_api.validation.annotation.ValidUniqEmail;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegistrDto {

    @Email(message = "Неккоректный email")
    @ValidUniqEmail(message = "Такой email уже существует")
    private String email;

    @ValidPassword(message = "Неправильный формат пароля. Пример: Qwer1")
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
