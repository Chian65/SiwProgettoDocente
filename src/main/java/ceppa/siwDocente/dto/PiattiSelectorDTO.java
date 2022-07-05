package ceppa.siwDocente.dto;

import java.util.Set;

import ceppa.siwDocente.model.Piatto;

public class PiattiSelectorDTO {

	private Long buffetId;
	private Set<Piatto> piatti;
	private Set<Piatto> piattiSelected;
	
	public PiattiSelectorDTO() {}

	public PiattiSelectorDTO(Long buffetId, Set<Piatto> piatti, Set<Piatto> piattiSelected) {
		this.buffetId = buffetId;
		this.piatti = piatti;
		this.piattiSelected = piattiSelected;
	}

	public Long getBuffetId() {
		return buffetId;
	}

	public void setBuffetId(Long buffetId) {
		this.buffetId = buffetId;
	}

	public Set<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(Set<Piatto> piatti) {
		this.piatti = piatti;
	}

	public Set<Piatto> getPiattiSelected() {
		return piattiSelected;
	}

	public void setPiattiSelected(Set<Piatto> piattiSelected) {
		this.piattiSelected = piattiSelected;
	}

}
