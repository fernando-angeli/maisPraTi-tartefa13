package com.crud_user.api_user.service;

import com.crud_user.api_user.dto.LoginRequest;
import com.crud_user.api_user.dto.LoginResponse;
import com.crud_user.api_user.repository.UserRepository;
import com.crud_user.api_user.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request){
        String username = getUsernameFromRequest(request);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, request.getPassword())
            );
            LoginResponse response = new LoginResponse();
            String token = jwtTokenProvider.generateToken(authentication);
            response.setToken(token);
            return response;
        } catch (UsernameNotFoundException error) {
            throw new UsernameNotFoundException("Credenciais inválidas.");
        }
    }

    private String getUsernameFromRequest(LoginRequest loginRequest) {
        try {
            Long userId = Long.parseLong(loginRequest.getUsernameOrId());
            return userService.getUserById(userId).getUsername();
        } catch (NumberFormatException e) {
            return loginRequest.getUsernameOrId();
        }
    }
}
