package com.clinic.studies.repository;

import com.clinic.studies.entity.HumanSystem;
import com.clinic.studies.entity.SystemDisease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemDiseaseRepository extends JpaRepository<SystemDisease, Long> {
    Optional<SystemDisease> findByDiseaseNameAndHumanSystem(String diseaseName, HumanSystem humanSystem);
}
