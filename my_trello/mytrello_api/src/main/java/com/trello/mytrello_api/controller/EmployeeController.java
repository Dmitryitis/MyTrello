package com.trello.mytrello_api.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String getEmployess() {
        return "Welocme!";
    }
}
