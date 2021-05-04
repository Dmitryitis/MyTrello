package com.trello.mytrello_api.handler;

import com.trello.mytrello_api.models.User;
import com.trello.mytrello_api.repository.UserRepository;
import com.trello.mytrello_api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OidcUser user = (OidcUser) authentication.getPrincipal();

        User user1 = userService.findEmail((String) user.getClaims().get("email"));
        if (user1 == null) {
            User google_user = new User();
            google_user.setEmail((String) user.getClaims().get("email"));
            google_user.setAuthenticationProvider(User.AuthenticationProvider.GOOGLE);
            google_user.setUsername((String) user.getClaims().get("given_name"));
            google_user.setSurname((String) user.getClaims().get("family_name"));
            google_user.setRole(User.Role.USER);
            google_user.setState(User.State.ACTIVE);
            userRepository.save(google_user);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
