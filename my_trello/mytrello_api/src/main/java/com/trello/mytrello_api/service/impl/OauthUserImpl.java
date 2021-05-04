package com.trello.mytrello_api.service.impl;

import com.trello.mytrello_api.dto.OauthDto;
import com.trello.mytrello_api.models.User;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OauthUserImpl implements OauthUser {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails createOrGetUser(OauthDto oauthDto) {
        User user = userRepository.findByEmail(oauthDto.getEmail());
        if (user != null) {
            return userDetailsService.loadUserByUsername(user.getEmail());
        } else {
            User google_user = new User();
            google_user.setEmail(oauthDto.getEmail());
            google_user.setState(User.State.ACTIVE);
            google_user.setRole(User.Role.USER);
            google_user.setUsername(oauthDto.getName());
            google_user.setSurname(oauthDto.getSurname());
            google_user.setAuthenticationProvider(User.AuthenticationProvider.GOOGLE);
            google_user.setLastVisit(LocalDateTime.now());
            userRepository.save(google_user);
            return userDetailsService.loadUserByUsername(oauthDto.getEmail());
        }
    }
}
