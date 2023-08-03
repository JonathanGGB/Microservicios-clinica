package com.clinic.studies.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinic.studies.entity.Study;

public interface StudyRepository extends JpaRepository<Study, Long>{
    Optional<Study> findByPatientId(Long patientId);
}
