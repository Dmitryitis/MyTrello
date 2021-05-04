package com.trello.mytrello_api.service;

import com.trello.mytrello_api.dto.OauthDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface OauthUser {
    UserDetails createOrGetUser(OauthDto oauthDto);
}
