package ceppa.siwDocente.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@NotBlank
	private String nome; 
	
	@NotBlank
	private String origine;
 
	@Column(length = 255)
	@NotBlank
	private String descrizione;
	
	//Molti ingredienti possono essere presenti in più piatti
	@ManyToMany
	private List<Piatto> piatti;
	
	public Ingrediente() {}

	public Ingrediente(Long id, @NotBlank String nome, @NotBlank String origine, @NotBlank String descrizione,
			List<Piatto> piatti) {
		this.id = id;
		this.nome = nome;
		this.origine = origine;
		this.descrizione = descrizione;
		this.piatti = piatti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public void addPiatto(Piatto piatto) {
		this.piatti.add(piatto);
	}
	
	public void removePiatto(Piatto piatto) {
		this.piatti.remove(piatto);
	}
	
}
