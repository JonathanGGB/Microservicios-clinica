package com.clinic.studies.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinic.studies.entity.Prognostic;

public interface PrognosticRepository extends JpaRepository<Prognostic, Long>{
    Optional<Prognostic> findByPatient_IdAndState(Long patientId, String state);
}
