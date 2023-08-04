package com.clinic.records.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerinatalHistoryDto {
	private String fullname;
	private Timestamp birthday;
	private int gestationWeeks;
	private String apgar;
	private BigDecimal weigh;
	private BigDecimal heigth;
	private int pregnancyNum;
	private String birthProblems;
}
