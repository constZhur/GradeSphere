package ru.mirea.gradesphere.service.security.impl;

import ru.mirea.gradesphere.dao.request.SignInRequest;
import ru.mirea.gradesphere.dao.request.SignUpRequest;
import ru.mirea.gradesphere.dao.response.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signup(SignUpRequest request);

    JwtAuthResponse signin(SignInRequest request);
}
