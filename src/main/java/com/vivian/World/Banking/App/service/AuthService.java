package com.vivian.World.Banking.App.service;

import com.vivian.World.Banking.App.domain.entity.UserEntity;
import com.vivian.World.Banking.App.payload.request.LoginRequest;
import com.vivian.World.Banking.App.payload.request.UserRequest;
import com.vivian.World.Banking.App.payload.response.APIResponse;
import com.vivian.World.Banking.App.payload.response.BankResponse;
import com.vivian.World.Banking.App.payload.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AuthService {

    BankResponse registerUser(UserRequest userRequest);
    ResponseEntity<APIResponse<JwtAuthResponse>> login(LoginRequest loginRequest);

}
