package com.vivian.World.Banking.App.service;

import com.vivian.World.Banking.App.payload.request.TransactionRequest;

public interface TransactionService {
    void saveTransaction(TransactionRequest transactionRequest);

}
