package com.study.restapi.service;

import com.study.restapi.dto.request.LoginRequest;
import com.study.restapi.dto.request.SignupRequest;
import com.study.restapi.dto.response.TokenResponse;
import com.study.restapi.dto.response.UserResponse;


public interface AuthService {
    UserResponse signUp(SignupRequest request);
    TokenResponse login(LoginRequest request);
}
