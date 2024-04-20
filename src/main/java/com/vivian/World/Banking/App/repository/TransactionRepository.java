package com.vivian.World.Banking.App.repository;

import com.vivian.World.Banking.App.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
