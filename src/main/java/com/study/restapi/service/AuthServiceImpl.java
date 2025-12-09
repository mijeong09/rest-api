package com.study.restapi.service;

import com.study.restapi.dto.request.LoginRequest;
import com.study.restapi.dto.request.SignupRequest;
import com.study.restapi.dto.response.TokenResponse;
import com.study.restapi.dto.response.UserResponse;
import com.study.restapi.entity.User;
import com.study.restapi.exception.CustomException;
import com.study.restapi.exception.ErrorCode;
import com.study.restapi.repository.UserRepository;
import com.study.restapi.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public UserResponse signUp(SignupRequest request) {
        // 중복 검사
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .username(request.getName())
                .password(encodedPassword)
                .name(request.getName())
                .email(request.getEmail())
                .build();

        User saved = userRepository.save(user);
        return UserResponse.from(saved);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.finaByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.createToken(user.getUsername());

        return TokenResponse.of(accessToken);
    }
}
