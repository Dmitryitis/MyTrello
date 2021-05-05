package com.trello.mytrello_api.controller;


import com.trello.mytrello_api.config.jwt.JwtTokenUtil;
import com.trello.mytrello_api.dto.OauthDto;
import com.trello.mytrello_api.dto.UserDto;
import com.trello.mytrello_api.service.impl.OauthUserImpl;
import com.trello.mytrello_api.service.impl.UserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@Slf4j
@CrossOrigin
public class AuthenticationRestControllerV1 {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private OauthUserImpl oauthUser;

    @ApiOperation(value = "Авторизация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно авторизирован", response = UserDto.class)})
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        System.out.println("Find user: "+ userDto.getEmail());
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());

        String token = jwtTokenUtil.generateToken(userDetails);

        Map<Object, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("status", 200);

        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "Авториация пользователя Google")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно найден")})
    @RequestMapping(value = "/auth-google", method = RequestMethod.POST)
    public ResponseEntity<?> getGoogleUser(@RequestBody OauthDto user_google) {
        UserDetails userDetails = oauthUser.createOrGetUser(user_google);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<Object, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("status", 200);

        return ResponseEntity.ok(res);
    }
}
