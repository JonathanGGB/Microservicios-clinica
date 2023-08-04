package com.clinic.records.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObstGynecHistoryDto {
	private String fullname;
	private Timestamp menarche;
    private int mensualCicle;
    private int basl;
    private int birthNum;
    private Timestamp lpd;
    private int abortions;
    private int cesareans;
}
