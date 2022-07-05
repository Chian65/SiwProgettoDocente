package ceppa.siwDocente.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Column(length = 255)
	@NotBlank
	private String descrizione;
	
	@ManyToOne
	private Chef chef;
	
	//Molti piatti possono essere presenti in più buffet
	@ManyToMany
	private Set<Buffet> buffets;
	
	//Molti piatti possono avere gli stessi ingredienti
	@ManyToMany(mappedBy = "piatti")
	private Set<Ingrediente> ingredienti;
	
	public Piatto() {}

	public Piatto(Long id, @NotBlank String nome, @NotBlank String descrizione, Set<Buffet> buffets,
			Set<Ingrediente> ingredienti) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.buffets = buffets;
		this.ingredienti = ingredienti;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Buffet> getBuffets() {
		return buffets;
	}

	public void setBuffets(Set<Buffet> buffets) {
		this.buffets = buffets;
	}

	public Set<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public void addBuffet(Buffet buffet) {
		this.buffets.add(buffet);
	}
	
	public void removeBuffet(Buffet buffet) {
		this.buffets.remove(buffet);
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		this.ingredienti.add(ingrediente);
	}

	public void removeIngrediente(Ingrediente ingrediente) {
		this.ingredienti.remove(ingrediente);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(buffets, descrizione, id, ingredienti, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piatto other = (Piatto) obj;
		return Objects.equals(buffets, other.buffets) && Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(id, other.id) && Objects.equals(ingredienti, other.ingredienti)
				&& Objects.equals(nome, other.nome);
	}
	
}
