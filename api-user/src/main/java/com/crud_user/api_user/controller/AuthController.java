package com.crud_user.api_user.controller;

import com.crud_user.api_user.dto.LoginRequest;
import com.crud_user.api_user.dto.LoginResponse;
import com.crud_user.api_user.security.JwtTokenProvider;
import com.crud_user.api_user.service.AuthService;
import com.crud_user.api_user.service.CustomUserDetailsService;
import com.crud_user.api_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        LoginResponse response = service.login(loginRequest);
        return ResponseEntity.ok(response.getToken());
    }

}
