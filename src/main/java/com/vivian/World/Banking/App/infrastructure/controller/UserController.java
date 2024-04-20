package com.vivian.World.Banking.App.infrastructure.controller;

import com.vivian.World.Banking.App.payload.request.CreditAndDebitRequest;
import com.vivian.World.Banking.App.payload.request.EnquiryRequest;
import com.vivian.World.Banking.App.payload.request.TransferRequest;
import com.vivian.World.Banking.App.payload.response.BankResponse;
import com.vivian.World.Banking.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/credit-account")
    public BankResponse creditAccount(@RequestBody CreditAndDebitRequest request) {
        return userService.creditAccount(request);
    }
        @PostMapping("/debit-account")
        public BankResponse debitAccount(@RequestBody CreditAndDebitRequest request){
            return  userService.debitAccount(request);
    }
    @GetMapping("/balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.balanceEnquiry(enquiryRequest);
    }
    @GetMapping("/name-enquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }
    @PostMapping("/transfer")
    public BankResponse transfer(@RequestBody TransferRequest request){
        return  userService.transfer(request);
    }
}
