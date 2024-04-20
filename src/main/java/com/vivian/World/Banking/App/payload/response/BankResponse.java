package com.vivian.World.Banking.App.payload.response;

import com.vivian.World.Banking.App.domain.entity.ErrorDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BankResponse<T> {

    private String responseCode;

    private String responseMessage;

    private AccountInfo accountInfo;

    public BankResponse(String responseCode, String responseMessage, AccountInfo accountInfo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.accountInfo = accountInfo;
    }

    public BankResponse(String responseMessage, ErrorDetails errorDetails) {
        this.responseMessage = responseMessage;
    }

    public BankResponse(String message, String fileUrl) {
        this.responseMessage = message;

    }

    public BankResponse(String message) {
        this.responseMessage = message;
    }
}
