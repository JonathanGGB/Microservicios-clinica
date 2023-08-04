package com.clinic.studies.client;

import com.clinic.studies.dto.client.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "studies-service/records", path = "/patient", url = "")
public interface IPatientClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<PatientDto> findPatientById(@PathVariable("id")Long id);
}
