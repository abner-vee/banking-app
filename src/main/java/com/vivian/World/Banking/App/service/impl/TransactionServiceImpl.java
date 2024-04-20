package com.vivian.World.Banking.App.service.impl;

import com.vivian.World.Banking.App.domain.entity.Transaction;
import com.vivian.World.Banking.App.payload.request.TransactionRequest;
import com.vivian.World.Banking.App.repository.TransactionRepository;
import com.vivian.World.Banking.App.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
   private final TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionRequest.getTransactionType())
                .accountNumber(transactionRequest.getAccountNumber())
                .amount(transactionRequest.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);

        System.out.println("Transaction saved successfully");
    }
}
