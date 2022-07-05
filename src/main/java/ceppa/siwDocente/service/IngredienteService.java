package ceppa.siwDocente.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceppa.siwDocente.dto.IngredientiSelectorDTO;
import ceppa.siwDocente.model.Ingrediente;
import ceppa.siwDocente.model.Piatto;
import ceppa.siwDocente.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired private IngredienteRepository ingredienteRepository;
	@Autowired private PiattoService piattoService;
	
	public Set<Ingrediente> getAllIngredienti() {
		Set<Ingrediente> ingredienti = new HashSet<Ingrediente>();
		for (Ingrediente ingrediente : ingredienteRepository.findAll()) {
			ingredienti.add(ingrediente);
		}
		return ingredienti;
	}
	
	public Ingrediente saveIngrediente(Ingrediente ingrediente) {
		return this.ingredienteRepository.save(ingrediente);
	}

	public Ingrediente getIngredienteById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}

	public void deleteIngrediente(Long id) {
		this.ingredienteRepository.deleteById(id);
	}

	@Transactional
	public Piatto addIngredientiToPiatto(IngredientiSelectorDTO dto) {
		// Retrieve del piatto
		Piatto piatto = this.piattoService.getPiattoById(dto.getPiattoId());
		// Per ogni ingrediente esistente
		for (Ingrediente ingrediente : this.getAllIngredienti()) {
			// Controllo se l'ingrediente è stato inserito 
			if (dto.getIngredientiSelected().contains(ingrediente)) {
				// Controllo sull'ingrediente se tra i piatti, il piatto in questione non era già stato inserito
				if (!ingrediente.getPiatti().contains(piatto)) {
					// Inserisco il piatto in ingrediente.piatti e salvo
					ingrediente.addPiatto(piatto);
					this.ingredienteRepository.save(ingrediente);
					// Inserisco l'ingrediente in piatto.ingredienti e salvo
					piatto.addIngrediente(ingrediente);
					this.piattoService.savePiatto(piatto);
				}
				// Se invece era già stato inserito => tutto okay
			} else { // Se non è stato inserito (è stato deselezionato)
				// Controllo sull'ingrediente se tra i piatti, il piatto in questione era già stato inserito
				if (ingrediente.getPiatti().contains(piatto)) {
					/*
					 * Nel caso in cui l'ingrediente era stato già inserito, e lo deseleziono tra le checkbox, devo fare in modo di rimuovere la reference dalla lista
					 */
					// Rimuovo il piatto in ingredente.piatti
					ingrediente.removePiatto(piatto);
					this.ingredienteRepository.save(ingrediente);
					// Rimuovo l'ingrediente in piatto.ingredienti
					piatto.removeIngrediente(ingrediente);
					this.piattoService.savePiatto(piatto);
				}
				// Se invece non era stato inserito => tutto okay
			}
		}
		return piatto;
	}
	
}
