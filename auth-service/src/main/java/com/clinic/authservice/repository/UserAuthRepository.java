package com.clinic.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.authservice.entity.UserAuth;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findByUserName (String userName);
}
