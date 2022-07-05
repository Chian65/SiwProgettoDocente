package ceppa.siwDocente.dto;

import java.util.Set;

import ceppa.siwDocente.model.Ingrediente;

public class IngredientiSelectorDTO {

	private Long piattoId;
	private Set<Ingrediente> ingredienti;
	private Set<Ingrediente> ingredientiSelected;
	
	public IngredientiSelectorDTO() {}

	public IngredientiSelectorDTO(Long piattoId, Set<Ingrediente> ingredienti, Set<Ingrediente> ingredientiSelected) {
		this.piattoId = piattoId;
		this.ingredienti = ingredienti;
		this.ingredientiSelected = ingredientiSelected;
	}

	public Long getPiattoId() {
		return piattoId;
	}

	public void setPiattoId(Long piattoId) {
		this.piattoId = piattoId;
	}

	public Set<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Set<Ingrediente> getIngredientiSelected() {
		return ingredientiSelected;
	}

	public void setIngredientiSelected(Set<Ingrediente> ingredientiSelected) {
		this.ingredientiSelected = ingredientiSelected;
	}

}
