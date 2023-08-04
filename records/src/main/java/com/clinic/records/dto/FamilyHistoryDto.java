package com.clinic.records.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FamilyHistoryDto {
	private String hearthDeseases;
	private String allergies;
	private String diabetes;
	private String psychiatrics;
	private String otherSyndromes;
	
	public void setHearthDeseases(boolean present) {
		this.hearthDeseases = isPresent(present);
	}
	
	public void setAllergies(boolean present) {
		this.allergies = isPresent(present);
	}
	
	public void setDiabetes(boolean present) {
		this.diabetes = isPresent(present);
	}
	
	public void setPsychiatrics(boolean present) {
		this.psychiatrics = isPresent(present);
	}
	
	public void setOtherSyndromes(boolean present) {
		this.otherSyndromes = isPresent(present);
	}
	
	private String isPresent(boolean present) {
		if(present) {
			return "Si";
		}else {
			return "No";
		}
	}
	
}
