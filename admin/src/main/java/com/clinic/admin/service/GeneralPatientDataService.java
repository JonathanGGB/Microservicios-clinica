package com.clinic.admin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
@Log4j2
public class GeneralPatientDataService {
    private final RestTemplate restTemplate = new RestTemplate();
    public String getGeneralPatientData(Long patientId, Long medicalHistoryId, String email) throws Exception {
        String patientRecordsUrl = "http://localhost:6200/records/patient-record/" + email;
        String patientMedicalHistoryUrl = "http://localhost:6300/studies/complete-medical-history/" + patientId.toString() + "/" + medicalHistoryId.toString();
        String patientStudiesUrl = "http://localhost:6300/studies/patient-studies/" + patientId;

        ResponseEntity<String> patientRecordsResponse = restTemplate.getForEntity(patientRecordsUrl, String.class);
        ResponseEntity<String> patientMedicalHistoryResponse = restTemplate.getForEntity(patientMedicalHistoryUrl, String.class);
        ResponseEntity<String> patientStudiesResponse = restTemplate.getForEntity(patientStudiesUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode combinedJson = objectMapper.createObjectNode();
        combinedJson.set("medicalHistory", objectMapper.readTree(patientMedicalHistoryResponse.getBody()));
        combinedJson.set("records", objectMapper.readTree(patientRecordsResponse.getBody()));
        combinedJson.set("studies", objectMapper.readTree(patientStudiesResponse.getBody()));

        JsonNode combinedJsonNode = objectMapper.readTree(combinedJson.toString());
        JsonNode recordsNode = combinedJsonNode.get("records");
        JsonNode patientDtoNode = recordsNode.get("patientDto");

        ObjectNode patientInfoNode = objectMapper.createObjectNode();
        patientInfoNode.set("patientInfo", patientDtoNode);

        ((ObjectNode) recordsNode).remove("patientDto");
        ((ObjectNode) combinedJsonNode).set("patientData", patientInfoNode);

        return combinedJsonNode.toString();
    }







}
