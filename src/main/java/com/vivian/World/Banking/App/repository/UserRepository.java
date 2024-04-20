package com.vivian.World.Banking.App.repository;

import com.vivian.World.Banking.App.domain.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Boolean existsByEmail(String email);

    Boolean existsByAccountNumber(String accountNumber);

    UserEntity findByAccountNumber(String accountNumber);


    Optional<UserEntity> findByEmail(String email);
}
