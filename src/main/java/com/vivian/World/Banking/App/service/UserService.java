package com.vivian.World.Banking.App.service;

import com.vivian.World.Banking.App.payload.request.CreditAndDebitRequest;
import com.vivian.World.Banking.App.payload.request.EnquiryRequest;
import com.vivian.World.Banking.App.payload.request.TransferRequest;
import com.vivian.World.Banking.App.payload.response.BankResponse;

public interface UserService {
    BankResponse creditAccount(CreditAndDebitRequest request);

    BankResponse debitAccount(CreditAndDebitRequest request);

    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);

    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse transfer(TransferRequest request);
}
