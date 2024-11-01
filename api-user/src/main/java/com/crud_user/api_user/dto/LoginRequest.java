package com.crud_user.api_user.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String usernameOrId;
    private String password;
}
