package com.trello.mytrello_api.controller;

import com.trello.mytrello_api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board/")
public class AccountControllerV1 {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Все пользователи")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "пользователи")})
    @GetMapping(value = "users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }
}
