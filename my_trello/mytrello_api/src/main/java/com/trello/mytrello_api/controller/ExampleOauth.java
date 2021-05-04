package com.trello.mytrello_api.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Component
@RestController
public class ExampleOauth {
    @RequestMapping(value = "/users")
    public Principal user(Principal principal) {
        return principal;
    }
}
