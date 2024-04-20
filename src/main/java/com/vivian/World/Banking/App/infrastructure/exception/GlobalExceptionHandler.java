package com.vivian.World.Banking.App.infrastructure.exception;

import com.vivian.World.Banking.App.domain.entity.ErrorDetails;
import com.vivian.World.Banking.App.payload.response.BankResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotSentException.class)
    public ResponseEntity<BankResponse<ErrorDetails>> handleEmailNotSentException(final EmailNotSentException ex){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .debugMessage("Email not sent")
                .build();

        BankResponse<ErrorDetails> response = new BankResponse<>(ex.getMessage(), errorDetails);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
