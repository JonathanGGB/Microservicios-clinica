package com.clinic.securityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.securityservice.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long>{
	Optional<UserAuth> findByUserName (String userName);
}
