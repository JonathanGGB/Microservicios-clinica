package com.clinic.studies.repository;

import com.clinic.studies.entity.HumanSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HumanSystemRepository extends JpaRepository<HumanSystem, Long> {
    Optional<HumanSystem> findByHumanSystemName(String humanSystemName);
}