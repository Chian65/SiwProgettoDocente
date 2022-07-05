package ceppa.siwDocente.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotBlank
	private String nazionalita;	
	
	@OneToMany(mappedBy = "chef")
	private Set<Piatto> piatti;
	
	//Ogni chef ha più buffet
	@OneToMany(mappedBy = "chef")
	private Set<Buffet> buffets; 
	
	public Chef() {
		
	}

	public Chef(Long id, @NotBlank String nome, @NotBlank String cognome, @NotBlank String nazionalita,
			Set<Buffet> buffets) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.buffets = buffets;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public Set<Buffet> getBuffets() {
		return buffets;
	}

	public void setBuffets(Set<Buffet> buffets) {
		this.buffets = buffets;
	}

	public Set<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(Set<Piatto> piatti) {
		this.piatti = piatti;
	}
	
}
