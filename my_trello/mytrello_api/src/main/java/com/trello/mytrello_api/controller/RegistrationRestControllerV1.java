package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.dto.RegistrDto;
import com.trello.mytrello_api.service.impl.RegistrUserImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class RegistrationRestControllerV1 {

    @Autowired
    RegistrUserImpl registrUser;

    @ApiOperation(value = "Регистрация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = RegistrDto.class)})
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registr(@RequestBody RegistrDto registrDto) {
        return ResponseEntity.ok(registrUser.createUser(registrDto));
    }
}
