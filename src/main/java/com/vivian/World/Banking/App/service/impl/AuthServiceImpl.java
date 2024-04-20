package com.vivian.World.Banking.App.service.impl;

import com.vivian.World.Banking.App.domain.entity.UserEntity;
import com.vivian.World.Banking.App.domain.enums.Roles;
import com.vivian.World.Banking.App.infrastructure.config.JwtTokenProvider;
import com.vivian.World.Banking.App.payload.request.EmailDetails;
import com.vivian.World.Banking.App.payload.request.LoginRequest;
import com.vivian.World.Banking.App.payload.request.UserRequest;
import com.vivian.World.Banking.App.payload.response.APIResponse;
import com.vivian.World.Banking.App.payload.response.AccountInfo;
import com.vivian.World.Banking.App.payload.response.BankResponse;
import com.vivian.World.Banking.App.payload.response.JwtAuthResponse;
import com.vivian.World.Banking.App.repository.UserRepository;
import com.vivian.World.Banking.App.service.AuthService;
import com.vivian.World.Banking.App.service.EmailService;
import com.vivian.World.Banking.App.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;
    @Override
    public BankResponse registerUser(UserRequest userRequest) {
        log.info("This is the email for signup " + userRequest.getEmail());
        if (userRepository.existsByEmail(userRequest.getEmail())){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        UserEntity newUser =UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNUmber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNUmber(userRequest.getAlternativePhoneNUmber())
                .status("ACTIVE")
                .roles(Roles.USER)
                .profilePicture("https://res.cloudinary.com/dpfqbb9pl/image/upload/v1701260428/maleprofile_ffeep9.png")
                .build();

        UserEntity saveUser = userRepository.save(newUser);

        //send email alert

            EmailDetails emailDetails = EmailDetails.builder()
                .recipient(saveUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("CONGRATULATIONS!!! Your Account Has Been Created Successfully. \n Your Account Details: \n" +
                        "Account Name: " + saveUser.getFirstName() + " " + saveUser.getLastName() + " " + saveUser.getMiddleName() +
                        "\nAccount Number: " + saveUser.getAccountNumber())
                .build();

        if (emailDetails != null) {
            emailService.sendEmailAlert(emailDetails);
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(saveUser.getAccountBalance())
                            .accountNumber(saveUser.getAccountNumber())
                            .accountName(saveUser.getFirstName() + " " +
                                    saveUser.getLastName() + " " +
                                    saveUser.getMiddleName())
                            .build())
                    .build();
        }
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                .build();

    }

    @Override
    public ResponseEntity<APIResponse<JwtAuthResponse>> login(LoginRequest loginRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(loginRequest.getEmail());

        Authentication authentication = null;

        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        EmailDetails loginAlert = EmailDetails.builder()
                .subject("You are logged in")
                .recipient(loginRequest.getEmail())
                .messageBody("You logged into your account. if you did not initiate this request, contact our support desk.")
                .build();

        emailService.sendEmailAlert(loginAlert);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        UserEntity userEntity = userEntityOptional.get();



        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new APIResponse<>(
                                "Login Successful",
                                JwtAuthResponse.builder()
                                        .accessToken(token)
                                        .tokenType("Bearer")
                                        .id(userEntity.getId())
                                        .email(userEntity.getEmail())
                                        .gender(userEntity.getGender())
                                        .firstName(userEntity.getFirstName())
                                        .lastName(userEntity.getLastName())
                                        .profilePicture(userEntity.getProfilePicture())
                                        .role(userEntity.getRoles())
                                        .build()
                        )
                );

    }
}
