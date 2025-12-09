package com.study.restapi.service;

import com.study.restapi.dto.response.UserResponse;

public interface UserService {
    UserResponse findByUsername(String username);
}
