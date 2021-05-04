package com.trello.mytrello_api.config;

import com.trello.mytrello_api.config.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final List<String> clients = Arrays.asList("google");

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        List<ClientRegistration> registrations = clients.stream()
//                .map(c -> getRegistration(c))
//                .filter(registration -> registration != null)
//                .collect(Collectors.toList());
//        return new InMemoryClientRegistrationRepository(registrations);
//    }

//    private static String CLIENT_PROPERTY_KEY
//            = "spring.security.oauth2.client.registration.";

//    @Autowired
//    private Environment environment;
//
//
//    private ClientRegistration getRegistration(String client) {
//        String clientId = environment.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");
//        String clientSecret = environment.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");
//        if (client.equals("google")) {
//            return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(clientId).clientSecret(clientSecret).build();
//        }
//        return null;
//    }
//
//    @Bean
//    public OAuth2AuthorizedClientService authorizedClientService() {
//        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
//    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        final String corsOrigin = "http://localhost:8080";

        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/api/v1/auth/authenticate", "/greeting","/api/v1/register", "/swagger-ui.html", "/v2/api-docs", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().oauth2Login();
        httpSecurity.cors();

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(new CorsFilter(corsConfigurationSource(corsOrigin)), AbstractPreAuthenticatedProcessingFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected CorsConfigurationSource corsConfigurationSource(String corsOrigin) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsOrigin));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "OPTIONS", "PUT", "PATCH", "DELETE"));
        configuration.setMaxAge(10L);
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Accept", "Access-Control-Request-Method", "Access-Control-Request-Headers",
                "Accept-Language", "Authorization", "Content-Type", "Request-Name", "Request-Surname", "Origin", "X-Request-AppVersion",
                "X-Request-OsVersion", "X-Request-Device", "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
